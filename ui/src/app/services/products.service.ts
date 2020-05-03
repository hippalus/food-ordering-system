import {Injectable} from "@angular/core";
import {Product} from "app/models/product.model";
import "rxjs/add/operator/map";
import {Observable} from "rxjs/Observable";
import {CachcingServiceBase} from "./caching.service";
import {Restaurant} from "app/models/restaurant.model";

import {HttpClient, HttpHeaders} from '@angular/common/http';

import {ShoppingCart} from "app/models/shopping-cart.model";


let count = 0;

@Injectable()
export class ProductsDataService extends CachcingServiceBase {
  private products: Observable<Product[]>;
  private restaurants: Observable<Restaurant[]>;

  public constructor(private http: HttpClient) {
    super();
  }

  private headers = new HttpHeaders({'Content-Type': 'application/json; charset=utf-8'});

  public getRestaurants(): Observable<any> {
    return this.http.get('http://localhost:8083' + '/restaurants/getall', {headers: this.headers}).map((data) => {
      console.log(data);
      return data;
    })
  }

  public order(order: ShoppingCart): Observable<any> {
    console.log(order);
    return this.http.post('http://localhost:8084' + '/orders/create', JSON.stringify(order), {headers: this.headers}).map((data) => {
      console.log(data);
      return data;
    })
  }

  public pay(payment: Object): Observable<any> {
    return this.http.post('http://localhost:8085' + '/payment/pay', JSON.stringify(payment), {headers: this.headers}).map((data) => {
      console.log(data);
      return data;
    })
  }


}
