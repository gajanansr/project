import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent {
  registrationForm: FormGroup;
  successMessage = '';
  errorMessage = '';

  constructor(private fb: FormBuilder) {
    this.registrationForm = this.fb.group({
      fullName: ['', Validators.required],
      username: ['', [Validators.required, Validators.pattern('^[a-zA-Z0-9_]+$')]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [
        Validators.required,
        Validators.minLength(8),
        Validators.pattern('^(?=.*[A-Z])(?=.*\\d).{8,}$')
      ]]
    });
  }

  onSubmit() {
    if (this.registrationForm.invalid) {
      this.errorMessage = 'Please fill out all required fields correctly.';
      return;
    }

    console.log(this.registrationForm.value);
    this.successMessage = 'Registration successful!';
    this.errorMessage = '';
    this.registrationForm.reset();
  }

  resetForm() {
    this.registrationForm.reset();
    this.successMessage = '';
    this.errorMessage = '';
  }
}
