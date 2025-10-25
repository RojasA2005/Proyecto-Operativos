/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ComponenteOS;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Massi
 */
public class CSVConfig {
    
    public class CSV {
    private static final String CONFIG_FILE = "simulacion_config.csv"; 
    private Map<String, String> configMap; 
    
    public CSV() {
        this.configMap = new HashMap<>(); 
    }
    
    public void cargarConfiguracion() throws IOException{
        configMap.clear(); 
        
        try (BufferedReader reader = new BufferedReader(new FileReader(CONFIG_FILE))){
            String line; 
            while ((line=reader.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("#")){
                    continue; 
                }
                    
                String[] parts = line.split(",", 2); 
                if (parts.length == 2) {
                    String clave = parts [0].trim();
                    String valor = parts[1].trim();
                    configMap.put(clave, valor); 
                }
            }
            System.out.println("Configuracion cargada desde: " + CONFIG_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("Archivo de configuracion no encontrado. Se usaran valores por defecto");
            crearConfiguracionPorDefecto(); 
        } catch (IOException e) {
            System.err.println("Error al leer: " + e.getMessage());
        }
    }
    
    public void guardarConfiguracion() throws IOException{
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CONFIG_FILE))){
            writer.write("# Configuracion del Simulador de Planificación");
            writer.newLine(); 
            writer.write("# Formato: clave, valor"); 
            writer.newLine(); 
            writer.newLine(); 
            
            for (Map.Entry<String, String> entry : configMap.entrySet()){
                writer.write(entry.getKey() + "," + entry.getValue());
                writer.newLine();
        }
        }catch (IOException e){
            
            System.err.println("Error al guardar configuración" + e.getMessage());
        }
    }
    
    public void crearConfiguracionPorDefecto() throws IOException{
        configMap.put("cycle_time_ms", "1000");
        configMap.put("memory_size_mb", "1024");
        configMap.put("scheduling_algorithm", "0");
        configMap.put("default_process_cycles", "50");
        configMap.put("io_cycles", "5");
        configMap.put("interrupt_cycles", "10");
        configMap.put("quantum_time", "5");
        configMap.put("auto_save_log", "true");
        
        guardarConfiguracion();
    }
    
    public int getInt(String clave, int valorPorDefecto){
        try {
            return Integer.parseInt(configMap.getOrDefault(clave, String.valueOf(valorPorDefecto)));
        } catch (NumberFormatException e) {
            return valorPorDefecto;
        }
    }
    public long getLong(String clave, long valorPorDefecto) {
        try {
            return Long.parseLong(configMap.getOrDefault(clave, String.valueOf(valorPorDefecto)));
        } catch (NumberFormatException e) {
            return valorPorDefecto;
        }
    }
    
    
}
}
