package labsot.com.proyectoiib.infrastructure.inputadapter.http;

import labsot.com.proyectoiib.domain.Proceso;
import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class ProcesoDTO {
        private int numeroProceso;
        private int tiempoLlegada;
        private int tiempoRafaga;
        private int tiempoRetorno;
        private int tiempoEspera;
        private int tiempoFinal;
}
