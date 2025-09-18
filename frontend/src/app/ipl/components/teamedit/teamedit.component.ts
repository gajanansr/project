
import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { IplService } from '../../services/ipl.service';
import { Team } from '../../types/Team';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-teamedit',
  templateUrl: './teamedit.component.html',
  styleUrls: ['./teamedit.component.scss']
})
export class TeamEditComponent implements OnInit {
  teamForm!: FormGroup;
  successMessage: string | null = null;
  errorMessage: string | null = null;
  currentYear: number = new Date().getFullYear();
  team: Team | null = null;
  teamId: number;

  constructor(
    private formBuilder: FormBuilder,
    private iplService: IplService,
    private route: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    this.teamForm = this.formBuilder.group({
        teamName: ['', [Validators.required, Validators.pattern(/^[a-zA-Z0-9 ]+$/)]], // No special characters
        location: ['', [Validators.required]],
        ownerName: ['', [Validators.required, Validators.minLength(2)]],
        establishmentYear: [
          null,
          [Validators.required, Validators.min(1900), Validators.max(this.currentYear)]
        ]
    });
    this.route.params.subscribe(params => {
        console.log(params);
        this.teamId = params.teamId;
        this.loadTeamDetails(this.teamId);
    });
  }

  loadTeamDetails(teamId: number): void {
    this.iplService.getTeamById(teamId).subscribe({
        next: (response) => {
            this.team = response;
            this.teamForm.patchValue({
                teamName: response.teamName,
                location: response.location,
                ownerName: response.ownerName,
                establishmentYear: response.establishmentYear
            })
        },
        error: (error) => {
            this.handleError(error);
        }
    });
}

  onSubmit(): void {
    if (this.teamForm.valid) {
        const updatedTeam: Team = {
          teamId: this.teamId,
          teamName: this.teamForm.value.teamName,
          location: this.teamForm.value.location,
          ownerName: this.teamForm.value.ownerName,
          establishmentYear: this.teamForm.value.establishmentYear,
          displayInfo: function (): void {}
        }
        this.iplService.updateTeam(updatedTeam).subscribe({
            next: (response) => {
              this.team = response;
              this.errorMessage = null;
              console.log(this.team);
              this.teamForm.reset();
            },
            error: (error) => {
              this.handleError(error);
            },
            complete: () => {
              this.successMessage = 'Team updated successfully!';
            }
        });
    } 
  }

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

