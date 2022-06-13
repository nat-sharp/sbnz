import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-student-homepage',
  templateUrl: './student-homepage.component.html',
  styleUrls: ['./student-homepage.component.css']
})
export class StudentHomepageComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void { }

  calendar() {
    this.router.navigate(['/sessions']);
  }

  subjects() {
    this.router.navigate(['/subjects']);
  }

  logOut() {
    this.router.navigate(['']);
    sessionStorage.setItem('username', '');
  }
}