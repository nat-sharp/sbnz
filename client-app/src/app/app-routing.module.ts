import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminHomepageComponent } from './admin-homepage/admin-homepage.component';
import { FrontPageComponent } from './front-page/front-page.component';
import { LoginAdminComponent } from './login-admin/login-admin.component';
import { LoginStudentComponent } from './login-student/login-student.component';
import { ObligationsComponent } from './obligations/obligations.component';
import { RegisterComponent } from './register/register.component';
import { SessionsComponent } from './sessions/sessions.component';
import { StudentHomepageComponent } from './student-homepage/student-homepage.component';
import { SubjectsComponent } from './subjects/subjects.component';

const routes: Routes = [
  { path: '', component: FrontPageComponent },
  { path: 'loginAdmin', component: LoginAdminComponent },
  { path: 'loginStudent', component: LoginStudentComponent },
  { path: 'admin', component: AdminHomepageComponent },
  { path: 'student', component: StudentHomepageComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'subjects', component: SubjectsComponent },
  { path: 'obligations', component: ObligationsComponent },
  { path: 'sessions', component: SessionsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
