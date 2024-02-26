import { Component, OnInit } from '@angular/core';

import {FormGroup, FormControl, Validators, ReactiveFormsModule} from '@angular/forms';
import {ProcesoDTO} from "../../modelos/Proceso";
import {CommonModule} from "@angular/common";
import {ProcesoService} from "../../servicio/proceso.service";

@Component({
  selector: 'app-proceso',
  styleUrls: ['./proceso.component.css'],
  templateUrl: './proceso.component.html',
  imports: [
    ReactiveFormsModule,CommonModule
  ],
  standalone: true
})
export class ProcesoComponent implements OnInit {
  progress = 0;
  showExecuteButton = false;
  myForm = new FormGroup({
    numProcesses: new FormControl(0, [Validators.required]),
  });
  listaProcesos: ProcesoDTO[] = [];

  private procesoService: ProcesoService;

  constructor(procesoService: ProcesoService) {
    this.procesoService = procesoService;
  }

  ngOnInit() {
    this.procesoService.joinRoom('ABC');
    this.listenerProcesos();

  }




  async sendMessage() {
    const numProcesses = this.myForm.get('numProcesses')!.value;

    if (numProcesses !== null) {
      try {
        await this.procesoService.sendMessage('ABC', numProcesses);
        this.showExecuteButton = true;
      } catch (error) {
        console.error('Error al enviar mensaje:', error);
      }
    } else {
      console.error('El campo numProcesses no debe ser nulo');
    }
    this.myForm.get('numProcesses')!.reset();
  }


  async executeProcesses() {
    for (const proceso of this.listaProcesos) {
      const interval = proceso.tiempoRafaga / 100;
      for (let i = 0; i <= 100; i++) {
        await this.delay(interval * 1000);
        proceso.porcentajedeEjecucion = i;
      }
      proceso.porcentajedeEjecucion = 100;
    }
    this.showExecuteButton = false;
  }

  delay(ms: number) {
    return new Promise(resolve => setTimeout(resolve, ms));
  }



  listenerProcesos(): void {
    this.procesoService.getProcesoSubject().subscribe((procesoList: ProcesoDTO[]) => {
      this.listaProcesos = procesoList;
      this.listaProcesos.map((proceso) => {proceso.porcentajedeEjecucion = 0;});
    });
  }



}
