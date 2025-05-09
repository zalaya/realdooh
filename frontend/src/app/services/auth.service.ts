import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Credentials {
    email: string;
    password: string;
}

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    private readonly url = `http://localhost:8080/auth/login`;

    constructor(private http: HttpClient) { }

    login(credentials: Credentials): Observable<any> {
        return this.http.post<any>(this.url, credentials);
    }
}
