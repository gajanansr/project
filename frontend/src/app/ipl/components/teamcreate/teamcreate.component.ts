import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { catchError, throwError } from 'rxjs';
import { IplService } from '../../services/ipl.service';
import { Team } from '../../types/Team';

@Component({
  selector: 'app-teamcreate',
  templateUrl: './teamcreate.component.html',
  styleUrls: ['./teamcreate.component.scss']
})
export class TeamCreateComponent implements OnInit {
  teamForm!: FormGroup;
  successMessage: string | null = null;
  errorMessage: string | null = null;
  currentYear: number = new Date().getFullYear();
  team: Team | null = null;

  constructor(
    private formBuilder: FormBuilder,
    private iplService: IplService
  ) {}

  ngOnInit(): void {
    this.initializeForm();
  }

  // Initialize form with validation rules
  private initializeForm(): void {
    this.teamForm = this.formBuilder.group({
      teamName: ['', [Validators.required, Validators.pattern(/^[a-zA-Z0-9 ]+$/)]], // No special characters
      location: ['', [Validators.required]],
      ownerName: ['', [Validators.required, Validators.minLength(2)]],
      establishmentYear: [
        null,
        [Validators.required, Validators.min(1900), Validators.max(this.currentYear)]
      ]
    });
  }

  // Form submission handler
  onSubmit(): void {
    if (this.teamForm.valid) {
      this.addTeam();
    } else {
      this.errorMessage = 'Please fill out all required fields correctly.';
      this.successMessage = null;
    }
  }

  // Method to call backend service and handle the response
  private addTeam(): void {
    this.iplService.addTeam(this.teamForm.value).subscribe(
      (response: Team) => {
        // Ensure that we are treating the response correctly as a Team
        this.team = response;  // This should be of type Team
        this.successMessage = 'Team created successfully!';
        this.errorMessage = null;
        console.log('Team Created: ', this.team);
        this.resetForm();
      },
      (error: HttpErrorResponse) => {
        this.handleError(error);
      }
    );
  }

  // Reset the form after successful submission
  resetForm(): void {
    this.teamForm.reset({
      teamId: null,
      teamName: '',
      location: '',
      ownerName: '',
      establishmentYear: this.currentYear
    });
  }

  // Error handling method
  private handleError(error: HttpErrorResponse): void {
    if (error.error instanceof ErrorEvent) {
      this.errorMessage = `Client-side error: ${error.error.message}`;
    } else {
      this.errorMessage = `Server-side error: ${error.status} ${error.message}`;
      if (error.status === 400) {
        this.errorMessage = 'Bad request. Please check your input.';
      }
    }
    this.successMessage = null;
    console.error('An error occurred:', this.errorMessage);
  }
}
