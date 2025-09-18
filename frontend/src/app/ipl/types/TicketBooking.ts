import { Match } from "./Match";

export class TicketBooking {

    bookingId: number;
    email: string;
    match: Match;
    numberOfTickets: number;

    constructor(
        bookingId: number,
        email: string,
        match: Match,
        numberOfTickets: number
    ) {
        this.bookingId = bookingId;
        this.email = email;
        this.match = match;
        this.numberOfTickets = numberOfTickets;
    }

    displayInfo() {
        console.log(`Booking ID: ${this.bookingId}`);
        console.log(`Email: ${this.email}`);
        console.log(`Number of Tickets: ${this.numberOfTickets}`);
    }
}