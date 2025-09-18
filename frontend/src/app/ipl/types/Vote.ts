export class Vote {
 
    voteId:number;
    email:string;
    category:string;
    cricketerId:number;
    teamId:number;
 
   
 
 
    constructor( voteId:number,
        email:string,
        category:string,
        cricketerId:number,
        teamId:number){
        this.voteId=voteId;
          this.email=email;
          this.category=category;
          this.cricketerId=cricketerId;
          this.teamId=teamId;
 
         
 
         
      }
 
      displayInfo(){
          console.log(`Vote ID: ${this.voteId}`);
          console.log(`Email: ${this.email}`);
      }
 
 
}