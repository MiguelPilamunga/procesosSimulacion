package labsot.com.proyectoiib.aplication;

import labsot.com.proyectoiib.domain.Proceso;

import java.util.concurrent.TimeUnit;

public class MainProcesamiento {

    public static void main(String[] args) {
        ColaConMapa cola = new ColaConMapa();
        cola.crearProcesos(10);

        int tiempoTotal = cola.devolverTiempoFinal();

        // Procesar cada proceso y mostrar tiempo simulado
        for (int i = 1; i <= 10; i++) {
            Proceso proceso = cola.obtenerProcesoPorNumero(i);
            procesarProceso(proceso, tiempoTotal);
        }
    }

    private static void procesarProceso(Proceso proceso, int tiempoTotal) {
        int porcentajeInicial = (int) ((proceso.getTiempoLlegada() / (double) tiempoTotal) * 100);
        int porcentajeFinal = (int) ((proceso.getTiempoFinal() / (double) tiempoTotal) * 100);

        System.out.println("Procesando Proceso " + proceso.getNumeroProceso() + "...");
        for (int tiempoActual = porcentajeInicial; tiempoActual <= porcentajeFinal; tiempoActual++) {
            // Simulación de procesamiento: espera 100 milisegundos por cada unidad de tiempo
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Imprimir tiempo actual y porcentaje total del proceso actual
            System.out.printf("\rProceso %d - Tiempo actual: %d%%", proceso.getNumeroProceso(), tiempoActual);
        }

        // Imprimir información del proceso al finalizar
        System.out.println("\nProceso " + proceso.getNumeroProceso() + " completado1.");
    }
}
