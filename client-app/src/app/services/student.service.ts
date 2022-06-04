import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Main } from 'src/main';

@Injectable({
  providedIn: 'root'
})
export class StudentService {
  readonly URL: string = Main.PATH + "api/student/";

  constructor(private http: HttpClient) { }

  login(loginDto: any): Observable<string> {
    return this.http.post(this.URL + 'login', loginDto, { responseType: 'text' });
  }
}
