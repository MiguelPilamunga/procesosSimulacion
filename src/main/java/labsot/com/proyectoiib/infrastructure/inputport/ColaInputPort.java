package labsot.com.proyectoiib.infrastructure.inputport;

import labsot.com.proyectoiib.domain.Proceso;


public interface ColaInputPort {
    void crearCola(int numeroProcesos);

    int devolverNumeroProcesos();

    int devolverTiempoTotal();

    Proceso devolverProceso(int posicion);
}
