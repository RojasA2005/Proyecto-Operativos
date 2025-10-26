/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ComponenteOS;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 *
 * @author Massi
 */
/*
public class Logger {
    private BufferedWriter writer; 
    private DateTimeFormatter formatter; 
    private static Logger instance; 
    
    private Logger(){
        try {
            this.writer = new BufferedWriter (new FileWriter("Simulacion_log.txt", true));
            this.formatter = DateTimeFormatter.ofPattern("E MMM DD YYYY HH:mm:ss.SSS"); 
             LogEvent("Simulación iniciada");
        }   catch (IOException e) {
                e.printStackTrace();
        }
}
    public static synchronized Logger getInstance(); {
        if (instance == null) {
            instance = new Logger(); 
        }
        return instance(); 
    }
    public synchronized void LogEvent (String message) {
        try {
            String timestamp = LocalDateTime.now().format(formatter); 
            writer.write(String.format("[%s] %s%n", timestamp, message)); 
            writer.flush(); 
        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }
    public void logProcesoSeleccionado(int id, String nombre, String algoritmo) {
        LogEvent(String.format("Proceso %d (%s) seleccionado usando el algoritmo %s", id, nombre, algoritmo));
    }

    public void logCambioEstado(int id, String nombre, String estadoAnterior, String estadoNuevo) {
        LogEvent(String.format("Proceso %d (%s) cambió de estado: %s → %s", id, nombre, estadoAnterior, estadoNuevo));
    }

    public void logProcesoCompletado(int id, String nombre, int rafagaTotal) {
        LogEvent(String.format("Proceso %d (%s) completó su ejecución. Ráfaga total: %d", id, nombre, rafagaTotal));
    }

    public void logInicioIO(int id, String nombre) {
        LogEvent(String.format("Proceso %d (%s) inició operación de E/S", id, nombre));
    }

    public void logFinIO(int id, String nombre) {
        LogEvent(String.format("Proceso %d (%s) finalizó operación de E/S", id, nombre));
    }

    public void logProcesoExpropiado(int id, String nombre, String razon) {
        LogEvent(String.format("Proceso %d (%s) fue expropiado. Razón: %s", id, nombre, razon));
    }

    public void logCPUInactiva(int disponibles, int total) {
        LogEvent(String.format("CPU inactiva. %d de %d disponibles", disponibles, total));
    }

    public void logCambioQuantum(String quantumAnterior, String quantumNuevo) {
        LogEvent(String.format("Quantum cambiado de %s a %s", quantumAnterior, quantumNuevo));
    }

    public void logQuantumExpirado(int id, String nombre) {
        LogEvent(String.format("Proceso %d (%s) agotó su quantum, movido a la cola de listos", id, nombre));
    }

    public void logProcesoGenerado(int idPadre, String nombrePadre, int idHijo, String nombreHijo) {
        LogEvent(String.format("Proceso %d (%s) generó al proceso %d (%s)", idPadre, nombrePadre, idHijo, nombreHijo));
    }
    
    public void close() {
            try{
                LogEvent("SIMULACION TERMINADA\n");
                if (writer != null) {
                    writer.close(); 
                } 
            }catch (IOException e) {
                e.printStackTrace(); 
            }
    }
}

*/
