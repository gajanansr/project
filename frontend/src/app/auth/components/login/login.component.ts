import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { catchError, of, tap } from 'rxjs';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  errorMessage: string | null = null;

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router
    ) {}

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      username: ['', [Validators.required, Validators.pattern(/^[a-zA-Z0-9]+$/)]],
      password: ['', [Validators.required, Validators.minLength(8)]]
    });
  }

  onSubmit(): void {
    if (this.loginForm.valid) {
      console.log(this.loginForm.controls['username'].value);
      console.log(this.loginForm.controls['password'].value);
      this.authService.login(this.loginForm.value).pipe(
        tap((response) => {
          console.log(response);
          localStorage.setItem("token", response.token);
          localStorage.setItem("role", response.roles);
          localStorage.setItem("user_id", response.userId);
          console.log(localStorage.getItem("role"));
          this.router.navigate(["ipl"]);
        }),
        catchError((error: string) => {
          this.errorMessage = 'Invalid username or password';
          console.error("Login error:", error);
          return of(null);
        })
      ).subscribe();
    } else {
      this.errorMessage = 'Please fill out the form correctly.';
    }
  }

}
