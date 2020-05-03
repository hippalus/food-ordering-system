import {NgModule} from "@angular/core";
import {RouterModule} from "@angular/router";

import {PaymentComponent} from "./components/payment/payment.component";
import {PaymentConfirmationComponent} from "./components/payment-confirmation/payment-confirmation.component";
import {OrderComponent} from "./components/order/order.component";
import {PopulatedCartRouteGuard} from "./route-gaurds/populated-cart.route-gaurd";

@NgModule({
  exports: [RouterModule],
  imports: [
    RouterModule.forRoot([
      {
        canActivate: [PopulatedCartRouteGuard],
        component: PaymentComponent,
        path: "checkout"
      },
      {
        canActivate: [PopulatedCartRouteGuard],
        component: PaymentConfirmationComponent,
        path: "payment"
      },
      {
        component: OrderComponent,
        path: "**"
      }])
  ]
})
export class AppRoutingModule {
}
