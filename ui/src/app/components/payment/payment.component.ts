import {Component, OnDestroy, OnInit} from "@angular/core";
import {CartItem} from "app/models/cart-item.model";
import {Product} from "app/models/product.model";
import {ProductsDataService} from "app/services/products.service";
import {BasketService} from "app/services/basket.service";
import {Subscription} from "rxjs/Subscription";
import {Router} from '@angular/router';


interface ICartItemWithProduct extends CartItem {
  product: Product;
  totalCost: number;
}

@Component({
  selector: "app-payment",
  styleUrls: ["./payment.component.scss"],
  templateUrl: "./payment.component.html"
})
export class PaymentComponent implements OnInit, OnDestroy {
  public cart: Object;
  public cartItems: ICartItemWithProduct[];
  public itemCount: number;

  private products: Product[];
  private cartSubscription: Subscription;


  public constructor(private productsService: ProductsDataService,
                     private basketService: BasketService,
                     private router: Router) {
  }

  public emptyCart(): void {
    this.basketService.empty();
  }


  public ngOnInit(): void {
    this.cart = this.basketService.orderResponse;
    this.cart["grossTotal"] = this.basketService.total;
    this.cartItems = this.basketService.orderResponse["items"];

  }

  public ngOnDestroy(): void {
    if (this.cartSubscription) {
      this.cartSubscription.unsubscribe();
    }
  }
}
