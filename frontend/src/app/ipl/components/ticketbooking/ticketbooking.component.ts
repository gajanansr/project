import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { TicketBooking } from '../../types/TicketBooking';

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

  constructor(private formBuilder: FormBuilder) {}

  ngOnInit(): void {
    this.ticketBookingForm = this.formBuilder.group({
      bookingId: [null, Validators.required],
      email: ['', [Validators.required, Validators.email]],
      matchId: [null, Validators.required],
      numberOfTickets: [null, [Validators.required, Validators.min(1)]]
    });
  }

  onSubmit(): void {
    if (this.ticketBookingForm.valid) {
      this.ticketBooking = this.ticketBookingForm.value;
      this.successMessage = 'Tickets booked successfully!';
      this.errorMessage = null;
      console.log(this.ticketBooking);
      this.resetForm();
    } else {
      this.successMessage = null;
      this.errorMessage = 'Please fill out all required fields correctly.';
    }
  }

  resetForm(): void {
    this.ticketBookingForm.reset();
  }
}
