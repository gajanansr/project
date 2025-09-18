import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { Cricketer } from '../../types/Cricketer';
// import { passwordValidator, usernameValidator } from './validators'; // Adjust the import path as needed

@Component({
    selector: 'app-cricketercreate',
      templateUrl: './cricketercreate.component.html',
      styleUrls: ['./cricketercreate.component.scss']
})
export class CricketerCreateComponent implements OnInit {
  cricketerForm!: FormGroup;
  successMessage: string | null = null;
  errorMessage: string | null = null;
  cricketer!:Cricketer;
  constructor(private formBuilder: FormBuilder) {}

  ngOnInit(): void {
    this.cricketerForm = this.formBuilder.group({
      cricketerId: [null, Validators.required],
      teamId: [null, Validators.required],
      cricketerName: ['', [Validators.required, this.usernameValidator()]],
      age: [null, [Validators.required, Validators.min(18)]],
      nationality: ['', Validators.required],
      experience: [null, [Validators.required, Validators.min(0)]], // Ensure non-negative experience
      role: ['', Validators.required],
      totalRuns: [null, Validators.min(0)],
      totalWickets: [null, Validators.min(0)]
    });
  }

usernameValidator(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      const username = control.value;
      if (!username) {
        return null;
      }
      const hasSpecialChar = /[^a-zA-Z0-9]/.test(username);
      if (hasSpecialChar) {
        return { 'usernameInvalid': true };
      }
      return null;
    };
  }

  onSubmit(): void {
    if (this.cricketerForm.valid) {
      this.cricketer = this.cricketerForm.value
      this.successMessage = 'Cricketer created successfully!';
      console.log(this.cricketerForm.value);
      this.resetForm();
      this.errorMessage = null;
    } else {
      this.errorMessage = 'Please fill out all required fields correctly.';
      this.successMessage = null;
    }
  }

  resetForm(): void {
    this.cricketerForm.reset();
  }
}