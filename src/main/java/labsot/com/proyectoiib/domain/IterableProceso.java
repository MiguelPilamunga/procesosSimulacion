package labsot.com.proyectoiib.domain;

import java.util.Iterator;

public interface IterableProceso<T extends Proceso> {
    Iterator<T> iterator();
}
