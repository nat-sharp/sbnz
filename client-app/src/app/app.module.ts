import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import { MatTableModule } from '@angular/material/table';
import { MatDialogModule } from '@angular/material/dialog';

import { FrontPageComponent } from './front-page/front-page.component';
import { LoginAdminComponent } from './login-admin/login-admin.component';
import { LoginStudentComponent } from './login-student/login-student.component';
import { AdminHomepageComponent } from './admin-homepage/admin-homepage.component';
import { StudentHomepageComponent } from './student-homepage/student-homepage.component';
import { RegisterComponent } from './register/register.component';
import { SubjectsComponent } from './subjects/subjects.component';
import { ObligationsComponent } from './obligations/obligations.component';
import { AddSubjectDialogComponent } from './subjects/add-subject-dialog/add-subject-dialog.component';

@NgModule({
  declarations: [
    AppComponent,
    FrontPageComponent,
    LoginAdminComponent,
    LoginStudentComponent,
    AdminHomepageComponent,
    StudentHomepageComponent,
    RegisterComponent,
    SubjectsComponent,
    ObligationsComponent,
    AddSubjectDialogComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule,
    MatCardModule,
    MatIconModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatSnackBarModule,
    MatRadioModule,
    MatSelectModule,
    MatTableModule,
    MatDialogModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
