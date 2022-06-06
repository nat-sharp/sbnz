import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Main } from 'src/main';

@Injectable({
  providedIn: 'root'
})
export class SubjectService {
  readonly URL: string = Main.PATH + "api/subject/";

  constructor(private http: HttpClient) { }

  getSubjectsForStudent(username: string): Observable<any> {
    return this.http.get(this.URL + 'student/' + username);
  }

  addSubject(username: string, name: string): Observable<string> {
    return this.http.post(this.URL + 'add/' + username, name, { responseType: 'text' });
  }
}
