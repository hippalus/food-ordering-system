import {Component, OnInit} from "@angular/core";
import {BasketService} from "../../services/basket.service";
import {Router} from '@angular/router';
import {ProductsDataService} from "app/services/products.service";
import * as _swal from 'sweetalert';
import {SweetAlert} from 'sweetalert/typings/core';

const swal: SweetAlert = _swal as any;

@Component({
  selector: "app-payment-confirmation",
  templateUrl: "./payment-confirmation.component.html"
})
export class PaymentConfirmationComponent implements OnInit {

  private creditCardNumber: Number;

  public constructor(
    private productsService: ProductsDataService,
    private basketService: BasketService,
    private router: Router) {
  }

  public ngOnInit(): void {

  }

  public pay(): void {
    console.log(this.basketService.orderResponse);

    let payment = {
      "id": this.basketService.orderResponse["id"],
      "cardNumber": this.creditCardNumber,
      "items": [],
      "paymentMethod": "CREDITCARD"
    };

    this.basketService.orderResponse["items"].forEach((element) => {
      let item = {
        "id": element.productId,
        "quantity": element.quantity,
        "itemDescription": element.itemDescription,
        "price": element.price
      };
      payment.items.push(item);
    });


    this.productsService.pay(payment).subscribe(val => {
      console.log(val);
      if (val.paymentStatus == "SUCCESS") {
        this.basketService.empty();
        this.router.navigate(['/']);
      } else if (val.paymentStatus == "FAILED") {
        //popup warning!
        swal({
          title: "Error",
          text: "Payment was not successfull, your order will be cancelled",
          icon: "warning"
        });
      }

    }, err => {
      console.log(err);

    });

  }
}
