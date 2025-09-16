import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { ReactiveFormsModule } from "@angular/forms";
@Component({
  selector: 'app-teamcreate',
  templateUrl: './teamcreate.component.html',
  styleUrls: ['./teamcreate.component.scss'] 
})

export class TeamCreateComponent implements OnInit {
//   team: any = {
//     teamId: '',
//     teamName: '',
//     location: '',
//     ownerName: '',
//     establishmentYear: ''
//   };

  teamForm: FormGroup;
  successMessage: string | null = null;
  errorMessage: string | null = null;

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.teamForm = this.fb.group({
      teamId: [null, [Validators.required, Validators.minLength(1)]],
      teamName: ['', [Validators.required, Validators.minLength(2)]],
      location: ['', Validators.required],
      ownerName: ['', [Validators.required, Validators.minLength(2)]],
      establishmentYear: [null, Validators.required]
    });
  }

  onSubmit(): void {
    if (this.teamForm.valid) {
      this.successMessage = 'Team has been successfully created!';
      this.errorMessage = null;
      console.log('Team Created: ', this.teamForm.value);
    } else {
      this.errorMessage = 'Please fill out all required fields correctly.';
      this.successMessage = null;
    }
  }

  resetForm(): void {
    this.teamForm.reset({
      teamId: null,
      teamName: '',
      location: '',
      ownerName: '',
      establishmentYear: new Date().getFullYear()
    });
  }
}
