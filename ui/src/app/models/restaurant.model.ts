import {Menu} from "app/models/menu.model";

export class Restaurant {
  public id: string;
  public name: string;
  public menus: Menu[]

  /* public updateFrom(src: Restaurant): void {
    this.id = src.id;
    this.name = src.name;
    this.menus = src.menus;
   
  } */
}
