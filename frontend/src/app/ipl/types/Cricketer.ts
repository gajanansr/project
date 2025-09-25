import { Team } from './Team';

export class Cricketer {
    cricketerId: number;
    cricketerName: string;
    age: number;
    nationality: string;
    experience: number;
    role: string;
    totalRuns: number;
    totalWickets: number;
    team: Team;  // Establish ManyToOne relationship

    constructor(
        cricketerId: number,
        cricketerName: string,
        age: number,
        nationality: string,
        experience: number,
        role: string,
        totalRuns: number,
        totalWickets: number,
        team: Team
    ) {
        this.cricketerId = cricketerId;
        this.cricketerName = cricketerName;
        this.age = age;
        this.nationality = nationality;
        this.experience = experience;
        this.role = role;
        this.totalRuns = totalRuns;
        this.totalWickets = totalWickets;
        this.team = team;  // Link cricketer to a team
    }

    displayInfo() {
        console.log(`Cricketer ID: ${this.cricketerId}`);
        console.log(`Team: ${this.team.teamName}`);
        console.log(`Cricketer Name: ${this.cricketerName}`);
    }
}
