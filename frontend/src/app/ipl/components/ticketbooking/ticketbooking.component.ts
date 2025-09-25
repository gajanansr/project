import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { IplService } from '../../services/ipl.service';
import { Match } from '../../types/Match';
import { TicketBooking } from '../../types/TicketBooking';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-ticketbooking',
  templateUrl: './ticketbooking.component.html',
  styleUrls: ['./ticketbooking.component.scss']
})
export class TicketBookingComponent implements OnInit {
  ticketBookingForm!: FormGroup;
  ticketBooking: TicketBooking | null = null;
  successMessage: string | null = null;
  errorMessage: string | null = null;
  matches: Match[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private iplService: IplService
  ) {}

  ngOnInit(): void {
    this.loadMatches();
    this.ticketBookingForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      match: [null, Validators.required],
      numberOfTickets: [null, [Validators.required, Validators.min(1)]]
    });
  }

  loadMatches(): void {
    this.iplService.getAllMatches().subscribe((matches) => {
      this.matches = matches;
    });
  }
  
  onSubmit(): void {
    if (this.ticketBookingForm.valid) {
      this.bookTicket();
    } else {
      this.errorMessage = 'Please fill out all required fields correctly.';
      this.successMessage = null;
    }
  }

  private bookTicket(): void {
    this.iplService.createBooking(this.ticketBookingForm.value).subscribe(
      (response: TicketBooking) => {
        this.successMessage = 'Ticket booked successfully!';
        this.errorMessage = null;
        this.resetForm();
      },
      (error: HttpErrorResponse) => {
        this.handleError(error);
      }
    );
  }

  resetForm(): void {
    this.ticketBookingForm.reset();
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
