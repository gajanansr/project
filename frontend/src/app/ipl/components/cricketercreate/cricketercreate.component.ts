import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-cricketer-create',
  templateUrl: './cricketercreate.component.html',
  styleUrls: ['./cricketercreate.component.scss']
})
export class CricketerCreateComponent {
  cricketerForm: FormGroup;
  successMessage = '';
  errorMessage = '';

  constructor(private fb: FormBuilder) {
    this.cricketerForm = this.fb.group({
      cricketerId: ['', Validators.required],
      teamId: ['', Validators.required],
      cricketerName: ['', Validators.required],
      age: ['', [Validators.required, Validators.min(15)]],
      nationality: ['', Validators.required],
      experience: ['', [Validators.required, Validators.min(0)]],
      role: ['', Validators.required],
      totalRuns: ['', [Validators.required, Validators.min(0)]],
      totalWickets: ['', [Validators.required, Validators.min(0)]],
      email: ['', [Validators.required, Validators.email]]
    });
  }

  onSubmit() {
    if (this.cricketerForm.invalid) {
      this.errorMessage = 'Please correct the form errors.';
      return;
    }

    const { cricketerName } = this.cricketerForm.value;
    if (this.simulateBackendError(cricketerName)) {
      this.errorMessage = 'Backend Error: Cricketer already exists.';
      return;
    }

    console.log('Cricketer Data:', this.cricketerForm.value);
    this.successMessage = 'Cricketer created successfully!';
    this.errorMessage = '';
    this.resetForm();
  }

  simulateBackendError(name: string): boolean {
    return name.toLowerCase() === 'errorplayer';
  }

  resetForm() {
    this.cricketerForm.reset();
  }
}
