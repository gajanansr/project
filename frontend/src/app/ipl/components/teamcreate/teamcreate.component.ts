import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-team-create',
  templateUrl: './teamcreate.component.html',
  styleUrls: ['./teamcreate.component.scss']
})
export class TeamCreateComponent {
  teamForm: FormGroup;
  successMessage = '';
  errorMessage = '';

  constructor(private fb: FormBuilder) {
    this.teamForm = this.fb.group({
      teamId: ['', Validators.required],
      teamName: ['', [Validators.required, Validators.pattern('^[a-zA-Z0-9 ]+$')]],
      location: ['', Validators.required],
      ownerName: ['', Validators.required],
      establishmentYear: ['', [Validators.required, Validators.min(1900), Validators.max(new Date().getFullYear())]]
    });
  }

  onSubmit() {
    if (this.teamForm.invalid) {
      this.errorMessage = 'Please fix the errors in the form.';
      return;
    }

    const { teamName } = this.teamForm.value;
    if (this.simulateBackendError(teamName)) {
      this.errorMessage = 'Backend Error: Team name already exists.';
      return;
    }

    console.log('Team Data:', this.teamForm.value);
    this.successMessage = 'Team created successfully!';
    this.errorMessage = '';
    this.resetForm();
  }

  simulateBackendError(teamName: string): boolean {
    return teamName.toLowerCase() === 'invalidteam';
  }

  resetForm() {
    this.teamForm.reset({
      establishmentYear: new Date().getFullYear()
    });
  }
}
