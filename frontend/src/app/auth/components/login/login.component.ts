import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  loginForm: FormGroup;
  successMessage = '';
  errorMessage = 'Invalid username or password.';

  constructor(private fb: FormBuilder) {
    this.loginForm = this.fb.group({
      username: ['', [
        Validators.required,
        Validators.pattern('^[a-zA-Z0-9_]+$') 
      ]],
      password: ['', [
        Validators.required,
        Validators.minLength(8),
        Validators.pattern('^(?=.*[A-Z])(?=.*\\d).{8,}$')
      ]]
    });
  }

  onSubmit() {
    this.successMessage = '';
    this.errorMessage = 'Invalid username or password.';

    if (this.loginForm.invalid) {
      this.errorMessage = 'Please fill out all required fields correctly.';
      return;
    }

    const { username, password } = this.loginForm.value;


    if (this.simulateBackendLoginError(username, password)) {
      this.errorMessage = 'Invalid username or password.';
      return;
    }

    this.successMessage = 'Login successful!';
  }

  simulateBackendLoginError(username: string, password: string): boolean {
    return username === 'erroruser' || password === 'WrongPass1';
  }
}
