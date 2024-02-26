package labsot.com.proyectoiib.infrastructure.inputadapter.http;


import labsot.com.proyectoiib.domain.Proceso;
import labsot.com.proyectoiib.infrastructure.inputport.ColaInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("/api/cola")
public class ProcesosAPI {

    @Autowired
    private ColaInputPort colaInputPort;

    @PostMapping("/crear")
    public ResponseEntity<String> crearCola(@RequestParam int numeroProcesos) {
        colaInputPort.crearCola(numeroProcesos);
        return ResponseEntity.ok("Cola creada con éxito.");
    }

    @GetMapping("/procesos")
    public ResponseEntity<ArrayList<ProcesoDTO>> devolverProcesos() {

        try {
            System.out.println("test");
            return ResponseEntity.ok( colaInputPort.getListaProcesos());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/numProcesos")
    public ResponseEntity<Integer> devolverNumeroProcesos() {
        return ResponseEntity.ok(colaInputPort.devolverNumeroProcesos());
    }

    @GetMapping("/tiempoTotal")
    public ResponseEntity<Integer> devolverTiempoTotal() {
        return ResponseEntity.ok(colaInputPort.devolverTiempoTotal());
    }

    @GetMapping("/proceso")
    public ResponseEntity<Proceso> devolverProceso(@RequestParam int posicion) {
        Proceso proceso = colaInputPort.devolverProceso(posicion);
        if (proceso != null) {
            return ResponseEntity.ok(proceso);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @MessageMapping("/chat/{idRoom}")
    @SendTo("/topic/{idRoom}")
    public ResponseEntity<ArrayList<ProcesoDTO>> hello(@DestinationVariable String idRoom, @Payload Map<String, Integer> payload) {
        try {
            int numeroProcesos = payload.get("numeroProcesos");
            colaInputPort.crearCola(numeroProcesos);
            colaInputPort.getListaProcesos().forEach(System.out::println);
            return ResponseEntity.ok( colaInputPort.getListaProcesos());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

}