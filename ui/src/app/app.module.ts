import {NgModule} from "@angular/core";
import {FormsModule} from "@angular/forms";
import {BrowserModule} from "@angular/platform-browser";

import {AppComponent} from "./app.component";
import {AppRoutingModule} from "./app.routing";
import {PaymentComponent} from "./components/payment/payment.component";
import {PaymentConfirmationComponent} from "./components/payment-confirmation/payment-confirmation.component";
import {BasketComponent} from "./components/basket/basket.component";
import {OrderComponent} from "./components/order/order.component";
import {PopulatedCartRouteGuard} from "./route-gaurds/populated-cart.route-gaurd";
import {ProductsDataService} from "./services/products.service";
import {BasketService} from "./services/basket.service";
import {LocalStorageServie, StorageService} from "./services/storage.service";

import {HttpClientModule} from '@angular/common/http';


@NgModule({
  bootstrap: [AppComponent],
  declarations: [
    AppComponent,
    BasketComponent,
    OrderComponent,
    PaymentComponent,
    PaymentConfirmationComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [
    ProductsDataService,
    PopulatedCartRouteGuard,
    LocalStorageServie,
    {provide: StorageService, useClass: LocalStorageServie},
    {
      deps: [StorageService, ProductsDataService],
      provide: BasketService,
      useClass: BasketService
    }
  ]
})
export class AppModule {
}
