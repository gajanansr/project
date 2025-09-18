import { Team } from './Team';

export class Match {
    matchId: number;
    firstTeam: Team; // ManyToOne relationship with Team
    secondTeam: Team; // ManyToOne relationship with Team
    matchDate: Date;
    venue: string;
    result: string;
    status: string;
    winnerTeam: Team; // ManyToOne relationship with Team

    constructor(
        matchId: number,
        firstTeam: Team,
        secondTeam: Team,
        matchDate: Date,
        venue: string,
        result: string,
        status: string,
        winnerTeam: Team
    ) {
        this.matchId = matchId;
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
        this.matchDate = matchDate;
        this.venue = venue;
        this.result = result;
        this.status = status;
        this.winnerTeam = winnerTeam;
    }

    displayInfo() {
        console.log(`Match ID: ${this.matchId}`);
        console.log(`First Team: ${this.firstTeam.teamName}`);
        console.log(`Second Team: ${this.secondTeam.teamName}`);
        console.log(`Winner: ${this.winnerTeam.teamName}`);
    }
}
