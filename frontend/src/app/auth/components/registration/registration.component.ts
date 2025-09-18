import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from 'src/app/ipl/types/User';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {
  registrationForm!: FormGroup;
  successMessage: string | null = null;
  errorMessage: string | null = null;

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.registrationForm = this.formBuilder.group({
      fullName: ['', Validators.required],
      username: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(8)]],
      email: ['', [Validators.required, Validators.email]],
      role: ["", [Validators.required]],
    });
  }

  onSubmit(): void {
    if (this.registrationForm.valid) {
      this.authService.createUser(this.registrationForm.value).subscribe(
        response => {
          this.successMessage = 'Registration successful!';
          this.errorMessage = null;
          this.registrationForm.reset();
        },
        error => {
          this.errorMessage = error;
          this.successMessage = null;
        }
      );
    } else {
      this.errorMessage = 'Please fill out the form correctly.';
    }
  }
}
