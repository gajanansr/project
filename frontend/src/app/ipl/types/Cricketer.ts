export class Cricketer {
 
    cricketerId:number;
    teamId:number;
    cricketerName:string;
    age:number;
    nationality:string;
    experience:number;
    role:string;
    totalRuns:number;
    totalWickets:number;
 
 
    constructor(cricketerId:number,
    teamId:number,
    cricketerName:string,
    age:number,
    nationality:string,
    experience:number,
    role:string,
    totalRuns:number,
    totalWickets:number){
        this.cricketerId=cricketerId;
          this.teamId=teamId;
          this.cricketerName=cricketerName;
          this.age=age;
          this.nationality=nationality;
          this.experience=experience;
          this.role=role;
          this.totalRuns=totalRuns;
          this.totalWickets=totalWickets;
 
         
      }
 
      displayInfo(){
          console.log(`Cricketer ID: ${this.cricketerId}`);
          console.log(`Team ID: ${this.teamId}`);
          console.log(`Cricketer Name: ${this.cricketerName}`);
      }
   
}
 
 
