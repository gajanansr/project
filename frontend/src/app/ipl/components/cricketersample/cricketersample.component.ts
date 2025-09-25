import { Component } from "@angular/core";
import { Cricketer } from "../../types/Cricketer";

@Component({
    selector: 'app-cricketersample',
    standalone: true,
    imports: [],
    templateUrl: './cricketersample.component.html',
    styleUrls: ['./cricketersample.component.scss'] 
  })
export class CricketerSampleComponent {
    cricketer: Cricketer = new Cricketer(1, 1, "Virat", 32, "Indian", 14, "Batsman", 580, 50);
}
