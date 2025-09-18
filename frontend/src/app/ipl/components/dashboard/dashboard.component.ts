import { Component, OnInit } from '@angular/core';
import { IplService } from '../../services/ipl.service';
import { Team } from '../../types/Team';
import { Cricketer } from '../../types/Cricketer';
import { Match } from '../../types/Match';
import { TicketBooking } from '../../types/TicketBooking';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Vote } from '../../types/Vote';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  teams: Team[] = [];
  cricketers: Cricketer[] = [];
  matches: Match[] = [];
  ticketsBooked: TicketBooking[] = [];
  allTicketsBooked: TicketBooking[] = [];
  voteList: Vote[];
  voteArray: { key: string, value: number }[] = [];
  emailForm: FormGroup;
  role: string| null;
  userId: number; 

  constructor(
    private readonly iplService: IplService,
    private readonly fb: FormBuilder,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.role = localStorage.getItem("role");
    this.userId = Number(localStorage.getItem("user_id"));
    this.emailForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]]
    });
    if (this.role === 'ADMIN') {
      this.loadAdminData();
    } else {
      this.loadUserData();
    }
  }

  loadAdminData(): void {
    this.loadTeams();
    this.loadCricketers();
    this.loadMatches();
    this.iplService.getAllTicketBookings().subscribe({
      next: (allTicketsBooked) => {
        this.allTicketsBooked = allTicketsBooked;
      },
      error: (error) => {
        console.error('Error loading all tickets booked.', error);
      },
      complete: () => {
        console.log('Ticket bookings loaded successfully.');
      }
    });

    this.iplService.getVotesCountOfAllCategories().subscribe({
      next: (response) => {
        const voteMap: Map<string, number> = response;
        if (voteMap.size > 0) {
          this.voteArray = Array.from(voteMap.entries()).map(([key, value]) => ({ key, value }));
        }
      },
      error: (error) => {
        console.error('Error loading votes count of all categories.', error);
      },
      complete: () => {
        console.log('Votes count of all categories loaded successfully.');
      }
    });
  }

  loadUserData(): void {
    this.loadTeams();
    this.loadCricketers();
    this.loadMatches();
    this.iplService.getAllVotes().subscribe({
      next: (response) => {
        this.voteList = response;
      },
      error: (error) => {
        console.error('Error loading votes', error);
      },
      complete: () => {
        console.log('Votes loaded successfully.');
      }
    });
  }

  loadTeams(): void {
    this.iplService.getAllTeams().subscribe({
      next: (response) => {
        this.teams = response;
      },
      error: (error) => {
        console.error('Error loading teams', error);
      },
      complete: () => {
        console.log('Teams loaded successfully.');
      }
    });
  }

  loadCricketers(): void {
    this.iplService.getAllCricketers().subscribe({
      next: (response) => {
        this.cricketers = response;
      },
      error: (error) => {
        console.error('Error loading cricketers', error);
      },
      complete: () => {
        console.log('Cricketers loaded successfully.');
      }
    });
  }

  loadMatches(): void {
    this.iplService.getAllMatches().subscribe({
      next: (response) => {
        this.matches = response;
      },
      error: (error) => {
        console.error('Error loading matches', error);
      },
      complete: () => {
        console.log('Matches loaded successfully.');
      }
    });
  }

  loadTicketsBooked(): void {
    const email = this.emailForm.get('email')?.value;
    this.iplService.getBookingsByUserEmail(email).subscribe({
      next: (response) => {
        this.ticketsBooked = response;
      },
      error: (error) => {
        console.error('Error loading tickets booked', error);
      },
      complete: () => {
        console.log('Tickets booked loaded successfully.');
      }
    });
  }

  onSubmitEmail(): void {
    if (this.emailForm.valid) {
      this.loadTicketsBooked();
    }
  }

  editTeam(teamId: number) {
    this.router.navigate(['/ipl/team/edit', teamId]);
  }

  editCricketer(cricketerId: number) {
    this.router.navigate(['/ipl/cricketer/edit', cricketerId]);
  }

  editMatch(matchId: number) {
    this.router.navigate(['/ipl/match/edit', matchId]);
  }

  deleteTeam(teamId: number): void {
    if (confirm('Are you sure you want to delete this team?')) {
      this.iplService.deleteTeam(teamId).subscribe({
        next: () => {
          alert('Team deleted successfully.');
          this.loadAdminData();
        },
        error: (error) => {
          console.error('Error deleting team:', error)
          alert('Unable to delete team' );
        }
      });
    }
  }

  deleteCricketer(cricketerId: number) {
    if (confirm('Are you sure you want to delete this cricketer?')) {
      this.iplService.deleteCricketer(cricketerId).subscribe({
        next: () => {
          alert('Cricketer deleted successfully.');
          this.loadAdminData();
        },
        error: (error) => {
          console.error('Error deleting cricketer:', error)
          alert('Unable to delete cricketer' );
        }
      });
    }
  }

  deleteMatch(matchId: number) {
    if (confirm('Are you sure you want to delete this Match?')) {
      this.iplService.deleteMatch(matchId).subscribe({
        next: () => {
          alert('Match deleted successfully.');
          this.loadAdminData();
        },
        error: (error) => {
          console.error('Error deleting match:', error)
          alert('Unable to delete match' );
        }
      });
    }
  }
}
