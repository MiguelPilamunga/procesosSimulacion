package labsot.com.proyectoiib.domain;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.Iterator;

@Data
@Builder
@ToString(exclude = {"siguienteProceso", "anteriorProceso"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Proceso implements Iterable<Proceso> {
    private int numeroProceso;
    private int tiempoLlegada;
    private int tiempoRafaga;
    private int tiempoRetorno;
    private int tiempoEspera;
    private int tiempoFinal;
    private Proceso siguienteProceso;
    private Proceso anteriorProceso;

    public void setTiempoLlegada(int tiempoLlegada, int llegadaAnterior) {
        this.tiempoLlegada = Math.max(tiempoLlegada, llegadaAnterior + 1);
    }

    @Override
    public Iterator<Proceso> iterator() {
        return new Iterator<Proceso>() {
            private Proceso actual = Proceso.this;

            @Override
            public boolean hasNext() {
                return actual != null;
            }

            @Override
            public Proceso next() {
                Proceso siguiente = actual;
                actual = actual.siguienteProceso;
                return siguiente;
            }

            // Método para iterar en sentido inverso
            public boolean hasPrevious() {
                return actual != null;
            }

            public Proceso previous() {
                Proceso anterior = actual;
                actual = actual.anteriorProceso;
                return anterior;
            }
        };
    }
}
