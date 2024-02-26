package labsot.com.proyectoiib.aplication;

import labsot.com.proyectoiib.domain.Proceso;
import labsot.com.proyectoiib.infrastructure.inputadapter.http.ProcesoDTO;
import labsot.com.proyectoiib.infrastructure.inputport.ColaInputPort;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ColaService implements ColaInputPort {
    private Proceso cabeza;
    private Proceso proceso;

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
    @Override
    public void crearCola(int numeroProcesos) {
        if (numeroProcesos <= 0) {
            throw new IllegalArgumentException("El número de procesos debe ser mayor a 0.");
        }

        cabeza = Proceso.builder().build();
        proceso = cabeza;

        for (int i = 0; i < numeroProcesos; i++) {
            Proceso nuevoProceso = Proceso.builder()
                    .numeroProceso(i + 1)
                    .tiempoRafaga(i == 0 ? rnd.nextInt(9) + 1 : rnd.nextInt(5) + 1)
                    .tiempoLlegada(i == 0 ? 0 : rnd.nextInt(proceso.getTiempoRafaga()))
                    .build();

            calcularFinal(proceso.getTiempoFinal(), nuevoProceso);
            calcularRetorno(nuevoProceso);
            calcularEspera(nuevoProceso);
            tiempoFinal = nuevoProceso.getTiempoFinal();

            proceso.setSiguienteProceso(nuevoProceso);
            nuevoProceso.setAnteriorProceso(proceso);
            proceso = nuevoProceso;
        }
    }

    @Override
    public int devolverNumeroProcesos() {
        int contador = 0;
        Iterator<Proceso> iterator = getIterator();

        while (iterator.hasNext()) {
            contador++;
            iterator.next();
        }

        return contador;
    }

    @Override
    public int devolverTiempoTotal() {
        int tiempoTotal = 0;
        Iterator<Proceso> iterator = getIterator();

        while (iterator.hasNext()) {
            tiempoTotal += iterator.next().getTiempoRafaga();
        }

        return tiempoTotal;
    }

    @Override
    public Proceso devolverProceso(int posicion) {
        Iterator<Proceso> iterator = getIterator();

        for (int i = 0; i < posicion; i++) {
            if (!iterator.hasNext()) {
                throw new IndexOutOfBoundsException("Posición fuera de rango.");
            }
            iterator.next();
        }

        return iterator.next();
    }

    @Override
    public ArrayList<ProcesoDTO> getListaProcesos() {
        ArrayList<ProcesoDTO> listaProcesos = new ArrayList<>();
        Iterator<Proceso> iterator = getIterator();
        Proceso proceso = iterator.next();
        while (iterator.hasNext()) {
            proceso = iterator.next();
            listaProcesos.add(ProcesoDTO.builder()
                    .numeroProceso(proceso.getNumeroProceso())
                    .tiempoRafaga(proceso.getTiempoRafaga())
                    .tiempoLlegada(proceso.getTiempoLlegada())
                    .tiempoRetorno(proceso.getTiempoRetorno())
                    .tiempoEspera(proceso.getTiempoEspera())
                    .tiempoFinal(proceso.getTiempoFinal())
                    .build());

        }
        return listaProcesos;
    }

    private Iterator<Proceso> getIterator() {
        return new Iterator<Proceso>() {
            private Proceso actual = cabeza;

            @Override
            public boolean hasNext() {
                return actual != null;
            }

            @Override
            public Proceso next() {
                Proceso siguiente = actual;
                actual = actual.getSiguienteProceso();
                return siguiente;
            }

            public boolean hasPrevious() {
                return actual != null;
            }

            public Proceso previous() {
                Proceso anterior = actual;
                actual = actual.getAnteriorProceso();
                return anterior;
            }
        };
    }
}
