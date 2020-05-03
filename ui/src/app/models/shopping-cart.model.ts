import {CartItem} from "app/models/cart-item.model";

export class ShoppingCart {
  public customerId: string;
  public items: CartItem[] = new Array<CartItem>();
  public itemsTotal: number = 0;

  public updateFrom(src: ShoppingCart) {
    this.items = src.items;
    this.itemsTotal = src.itemsTotal;
  }
}
