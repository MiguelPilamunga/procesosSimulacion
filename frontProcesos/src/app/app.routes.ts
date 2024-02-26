import { Routes } from '@angular/router';
import {ProcesoComponent} from "./componente/proceso/proceso.component";

export const routes: Routes = [

  { path: 'proceso/:procesoId', component: ProcesoComponent },
];
