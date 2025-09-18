import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { IplService } from '../../services/ipl.service';
import { Match } from '../../types/Match';
import { Team } from '../../types/Team';

@Component({
  selector: 'app-matchcreate',
  templateUrl: './matchcreate.component.html',
  styleUrls: ['./matchcreate.component.scss']
})
export class MatchCreateComponent implements OnInit {
  matchForm!: FormGroup;
  match: Match | null = null;
  successMessage: string | null = null;
  errorMessage: string | null = null;
  teams: Team[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private iplService: IplService
  ) {}

  ngOnInit(): void {
    this.loadTeams();
    this.matchForm = this.formBuilder.group({
      firstTeam: [null, Validators.required],
      secondTeam: [null, Validators.required],
      matchDate: [null, Validators.required],
      venue: [''],
      result: [''],
      status: ['', Validators.required]
    });
  }

  loadTeams(): void {
    this.iplService.getAllTeams().subscribe((teams) => {
      this.teams = teams;
    });
  }

  // Form submission handler
  onSubmit(): void {
    if (this.matchForm.valid) {
      this.addMatch();
    } else {
      this.errorMessage = 'Please fill out all required fields correctly.';
      this.successMessage = null;
    }
  }

  // Method to call backend service and handle the response
  private addMatch(): void {
    this.iplService.addMatch(this.matchForm.value).subscribe(
      (response: Match) => {
        this.match = response;  
        this.successMessage = 'Match created successfully!';
        this.errorMessage = null;
        console.log('Match Created: ', this.match);
        this.resetForm();
      },
      (error: HttpErrorResponse) => {
        this.handleError(error);
      }
    );
  }

  resetForm(): void {
    this.matchForm.reset();
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
