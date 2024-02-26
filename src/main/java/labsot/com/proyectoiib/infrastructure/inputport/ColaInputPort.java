package labsot.com.proyectoiib.infrastructure.inputport;

import labsot.com.proyectoiib.domain.Proceso;
import labsot.com.proyectoiib.infrastructure.inputadapter.http.ProcesoDTO;

import java.util.ArrayList;
import java.util.List;


public interface ColaInputPort {
    void crearCola(int numeroProcesos);

    int devolverNumeroProcesos();

    int devolverTiempoTotal();

    Proceso devolverProceso(int posicion);

    ArrayList<ProcesoDTO> getListaProcesos();
}
