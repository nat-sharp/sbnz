import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Main } from 'src/main';

@Injectable({
  providedIn: 'root'
})
export class CalendarService {
  readonly URL: string = Main.PATH + "api/studycal/";

  constructor(private http: HttpClient) { }

  createSessionsForStudent(username: string): Observable<any> {
    console.log("POZVAN CREATING")
    return this.http.post(this.URL + username, null);
  }

  getSessionsForStudent(username: string): Observable<any> {
    console.log("POZVAN GET")
    return this.http.get(this.URL + username);
  }
}
