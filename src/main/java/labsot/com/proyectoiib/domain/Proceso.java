package labsot.com.proyectoiib.domain;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = "siguienteProceso")
public class Proceso {
    private int numeroProceso;
    private int tiempoLlegada;
    private int tiempoRafaga;
    private int tiempoRetorno;
    private int tiempoEspera;
    private int tiempoFinal;
    private Proceso siguienteProceso;

    public void setTiempoLlegada(int tiempoLlegada, int llegadaAnterior) {
        this.tiempoLlegada = Math.max(tiempoLlegada, llegadaAnterior + 1);
    }
}