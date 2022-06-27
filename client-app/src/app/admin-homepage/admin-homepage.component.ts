import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-homepage',
  templateUrl: './admin-homepage.component.html',
  styleUrls: ['./admin-homepage.component.css']
})
export class AdminHomepageComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void { }

  reports() {
    this.router.navigate(["/reports"]);
  }

  logOut() {
    this.router.navigate(['']);
    sessionStorage.setItem('username', '');
  }
}
