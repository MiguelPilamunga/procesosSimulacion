export interface ProcesoDTO {
  numeroProceso: number;
  tiempoLlegada: number;
  tiempoRafaga: number;
  tiempoRetorno?: number;
  tiempoEspera?: number;
  tiempoFinal?: number;
  porcentajedeEjecucion: number;
}
