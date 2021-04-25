import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import {Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  USER_NAME_SESSION_ATTRIBUTE_NAME = 'authenticatedUser';
  USER_NAME_SESSION_ATTRIBUTE_PASSWD = 'password';

  public username: string;
  public password: string;
  private _source: string;

  constructor(private http: HttpClient, private router: Router) {
    if (!this.isUserLoggedIn()) {
      this.router.navigate(['/']);
    }
  }

  authenticationService(username: string, password: string) {
    return this.http.get(`http://localhost:8080/api/v1/pessoas`,
      { headers: { authorization: this.createBasicAuthToken(username, password) } }).pipe(map((res) => {
        this.username = username;
        this.password = password;
        this.registerSuccessfulLogin(username, password);
      }));
  }
  createBasicAuthToken(username: string, password: string) {
    return 'Basic ' + window.btoa(`${username}:${password}`);
  }

  carregarSource() {
    this.http.get<string>('http://localhost:8080/source', {
      headers: new HttpHeaders({'Content-Type': 'application/json',  accept: 'text/plain'}),
      responseType: 'text' as any
    }).subscribe(value => {
      this._source = value;
    });
  }

  get source(): string {
    return this._source;
  }

  registerSuccessfulLogin(username, password) {
    sessionStorage.setItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME, username);
    sessionStorage.setItem(this.USER_NAME_SESSION_ATTRIBUTE_PASSWD, password);
  }

  logout() {
    sessionStorage.removeItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME);
    sessionStorage.removeItem(this.USER_NAME_SESSION_ATTRIBUTE_PASSWD);
    this.username = null;
    this.password = null;
  }

  isUserLoggedIn() {
    const user = sessionStorage.getItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME);
    const passwd = sessionStorage.getItem(this.USER_NAME_SESSION_ATTRIBUTE_PASSWD);
    this.username = user;
    this.password = passwd;
    return user !== null && passwd !== null;
  }

  getLoggedInUserName() {
    const user = sessionStorage.getItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME);
    return user === null ? '' : user;
  }
}
