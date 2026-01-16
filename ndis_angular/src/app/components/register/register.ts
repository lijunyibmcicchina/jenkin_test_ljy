import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  imports: [FormsModule],
  templateUrl: './register.html',
  styleUrl: './register.css',
})
export class Register {
  constructor(private http: HttpClient, private router: Router) {}

  formData = {
    fullName: '',
    email: '',
    password: '',
  };

  register() {
    this.http.post('/api/auth/register', this.formData).subscribe((res: any) => {
      if (res.code == 200) {
        this.router.navigate(['/login']);
      } else {
        alert('Registration failed: ' + res.message);
      }
    });
  }
}
