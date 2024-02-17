import { HttpInterceptorFn } from "@angular/common/http";
import { inject } from "@angular/core";
import { CookieService } from "ngx-cookie-service";

export const authInterceptor: HttpInterceptorFn = (req, next) => {
    const cookieService = inject(CookieService);
    const token = cookieService.get('token');
    req = req.clone({
        setHeaders: {
            Authorization: `Token ${token}`
        }
    });
    return next(req);
}