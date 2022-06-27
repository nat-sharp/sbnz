import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AdminService } from '../services/admin.service';


// export interface ReportUnit {
//   studentName: string,
//   studentId: number,
//   category: string,
//   status: string,
//   points: number
// }

// const REPORT_UNITS: ReportUnit[] = [];


@Component({
  selector: 'app-reports',
  templateUrl: './reports.component.html',
  styleUrls: ['./reports.component.css']
})
export class ReportsComponent implements OnInit {
  // columns = [
  //   {
  //     columnDef: 'studentName',
  //     header: 'Student',
  //     cell: (element: ReportUnit) => `${element.studentName}`,
  //   },
  //   {
  //     columnDef: 'points',
  //     header: 'Points',
  //     cell: (element: ReportUnit) => `${element.points}`,
  //   },
  //   {
  //     columnDef: 'category',
  //     header: "Category (by academic points)",
  //     cell: (element: ReportUnit) => `${element.category}h`,
  //   },
  //   {
  //     columnDef: 'status',
  //     header: "Status (by application activity)",
  //     cell: (element: ReportUnit) => `${element.status}`,
  //   }
  // ]

  reps: any;
  displayedColumns : string[] = ['studentName', 'points', 'category', 'status'];

  constructor(private router: Router, 
    private service:  AdminService,) { }

  ngOnInit(): void {
    this.loadData();
  }

  loadData() {
    this.service.getReport().subscribe(
      data => {
       
        this.reps = data.reportUnits;

        console.log("USPEH", data);
        return;
      }, error => {
        console.log("Err", error);
      }
    )
    
  }

  back() {
    this.router.navigate(['/admin']);
  }

}