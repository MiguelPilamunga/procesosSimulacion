package labsot.com.proyectoiib.aplication;

import labsot.com.proyectoiib.domain.Proceso;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ColaConMapa {
    private Map<Integer, Proceso> procesosMap = new HashMap<>();
    private Random rnd = new Random();
    private int tiempoFinal;

    public void calcularFinal(int tiempoFinalAnterior, Proceso nuevo) {
        nuevo.setTiempoFinal(nuevo.getTiempoRafaga() + tiempoFinalAnterior);
    }

    public void calcularRetorno(Proceso nuevo) {
        nuevo.setTiempoRetorno(nuevo.getTiempoFinal() - nuevo.getTiempoLlegada());
    }

    public void calcularEspera(Proceso nuevo) {
        nuevo.setTiempoEspera(nuevo.getTiempoRetorno() - nuevo.getTiempoRafaga());
    }

    public void crearProcesos(int numeroProcesos) {
        for (int i = 0; i <= numeroProcesos; i++) {
            Proceso nuevo = new Proceso();
            if (i == 0) {
                nuevo.setNumeroProceso(i + 1);
                nuevo.setTiempoLlegada(0, 0);
                nuevo.setTiempoRafaga(rnd.nextInt(9) + 1);
                calcularFinal(0, nuevo);
                calcularRetorno(nuevo);
                calcularEspera(nuevo);
                tiempoFinal = nuevo.getTiempoFinal();
                procesosMap.put(nuevo.getNumeroProceso(), nuevo);
            } else {
                Proceso anterior = procesosMap.get(i);
                nuevo.setNumeroProceso(i + 1);
                nuevo.setTiempoRafaga(rnd.nextInt(5) + 1);
                int a = rnd.nextInt(anterior.getTiempoRafaga());
                nuevo.setTiempoLlegada(a, anterior.getTiempoLlegada());
                calcularFinal(anterior.getTiempoFinal(), nuevo);
                calcularRetorno(nuevo);
                calcularEspera(nuevo);
                tiempoFinal = nuevo.getTiempoFinal();
                procesosMap.put(nuevo.getNumeroProceso(), nuevo);
            }
        }
    }

    public Proceso obtenerProcesoPorNumero(int numeroProceso) {
        return procesosMap.get(numeroProceso);
    }

    public int devolverTiempoFinal() {
        return tiempoFinal;
    }

}


