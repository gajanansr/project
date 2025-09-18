
import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { IplService } from '../../services/ipl.service';
import { Match } from '../../types/Match';
import { Team } from '../../types/Team';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-matchedit',
  templateUrl: './matchedit.component.html',
  styleUrls: ['./matchedit.component.scss']
})
export class MatchEditComponent implements OnInit {
  matchForm!: FormGroup;
  match: Match | null = null;
  matchId: number;
  successMessage: string | null = null;
  errorMessage: string | null = null;
  teams: Team[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private iplService: IplService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.matchForm = this.formBuilder.group({
      firstTeam: [null, Validators.required],
      secondTeam: [null, Validators.required],
      matchDate: [null, Validators.required],
      venue: [''],
      result: [''],
      status: ['', Validators.required],
      winnerTeam: [null]
    });
    this.route.params.subscribe(params => {
        console.log(params);
        this.matchId = params.matchId;
        this.loadTeamsAndMatchDetails(this.matchId);
      });
  }

  loadTeamsAndMatchDetails(matchId: number): void {
    this.iplService.getAllTeams().subscribe({
      next: (teams) => {
        this.teams = teams;
        this.loadMatchDetails(matchId);
      },
      error: (error) => {
        console.error('Error loading teams:', error);
      }
    });
  }

    loadMatchDetails(matchId: number): void {
        this.iplService.getMatchById(matchId).subscribe({
            next: (response) => {
                this.match = response;
                this.matchForm.patchValue({
                    firstTeam: this.teams.find(team => team.teamId === response.firstTeam.teamId),
                    secondTeam: this.teams.find(team => team.teamId === response.secondTeam.teamId),
                    matchDate: response.matchDate,
                    venue: response.venue,
                    result: response.result,
                    status: response.status,
                    winnerTeam: response.winnerTeam !== null ? this.teams.find(team => team.teamId === response.winnerTeam.teamId): null
                })
            },
            error: (error) => {
                this.handleError(error);
            }
        });
    }


  onSubmit(): void {
    if (this.matchForm.valid) {
        const updatedMatch: Match = {
            matchId: this.matchId,
            matchDate: this.matchForm.value.matchDate,
            firstTeam: this.matchForm.value.firstTeam,
            secondTeam: this.matchForm.value.secondTeam,
            venue: this.matchForm.value.venue,
            result: this.matchForm.value.result,
            status: this.matchForm.value.status,
            winnerTeam: this.matchForm.value.winnerTeam,
            displayInfo: function (): void {}
          }
          this.iplService.updateMatch(updatedMatch).subscribe({
            next: (response) => {
              this.match = response;
              this.errorMessage = null;
              console.log(this.match);
              this.matchForm.reset();
            },
            error: (error) => {
              this.handleError(error);
            },
            complete: () => {
              this.successMessage = 'Match updated successfully!';
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
