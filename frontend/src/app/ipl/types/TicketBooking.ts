export class TicketBooking {
   
    bookingId:number;
    email:string;
    matchId:number;
    numberOfTickets:number;
 
   
 
 
    constructor(    bookingId:number,
        email:string,
        matchId:number,
        numberOfTickets:number){
        this.bookingId=bookingId;
          this.email=email;
          this.matchId=matchId;
          this.numberOfTickets=numberOfTickets;
         
 
         
      }
 
      displayInfo(){
          console.log(`Booking ID: ${this.bookingId}`);
          console.log(`Email: ${this.email}`);
          console.log(`Number Of Tickets: ${this.numberOfTickets}`);
      }
 
 
}