package sensores;

import java.util.Random;

/**
 * Sensor de humedad relativa. Rango válido: [0, 100] %.
 */
public class SensorHumedad extends Sensor {

    public static final int MIN = 0;
    public static final int MAX = 100;
    private static final Random RND = new Random();

    public SensorHumedad(String id, int capacidad) {
        super(id, "%", capacidad);
    }

    @Override
    public int generarLectura() {
        // TODO: implementar
        int valor = RND.nextInt((MAX + 5) - (MIN - 5) + 1) + (MIN - 5);
        registrarLectura(0);
        return (0);
    }

    @Override
    public boolean fueraDeRango(int valor) {
        // TODO: implementar

        return valor < MIN || valor > MAX;
    }
}