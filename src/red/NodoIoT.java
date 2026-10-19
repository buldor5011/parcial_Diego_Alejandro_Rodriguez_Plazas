package red;

import sensores.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Nodo IoT que COMPONE sensores.
 * Los sensores se crean dentro del nodo (composición real).
 */
public class NodoIoT {

    private String id;
    private ArrayList<Sensor> sensores;

    public NodoIoT(String id) {
        this.id = id;
        this.sensores = new ArrayList<>();
    }

    /**
     * Crea y agrega un sensor del tipo indicado dentro del nodo.
     * Tipo: "TEMPERATURA", "HUMEDAD", "LUMINOSIDAD".
     * Retorna el sensor creado, o null si el tipo es inválido.
     */
    public Sensor agregarSensor(String tipo, String idSensor, int capacidad) {
        // TODO: implementar (polimorfismo + composición)
        if (tipo == null || idSensor == null || capacidad <= 0) {
            return null;
        }

        Sensor sensor;
        switch (tipo.toUpperCase()) {
            case "TEMPERATURA":
                sensor = new SensorTemperatura(idSensor, capacidad);
                break;
            case "HUMEDAD":
                sensor = new SensorHumedad(idSensor, capacidad);
                break;
            case "LUMINOSIDAD":
                sensor = new SensorLuminosidad(idSensor, capacidad);
                break;
            default:
                return null;
        }

        sensores.add(sensor);
        return sensor;
    }


    /**
     * Promedio de temperatura del nodo. Retorna 0 si no hay sensores de temperatura.
     * Pista: usar instanceof para filtrar.
     */
    public double promedioTemperatura() {
        // TODO: implementar
        double suma = 0;
        int cantidad = 0;

        for (Sensor sensor : sensores) {
            if (sensor instanceof SensorTemperatura
                    && sensor.getCantidadLecturas() > 0) {
                suma += sensor.promedioUltimasLecturas();
                cantidad++;
            }
        }

        return cantidad == 0 ? 0 : suma / cantidad;
    }

    /**
     * Lista de sensores cuya última lectura está fuera de rango.
     */
    public List<Sensor> sensoresFueraDeRango() {
        // TODO: implementar
        List<Sensor> fueraDeRango = new ArrayList<>();

        for (Sensor sensor : sensores) {
            int cantidad = sensor.getCantidadLecturas();
            if (cantidad > 0) {
                int indiceUltima = (sensor.getIndiceActual()
                        - 1 + sensor.getUltimasLecturas().length)
                        % sensor.getUltimasLecturas().length;
                int ultimaLectura = sensor.getUltimasLecturas()[indiceUltima];
                if (sensor.fueraDeRango(ultimaLectura)) {
                    fueraDeRango.add(sensor);
                }
            }
        }

        return fueraDeRango;
    }

    public String getId() { return id; }
    public ArrayList<Sensor> getSensores() { return sensores; }
}