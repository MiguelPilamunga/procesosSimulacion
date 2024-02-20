import { Component, OnInit } from '@angular/core';
import {FormGroup, FormControl, Validators, ReactiveFormsModule} from '@angular/forms';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {CommonModule} from "@angular/common";

@Component({
  selector: 'app-proceso-form',
  templateUrl: './proceso-form.component.html',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    CommonModule,
    HttpClientModule
  ],
  styleUrls: ['./proceso-form.component.css']
})
export class ProcesoFormComponent implements OnInit {
  numeroProcesosForm: FormGroup;

  constructor(private http: HttpClient) {
    this.numeroProcesosForm = new FormGroup({
      numeroProcesos: new FormControl('', [
        Validators.required,
        Validators.min(1),
      ])
    });
  }

  ngOnInit(): void {}

  onSubmit() {
    if (this.numeroProcesosForm.valid) {
      const numeroProcesos = this.numeroProcesosForm.value.numeroProcesos;
      this.http.post('http://localhost:8080/api/procesos', {numeroProcesos}).subscribe(
        (response) => {
          console.log(response);
        },
        (error) => {
          console.log(error);
        }
      );
    }
  }
}
