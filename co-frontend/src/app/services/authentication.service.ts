import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private currentUserSubject: BehaviorSubject<any>;
    public currentUser: Observable<any>;
    private urlbase: string = '/co-backend/';

    constructor(private http: HttpClient) {
        this.currentUserSubject = new BehaviorSubject<any>(JSON.parse(localStorage.getItem('currentUser')));
        this.currentUser = this.currentUserSubject.asObservable();
    }

    public get currentUserValue() {
        return this.currentUserSubject.value;
    }

    public dummyLogin() {
      localStorage.setItem('currentUser', JSON.stringify(true));
    }

    register(username, password, email) {

    }

    login(username, password) {

      const body = new HttpParams()
    .set('login', username)
    .set('password', password);
      let headers = new Headers({'Content-Type': 'application/x-www-form-urlencoded'});

        return this.http.post<any>(this.urlbase + `login`, body.toString(), {
          headers: new HttpHeaders()
            .set('Content-Type', 'application/x-www-form-urlencoded')
        }).subscribe((data: any) => {
          console.log(data);
          if (data.success) {
            localStorage.setItem('currentUser', JSON.stringify(data.token));
            this.currentUserSubject.next(data);
            window.location.href = ""
          }
        })  ;
        /*
            .pipe(
              map( response => {
                // store user details and jwt token in local storage to keep user logged in between page refreshes
                localStorage.setItem('currentUser', JSON.stringify(response));
                this.currentUserSubject.next(response);
                return response;
              })// Subscribe
              //,catchError( error => {})
            );
            */
    }

    logout() {
        // remove user from local storage and set current user to null
        localStorage.removeItem('currentUser');
        this.currentUserSubject.next(null);
    }
}
