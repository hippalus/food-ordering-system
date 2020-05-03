import {Injectable} from "@angular/core";
import {CanActivate, Router} from "@angular/router";
import {Observable} from "rxjs/Observable";
import {Observer} from "rxjs/Observer";
import {BasketService} from "../services/basket.service";

@Injectable()
export class PopulatedCartRouteGuard implements CanActivate {
  public constructor(private router: Router,
                     private basketService: BasketService) {
  }

  public canActivate(): Observable<boolean> {
    return new Observable<boolean>((observer: Observer<boolean>) => {
      const cartSubscription = this.basketService
        .get()
        .subscribe((cart) => {
          if (cart.items.length === 0) {
            observer.next(false);
            this.router.navigate(["/"]);
          } else {
            observer.next(true);
          }
        });
      return () => cartSubscription.unsubscribe();
    });
  }
}
