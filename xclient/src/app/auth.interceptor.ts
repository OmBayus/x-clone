import { HttpInterceptorFn } from "@angular/common/http";
import { inject } from "@angular/core";
import { CookieService } from "ngx-cookie-service";

export const authInterceptor: HttpInterceptorFn = (req, next) => {
    if (req.url.includes('register') || req.url.includes('login')) {
        return next(req);
    }
    const cookieService = inject(CookieService);
    const token = cookieService.get('token');
    req = req.clone({
        setHeaders: {
            Authorization: `Bearer ${token}`
        }
    });
    return next(req);
}