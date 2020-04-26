package com.hippalus.orderingservice.domain.models;

import com.hippalus.orderingservice.domain.commands.CreateOrder;
import com.hippalus.orderingservice.domain.domainevents.OrderCreated;
import com.hippalus.orderingservice.domain.domainevents.OrderStatusChanged;
import com.hippalus.orderingservice.domain.exceptions.AggregateException;
import com.hippalus.orderingservice.domain.policies.OrderPolicy;
import com.hippalus.sharedkernel.domain.AbstractAggregateRoot;
import com.hippalus.sharedkernel.domain.DomainObjectId;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Builder
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
@Entity
public class Orders extends AbstractAggregateRoot<OrderId> {

    private String customerId;

    @Enumerated(EnumType.ORDINAL)
    private OrderStatus status;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", nullable = false)
    private Set<OrderItem> orderItems;

    private OffsetDateTime createdDate;

    private OffsetDateTime modifiedDate;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable
    private Set<OrderStatusChange> statusChangeHistory;

    public Orders() {
        super(DomainObjectId.randomId(OrderId.class));
        this.customerId = "";
        this.orderItems = new HashSet<>();
        this.status = OrderStatus.CREATED;
        this.createdDate = OffsetDateTime.now();

    }
    //TODO Builder
    public Orders(OrderId orderId, String customerId,
                   Set<OrderItem> orderItems, OffsetDateTime createdDate){
        super(orderId);
        this.customerId = customerId;
        this.status = OrderStatus.CREATED;
        this.orderItems = orderItems;
        this.createdDate = createdDate;
        this.statusChangeHistory = new HashSet<>();
    }


    public static Orders create(CreateOrder cmd) throws AggregateException {
        var order =new Orders(cmd.getId(),cmd.getCustomerId(),cmd.getItems(),OffsetDateTime.now());
        OrderPolicy.verify(order);
        order.applyEvent(new OrderCreated<>(order.getId(), order.customerId, order.orderItems, OffsetDateTime.now()));
        return order;
    }

    public void addItem(Product product, int quantity) {
        var item = new OrderItem(product.getId(),quantity,product.getName(),product.getPrice());
        this.orderItems.add(item);
    }
    public BigDecimal totalFee() {
        return this.getOrderItems()
                .stream()
                .map(OrderItem::fee)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void completed()   {
        this.changeStatus(OrderStatus.COMPLETED);
    }

    public void cancel()   {
        this.changeStatus(OrderStatus.CANCELED);
    }

    private void changeStatus(OrderStatus status) {
        var originalStatus = this.status;
        this.status = status;
        this.modifiedDate = OffsetDateTime.now();
        var stateChange=new OrderStatusChange(this.modifiedDate,this.status);
        statusChangeHistory.add(stateChange);
        this.applyEvent(new OrderStatusChanged<>(this.getId(), originalStatus, status, this.modifiedDate));
    }

}
