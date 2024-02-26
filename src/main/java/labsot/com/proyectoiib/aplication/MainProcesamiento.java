package labsot.com.proyectoiib.aplication;

import labsot.com.proyectoiib.domain.Proceso;
import labsot.com.proyectoiib.infrastructure.inputport.ColaInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class MainProcesamiento {

    public static void main(String[] args) {
        // Crear una instancia de ColaInputPort (puede ser ColaService)

        ColaInputPort colaInputPort = new ColaService();

        // Crear una cola con un número específico de procesos
        int numeroProcesos = 5;
        colaInputPort.crearCola(numeroProcesos);

        // Obtener el número de procesos en la cola
        int numProcesosEnCola = colaInputPort.devolverNumeroProcesos();
        System.out.println("Número de procesos en la cola: " + numProcesosEnCola);

        // Obtener el tiempo total de la cola
        int tiempoTotal = colaInputPort.devolverTiempoTotal();
        System.out.println("Tiempo total de la cola: " + tiempoTotal);

        // Obtener una lista de procesos

    }

}
