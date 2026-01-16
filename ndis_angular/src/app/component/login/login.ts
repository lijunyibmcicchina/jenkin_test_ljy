import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  imports: [FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {
  constructor(private http: HttpClient, private router: Router) {}

  email: string = '';
  password: string = '';

  login() {
    console.log(this.email + ',,,,' + this.password);
    this.http
      .post('/api/auth/login', {
        email: this.email,
        password: this.password,
      })
      .subscribe((res: any) => {
        //存token，导航到Service types page
        debugger;
        var token = res.data.token;
        if (token) {
          localStorage.setItem('token', token);
          this.router.navigate(['/service-types']);
        }
      });
  }
}
