import {ChangeDetectionStrategy, Component, OnDestroy, OnInit} from "@angular/core";
import {Product} from "app/models/product.model";
import {ShoppingCart} from "app/models/shopping-cart.model";
import {ProductsDataService} from "app/services/products.service";
import {BasketService} from "app/services/basket.service";
import {Observable} from "rxjs/Observable";
import {Subscription} from "rxjs/Subscription";
import {Router} from '@angular/router';


@Component({
  changeDetection: ChangeDetectionStrategy.OnPush,
  selector: "app-basket",
  templateUrl: "./basket.component.html"
})
export class BasketComponent implements OnInit, OnDestroy {
  public products: Observable<Product[]>;
  public cart: Observable<ShoppingCart>;
  public itemCount: number;

  private cartSubscription: Subscription;

  public shoppingCart: ShoppingCart;

  public constructor(private productsService: ProductsDataService,
                     private basketService: BasketService,
                     private router: Router) {
  }

  public emptyCart(): void {
    this.basketService.empty();
  }

  public order(): void {
    this.shoppingCart.customerId = "Habip";
    this.basketService.total = this.shoppingCart.itemsTotal;
    delete this.shoppingCart.itemsTotal;
    this.productsService.order(this.shoppingCart).subscribe(val => {
      console.log(val);
      this.basketService.orderResponse = val;
      this.router.navigate(['/checkout']);

    }, err => {
      console.log(err);
      /*  this.basketService.orderResponse = {
         "status": 1,
         "id": "f76a90b6-5679-4b79-8f58-ce4f9350da4e",
         "items": [
             {
                 "productId": "b63e-b7e87386-9f9f",
                 "quantity": 1,
                 "itemDescription": "Ciger",
                 "price": 25,
                 "fee": 25
             },
             {
                 "productId": "b7e87386-b63e-4812-9f9f-d33e7f816fea",
                 "quantity": 2,
                 "itemDescription": "Kebap",
                 "price": 30,
                 "fee": 60
             }
         ],
         "createdDate": "2020-04-29T23:07:36.1584+03:00",
         "modifiedDate": null
     };

       this.router.navigate(['/checkout']); */

    });

  }

  public ngOnInit(): void {
    //this.products = this.productsService.all();
    this.cart = this.basketService.get();
    this.cartSubscription = this.cart.subscribe((cart) => {
      this.itemCount = cart.items.map((x) => x.quantity).reduce((p, n) => p + n, 0);
      this.shoppingCart = cart;
    });
  }

  public ngOnDestroy(): void {
    if (this.cartSubscription) {
      this.cartSubscription.unsubscribe();
    }
  }
}
