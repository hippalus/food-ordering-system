@TypeDefs(value = {
        @TypeDef(defaultForType = PaymentId.class, typeClass = PaymentIdType.class),
        @TypeDef(defaultForType = TransactionId.class, typeClass = TransactionIdType.class),
        @TypeDef(defaultForType = TransactionItemId.class, typeClass = TransactionItemIdType.class)
})
package com.hippalus.paymentservice.infrastructer.hibernate;

import com.hippalus.paymentservice.domain.models.PaymentId;
import com.hippalus.paymentservice.domain.models.TransactionId;
import com.hippalus.paymentservice.domain.models.TransactionItemId;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;