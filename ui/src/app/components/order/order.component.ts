import {ChangeDetectionStrategy, Component, OnInit} from "@angular/core";
import {Product} from "app/models/product.model";
import {Restaurant} from "app/models/restaurant.model";
import {ProductsDataService} from "app/services/products.service";
import {BasketService} from "app/services/basket.service";
import {Observable} from "rxjs/Observable";
import {Observer} from "rxjs/Observer";
import {Menu} from "app/models/menu.model";


@Component({
  changeDetection: ChangeDetectionStrategy.OnPush,
  selector: "app-order",
  styleUrls: ["./order.component.scss"],
  templateUrl: "./order.component.html"
})
export class OrderComponent implements OnInit {
  public products: Product[];
  public restaurants: Restaurant[];

  public selectedRestaurant: Restaurant;
  public selectedMenu: Menu;

  public menus: Menu[];


  public constructor(private productsService: ProductsDataService,
                     private basketService: BasketService) {
  }

  public addProductToCart(product: Product): void {
    this.basketService.addItem(product, 1);
  }

  public removeProductFromCart(product: Product): void {
    this.basketService.addItem(product, -1);
  }

  public productInCart(product: Product): boolean {
    return Observable.create((obs: Observer<boolean>) => {
      const sub = this.basketService
        .get()
        .subscribe((cart) => {
          obs.next(cart.items.some((i) => i.productId === product.id));
          obs.complete();
        });
      sub.unsubscribe();
    });
  }

  public updateMenuList(): void {
    this.menus = this.selectedRestaurant.menus;
    this.selectedMenu = this.selectedRestaurant.menus[0];
    this.updateProductList();
  }

  public updateProductList(): void {
    this.products = this.selectedMenu.menuItems;
  }

  public ngOnInit(): void {

    this.productsService.getRestaurants().subscribe(val => {
      this.restaurants = val;
      this.selectedRestaurant = this.restaurants[0];
      this.updateMenuList();
    }, err => {
      console.log(err);
    });

  }
}
