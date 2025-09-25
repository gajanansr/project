import { Injectable } from "@angular/core";
import { environment } from "src/environments/environment";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Team } from "../types/Team";
import { Cricketer } from "../types/Cricketer";
import { Match } from "../types/Match";
import { Vote } from "../types/Vote";
import { TicketBooking } from "../types/TicketBooking";


@Injectable({
  providedIn: "root",
})
export class IplService {
  private baseUrl = `${environment.apiUrl}`;

  constructor(private http: HttpClient) {}

  //Backend API calls of Team
  
  addTeam(team: Team): Observable<Team> {
    // Implementation goes here
    return new Observable<Team>();
  }

  updateTeam(team: Team): Observable<Team> {
    // Implementation goes here
    return new Observable<Team>();
  }

  deleteTeam(teamId: number): Observable<any> {
    // Implementation goes here
    return new Observable<any>();
  }

  getAllTeams(): Observable<Team[]> {
    // Implementation goes here
    return new Observable<Team[]>();
  }

  getTeamById(teamId: number): Observable<Team> {
     // Implementation goes here
     return new Observable<Team>();
  }


  //Backend API calls of Cricketer

  addCricketer(cricketer: Cricketer): Observable<Cricketer> {
    // Implementation goes here
    return new Observable<Cricketer>();
  }

  updateCricketer(cricketer: Cricketer): Observable<Cricketer> {
    // Implementation goes here
    return new Observable<Cricketer>();
  }

  deleteCricketer(cricketerId: number): Observable<any> {
    // Implementation goes here
    return new Observable<any>();
  }

  getAllCricketers(): Observable<Cricketer[]> {
    // Implementation goes here
    return new Observable<Cricketer[]>();
  }

  getCricketerById(cricketerId: number): Observable<Cricketer> {
     // Implementation goes here
     return new Observable<Cricketer>();
  }

  getCricketersByTeam(teamId: number): Observable<Cricketer[]> {
    // Implementation goes here
    return new Observable<Cricketer[]>();
  }

  //Backend API calls of Match

  addMatch(match: Match): Observable<Match> {
    // Implementation goes here
    return new Observable<Match>();
  }

  updateMatch(match: Match): Observable<Match> {
    // Implementation goes here
    return new Observable<Match>();
  }

  deleteMatch(matchId: number): Observable<any> {
    // Implementation goes here
    return new Observable<any>();
  }

  getAllMatches(): Observable<Match[]> {
    // Implementation goes here
    return new Observable<Match[]>();
  }

  getMatchById(matchId: number): Observable<Match> {
     // Implementation goes here
     return new Observable<Match>();
  }

  getAllMatchesByStatus(status: string): Observable<Team[]> {
    // Implementation goes here
    return new Observable<Team[]>();
  }

  //Backend API calls of Vote

  getAllVotes(): Observable<Vote[]> {
    // Implementation goes here
    return new Observable<Vote[]>();
  }
  
  createVote(vote: Vote): Observable<Vote> {
    // Implementation goes here
    return new Observable<Vote>();
  }
  
  getVotesCountOfAllCategories(): Observable<Map<string, number>> {
    // Implementation goes here
    return new Observable<Map<string, number>>();
  }

  //Backend API calls of TicketBooking

  getAllTicketBookings(): Observable<TicketBooking[]> {
    // Implementation goes here
    return new Observable<TicketBooking[]>();
  }
    
  createBooking(ticketBooking: TicketBooking): Observable<TicketBooking> {
    // Implementation goes here
    return new Observable<TicketBooking>();
  }
    
  cancelBooking(bookingId: number): Observable<any> {
    // Implementation goes here
    return new Observable<>();
  }

  getBookingsByUserEmail(email: string): Observable<TicketBooking[]> {
    // Implementation goes here
    return new Observable<TicketBooking[]>();
  }
}
