package labsot.com.proyectoiib.infrastructure.inputadapter.http;


import labsot.com.proyectoiib.domain.Proceso;
import labsot.com.proyectoiib.infrastructure.inputport.ColaInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


}