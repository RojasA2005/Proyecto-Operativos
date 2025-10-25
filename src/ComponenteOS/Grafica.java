/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ComponenteOS;

import javax.swing.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.plot.PlotOrientation;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Massi
 */
//x = cantidad de tiempo abajo, y = procesos completados 

public class Grafica extends JFrame {

    public enum TimeUnit {
        HOURS("Horas"), SECONDS("Segundos"), MILLISECONDS("Milisegundos"), CYCLES("Ciclos");

        private final String label;
        TimeUnit(String label) { this.label = label; }
        public String getLabel() { return label; }
    }

    private DefaultCategoryDataset dataset;
    private TimeUnit currentUnit;
    private List<Integer> completedProcessesHistory;
    private List<Integer> timeHistory;
    private String title;

    public Grafica(String title, TimeUnit unidad) {
        super(title);
        this.title = title;
        this.currentUnit = unidad;
        this.completedProcessesHistory = new ArrayList<>();
        this.timeHistory = new ArrayList<>();
        this.dataset = new DefaultCategoryDataset();
        
        initializeChart();
    }

    private void initializeChart() {
        // Crear el gráfico de líneas
        JFreeChart chart = ChartFactory.createLineChart(
            title,
            "Tiempo (" + currentUnit.getLabel() + ")",
            "Procesos Completados",
            dataset,
            PlotOrientation.VERTICAL,
            true,  // incluir leyenda
            true,  // tooltips
            false  // URLs
        );

        // Personalizar el gráfico
        chart.setBackgroundPaint(java.awt.Color.WHITE);
        
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 500));
        
        setContentPane(chartPanel);
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }


    public void agregarPuntoDatos(int procesosCompletados, int tiempo) {
        completedProcessesHistory.add(procesosCompletados);
        timeHistory.add(tiempo);
        
        String tiempoStr = convertirTiempo(tiempo, currentUnit);
        dataset.addValue(procesosCompletados, "Procesos Completados", tiempoStr);
    }


    public void actualizarDesdeArray(int[] procesosCompletados) {
        dataset.clear();
        completedProcessesHistory.clear();
        timeHistory.clear();
        
        for (int i = 0; i < procesosCompletados.length; i++) {
            agregarPuntoDatos(procesosCompletados[i], i);
        }
    }

    public void actualizarTiempoReal(int procesosCompletadosAcumulados, int tiempoActual) {
        agregarPuntoDatos(procesosCompletadosAcumulados, tiempoActual);
    }


    public void cambiarUnidadTiempo(TimeUnit nuevaUnidad) {
        this.currentUnit = nuevaUnidad;
        
        // Reconstruir el dataset con la nueva unidad
        dataset.clear();
        for (int i = 0; i < completedProcessesHistory.size(); i++) {
            int tiempo = timeHistory.get(i);
            int procesos = completedProcessesHistory.get(i);
            String tiempoStr = convertirTiempo(tiempo, currentUnit);
            dataset.addValue(procesos, "Procesos Completados", tiempoStr);
        }
        
        // Actualizar el título del eje X
        initializeChart();
    }

    private String convertirTiempo(int tiempo, TimeUnit unidad) {
        switch (unidad) {
            case HOURS: 
                return (tiempo / 3600) + "h"; // Asumiendo que tiempo está en segundos
            case SECONDS: 
                return tiempo + "s";
            case MILLISECONDS: 
                return (tiempo * 1000) + "ms"; // Asumiendo que tiempo está en segundos
            case CYCLES:
                return "Ciclo " + tiempo;
            default: 
                return String.valueOf(tiempo);
        }
    }


    public static void crearYMostrar(int[] procesosCompletados, TimeUnit unidad) {
        SwingUtilities.invokeLater(() -> {
            Grafica grafica = new Grafica("Procesos Completados vs Tiempo", unidad);
            grafica.actualizarDesdeArray(procesosCompletados);
            grafica.setVisible(true);
        });
    }

 
    public static void crearDesdeHistorico(List<Integer> historicoCompletados, TimeUnit unidad) {
        int[] arrayCompletados = new int[historicoCompletados.size()];
        for (int i = 0; i < historicoCompletados.size(); i++) {
            arrayCompletados[i] = historicoCompletados.get(i);
        }
        crearYMostrar(arrayCompletados, unidad);
    }
}

