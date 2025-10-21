/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ComponenteOS;

/**
 *
 * @author Andrés
 */
public class CPU extends Thread{
    private int Time_per_cycle;
    private boolean interrupt;
    private int Memoria;
    
    
    public CPU(int Time){
        this.Time_per_cycle = Time;
    }
    
    @Override
    public void run(){
        
    }

    /**
     * @return the Time_per_cycle
     */
    public int getTime_per_cycle() {
        return Time_per_cycle;
    }

    /**
     * @param Time_per_cycle the Time_per_cycle to set
     */
    public void setTime_per_cycle(int Time_per_cycle) {
        this.Time_per_cycle = Time_per_cycle;
    }

    /**
     * @return the interrupt
     */
    public boolean isInterrupt() {
        return interrupt;
    }

    /**
     * @return the Memoria
     */
    public int getMemoria() {
        return Memoria;
    }

    /**
     * @param Memoria the Memoria to set
     */
    public void setMemoria(int Memoria) {
        this.Memoria = Memoria;
    }
    
}
