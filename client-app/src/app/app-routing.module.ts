import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FrontPageComponent } from './front-page/front-page.component';
import { LoginAdminComponent } from './login-admin/login-admin.component';
import { LoginStudentComponent } from './login-student/login-student.component';

const routes: Routes = [
  { path: '', component: FrontPageComponent },
  { path: 'loginAdmin', component: LoginAdminComponent },
  { path: 'loginStudent', component: LoginStudentComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
