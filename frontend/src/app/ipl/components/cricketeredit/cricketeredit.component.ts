import { HttpErrorResponse } from "@angular/common/http";
import { Component, OnInit } from "@angular/core";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { IplService } from "../../services/ipl.service";
import { Cricketer } from "../../types/Cricketer";
import { Team } from "../../types/Team";
import { ActivatedRoute, Router } from "@angular/router";

@Component({
    selector: 'app-cricketeredit',
    templateUrl: './cricketeredit.component.html',
    styleUrls: ['./cricketeredit.component.scss'],
  })
export class CricketerEditComponent implements OnInit {
    cricketerForm!: FormGroup;
    cricketer: Cricketer | null = null;
    successMessage: string | null = null;
    errorMessage: string | null = null;
    teams: Team[] = [];
    cricketerId: number;

    constructor(
        private formBuilder: FormBuilder,
        private iplService: IplService,
        private route: ActivatedRoute
      ) {}
  
    ngOnInit(): void {
      this.cricketerForm = this.formBuilder.group({
        team: [null, Validators.required],
        cricketerName: ['', Validators.required],
        age: [null, [Validators.required, Validators.min(18)]],
        nationality: ['', Validators.required],
        experience: [null, [Validators.required, Validators.min(0)]],
        role: ['', Validators.required],
        totalRuns: [null, [Validators.min(0)]],
        totalWickets: [null, [Validators.min(0)]],
      });

      this.route.params.subscribe(params => {
        console.log(params);
        this.cricketerId = params.cricketerId;
        this.loadTeamsAndCricketerDetails(this.cricketerId);
      });
    }
  
    loadTeamsAndCricketerDetails(cricketerId: number): void {
        this.iplService.getAllTeams().subscribe({
          next: (teams) => {
            this.teams = teams;
            this.loadCricketerDetails(cricketerId);
          },
          error: (error) => {
            console.error('Error loading teams:', error);
          }
        });
    }

    loadCricketerDetails(cricketerId: number): void {
        this.iplService.getCricketerById(cricketerId).subscribe({
            next: (response) => {
                const selectedTeam = this.teams.find(team => team.teamId === response.team.teamId);
                this.cricketer = response;
                this.cricketerForm.patchValue({
                    team: selectedTeam,
                    cricketerName: response.cricketerName,
                    age: response.age,
                    nationality: response.nationality,
                    experience: response.experience,
                    role: response.role,
                    totalRuns: response.totalRuns,
                    totalWickets: response.totalWickets
                })
            },
            error: (error) => {
                this.handleError(error);
            }
        });
    }
  
    onSubmit(): void {
      if (this.cricketerForm.valid) {
        const updatedCricketer: Cricketer = {
          cricketerId: this.cricketerId,
          team: this.cricketerForm.value.team,
          cricketerName: this.cricketerForm.value.cricketerName,
          age: this.cricketerForm.value.age,
          nationality: this.cricketerForm.value.nationality,
          experience: this.cricketerForm.value.experience,
          role: this.cricketerForm.value.role,
          totalRuns: this.cricketerForm.value.totalRuns,
          totalWickets: this.cricketerForm.value.totalWickets,
          displayInfo: function (): void {}
        }
        this.iplService.updateCricketer(updatedCricketer).subscribe({
          next: (response) => {
            this.cricketer = response;
            this.errorMessage = null;
            console.log(this.cricketer);
            this.cricketerForm.reset();
          },
          error: (error) => {
            this.handleError(error);
          },
          complete: () => {
            this.successMessage = 'Cricketer updated successfully!';
          }
        });
      }
    }
  
    private handleError(error: HttpErrorResponse): void {
      if (error.error instanceof ErrorEvent) {
        // Client-side error
        this.errorMessage = `Client-side error: ${error.error.message}`;
      } else {
        // Backend error
        this.errorMessage = `Server-side error: ${error.status} ${error.message}`;
        // Optionally, you can handle different status codes here
        if (error.status === 400) {
          this.errorMessage = 'Bad request. Please check your input.';
        }
      }
      this.successMessage = null;
    }
  }
  