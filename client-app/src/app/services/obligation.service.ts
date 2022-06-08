import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Main } from 'src/main';

@Injectable({
  providedIn: 'root'
})
export class ObligationService {
  readonly URL: string = Main.PATH + "api/obligation/";

  constructor(private http: HttpClient) { }

  getObligationsForSubject(subjectId: any): Observable<any> {
    return this.http.get(this.URL + 'subject/' + subjectId);
  }

  addObligation(dto: any): Observable<string> {
    return this.http.post(this.URL + 'add/', dto, { responseType: 'text' });
  }

  finishObligation(dto: any): Observable<string> {
    return this.http.post(this.URL + 'finish/', dto, { responseType: 'text' });
  }
}
