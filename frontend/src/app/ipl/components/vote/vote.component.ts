import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Vote } from '../../types/Vote';

@Component({
  selector: 'app-vote',
  templateUrl: './vote.component.html',
  styleUrls: ['./vote.component.scss']
})
export class VoteComponent implements OnInit {
  voteForm!: FormGroup;
  vote: Vote | null = null;
  successMessage: string | null = null;
  errorMessage: string | null = null;

  constructor(private formBuilder: FormBuilder) {}

  ngOnInit(): void {
    this.voteForm = this.formBuilder.group({
      voteId: [null, Validators.required],
      email: ['', [Validators.required, Validators.email]],
      category: ['', Validators.required],
      cricketerId: [null, Validators.required],
      teamId: [null, Validators.required]
    });
  }

  onSubmit(): void {
    if (this.voteForm.valid) {
      this.vote = this.voteForm.value;
      this.successMessage = 'Vote submitted successfully!';
      this.errorMessage = null;
      console.log(this.vote);
      this.resetForm();
    } else {
      this.successMessage = null;
      this.errorMessage = 'Please fill out all required fields correctly.';
    }
  }

  resetForm(): void {
    this.voteForm.reset();
  }
}