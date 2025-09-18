import { Component } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { OnInit } from "@angular/core";
import { Match } from "../../types/Match";

@Component({
    selector: 'app-matchcreate',
    templateUrl: './matchcreate.component.html',
    styleUrls: ['./matchcreate.component.scss']
  })

export class MatchCreateComponent implements OnInit{
    match : Match | null = null;
    successMessage: string | null = null;
    errorMessage: string | null = null;
    matchForm: any;

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.matchForm = this.fb.group({
      matchId: [null, [Validators.required]],
      firstTeamId: [null, [Validators.required]],
      secondTeamId: [null, [Validators.required]],
      matchDate: ['', [Validators.required]],
      venue: ['', [Validators.required]],
      result: ['', [Validators.required]],
      status:['' , [Validators.required]],
      winnerTeamId: [null, Validators.required],
    });
  }

  onSubmit(): void {
    if (this.matchForm.valid) {
      this.match = this.matchForm.value;
      this.successMessage = 'Match created successfully!';
      this.errorMessage = null;
      this.resetForm();
    } else {
      this.errorMessage = 'Please fill out all required fields correctly.';
      this.successMessage = null;
    }
  }

  resetForm(): void {
    // this.cricketerForm.reset({
    //   cricketerId: null,
    //   teamId: null,
    //   cricketerName: '',
    //   age: null,
    //   nationality: '',
    //   experience: null,
    //   role: '',
    //   totalRuns: null,
    //   totalWickets: null
    // });
    this.matchForm.reset();
  }

}