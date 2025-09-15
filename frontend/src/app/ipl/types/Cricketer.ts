// export class Cricketer {
  
//     cricketerId : number;
//     teamId : number;
//     cricketerName : string;
//     age : number;
//     nationality : string;
//     experience : number;
//     role : string;
//     totalRuns : number;
//     totalWickets : number;

//     constructor(cricketerId:number , teamId:number , cricketerName:string , age:number ,nationality:string , experience:number , role:string , totalRuns:number , totalWickets:number){

//         this.cricketerId = cricketerId;
//         this.teamId = teamId;
//         this.cricketerName = cricketerName;
//         this.age = age;
//         this.nationality = nationality;
//         this.experience = experience;
//         this.role = role;
//         this.totalRuns = totalRuns;
//         this.totalWickets = totalWickets;
//     }

//     displayInfo(){
//         console.log(`Cricketer ID: ${this.cricketerId}`);
//         console.log(`Team ID: ${this.teamId}`);
//         console.log(`Cricketer Name: ${this.cricketerName}`);
//         // console.log(`Age: ${this.age}`);
//         // console.log(`Nationality: ${this.nationality}`);
//         // console.log(`Experience: ${this.experience}`);
//         // console.log(`Role: ${this.role}`);
//         // console.log(`Total Runs: ${this.totalRuns}`);
//         // console.log(`Total Wickets: ${this.totalWickets}`);
//     }
   
// }



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
 
 
