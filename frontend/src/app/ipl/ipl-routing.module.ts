import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { CricketerCreateComponent } from "./components/cricketercreate/cricketercreate.component";
import { CricketerEditComponent } from "./components/cricketeredit/cricketeredit.component";
import { DashboardComponent } from "./components/dashboard/dashboard.component";
import { MatchCreateComponent } from "./components/matchcreate/matchcreate.component";
import { MatchEditComponent } from "./components/matchedit/matchedit.component";
import { TeamCreateComponent } from "./components/teamcreate/teamcreate.component";
import { TeamEditComponent } from "./components/teamedit/teamedit.component";
import { TicketBookingComponent } from "./components/ticketbooking/ticketbooking.component";
import { VoteComponent } from "./components/vote/vote.component";

const routes: Routes = [
  { path: "", component: DashboardComponent },
  { path: "team", component: TeamCreateComponent },
  { path: "match", component: MatchCreateComponent },
  { path: "cricketer", component: CricketerCreateComponent },
  { path: "team/edit", component: TeamEditComponent },
  { path: "match/edit", component: MatchEditComponent },
  { path: "cricketer/edit", component: CricketerEditComponent },
  { path: "vote", component: VoteComponent },
  { path: "ticketbooking", component: TicketBookingComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class IplRoutingModule {}