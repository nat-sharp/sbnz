import { Injectable } from '@angular/core';
import { Main } from 'src/main';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  readonly URL: string = Main.PATH + "api/admin/";

  constructor(private http: HttpClient) { }

  login(loginDto: any): Observable<string> {
    return this.http.post(this.URL + 'login', loginDto, { responseType: 'text' });
  }

  getReport(): Observable<any> {
    return this.http.get(this.URL + 'report');
  }

}
