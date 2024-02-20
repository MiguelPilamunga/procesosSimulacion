package labsot.com.proyectoiib.infrastructure.inputadapter.http;

import labsot.com.proyectoiib.domain.Proceso;
import labsot.com.proyectoiib.infrastructure.inputport.ColaInputPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProcesosAPITest {

    @Autowired
    ColaInputPort colaInputPort;

    @Test
    void crearCola() {
        colaInputPort.crearCola(5);
        assertEquals(5, colaInputPort.devolverNumeroProcesos());
        for (int i = 1; i <= 5; i++) {
            Proceso proceso = colaInputPort.devolverProceso(i);
            System.out.println("Proceso " + i + ":");
            System.out.println("  Tiempo de llegada: " + proceso.getTiempoLlegada());
            System.out.println("  Tiempo de ráfaga: " + proceso.getTiempoRafaga());
            System.out.println("  Tiempo de retorno: " + proceso.getTiempoRetorno());
            System.out.println("  Tiempo de espera: " + proceso.getTiempoEspera());
            System.out.println("  Tiempo final: " + proceso.getTiempoFinal());
        }

        System.out.println("Tiempo total: " + colaInputPort.devolverTiempoTotal());
    }
    @Test
    void devolverProceso() {

    }
}