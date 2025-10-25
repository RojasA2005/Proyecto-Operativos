/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ComponenteOS;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Massi
 */
public class CargarCSV {
    
    private static final String PROCESS_FILE = "predefined_processes.csv";
    
    public static class ProcessDefinition {
        public String nombre;
        public int ciclosTotales;
        public boolean esCPUBound;
        public int ciclosParaIO;
        public int ciclosPorInterrupcion;
        
        public ProcessDefinition(String nombre, int ciclosTotales, boolean esCPUBound, 
                               int ciclosParaIO, int ciclosPorInterrupcion) {
            this.nombre = nombre;
            this.ciclosTotales = ciclosTotales;
            this.esCPUBound = esCPUBound;
            this.ciclosParaIO = ciclosParaIO;
            this.ciclosPorInterrupcion = ciclosPorInterrupcion;
        }
    }
    
    /**
     * Carga procesos predefinidos desde CSV
     */
    public List<ProcessDefinition> cargarProcesosPredefinidos() {
        List<ProcessDefinition> procesos = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(PROCESS_FILE))) {
            String line;
            boolean primeraLinea = true;
            
            while ((line = reader.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false; // Saltar encabezados
                    continue;
                }
                
                if (line.trim().isEmpty() || line.startsWith("#")) {
                    continue;
                }
                
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    String nombre = parts[0].trim();
                    int ciclosTotales = Integer.parseInt(parts[1].trim());
                    boolean esCPUBound = Boolean.parseBoolean(parts[2].trim());
                    int ciclosParaIO = Integer.parseInt(parts[3].trim());
                    int ciclosPorInterrupcion = Integer.parseInt(parts[4].trim());
                    
                    procesos.add(new ProcessDefinition(nombre, ciclosTotales, esCPUBound, 
                                                     ciclosParaIO, ciclosPorInterrupcion));
                }
            }
            System.out.println("Procesos predefinidos cargados: " + procesos.size());
        } catch (FileNotFoundException e) {
            System.out.println("Archivo de procesos no encontrado: " + PROCESS_FILE);
            crearProcesosEjemplo();
        } catch (IOException e) {
            System.err.println("Error al cargar procesos: " + e.getMessage());
        }
        
        return procesos;
    }
    
    /**
     * Guarda procesos en archivo CSV
     */
    public void guardarProcesosPredefinidos(List<ProcessDefinition> procesos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PROCESS_FILE))) {
            // Escribir encabezados
            writer.write("nombre,ciclos_totales,es_cpu_bound,ciclos_para_io,ciclos_por_interrupcion");
            writer.newLine();
            
            for (ProcessDefinition proceso : procesos) {
                writer.write(String.format("%s,%d,%b,%d,%d",
                    proceso.nombre,
                    proceso.ciclosTotales,
                    proceso.esCPUBound,
                    proceso.ciclosParaIO,
                    proceso.ciclosPorInterrupcion
                ));
                writer.newLine();
            }
            
            System.out.println("Procesos guardados en: " + PROCESS_FILE);
        } catch (IOException e) {
            System.err.println("Error al guardar procesos: " + e.getMessage());
        }
    }
    
    /**
     * Crea un archivo de ejemplo con procesos predefinidos
     */
    private void crearProcesosEjemplo() {
        List<ProcessDefinition> procesosEjemplo = new ArrayList<>();
        procesosEjemplo.add(new ProcessDefinition("Navegador", 100, false, 8, 15));
        procesosEjemplo.add(new ProcessDefinition("EditorTexto", 80, true, 0, 0));
        procesosEjemplo.add(new ProcessDefinition("Reproductor", 120, false, 10, 20));
        procesosEjemplo.add(new ProcessDefinition("Compilador", 200, true, 5, 10));
        procesosEjemplo.add(new ProcessDefinition("Juego", 150, false, 12, 25));
        
        guardarProcesosPredefinidos(procesosEjemplo);
        System.out.println("Archivo de procesos ejemplo creado");
    }
}
