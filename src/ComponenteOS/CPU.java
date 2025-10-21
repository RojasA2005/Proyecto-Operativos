/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ComponenteOS;

import EDD.Lista;
import EDD.Nodo;

/**
 *
 * @author Andrés
 */
public class CPU extends Thread{
    private int Time_per_cycle;
    private int Memoria;
    ShortScheduler Scheduler;
    LongScheduler Long;
    Interrupt Pausa;
    int procesoid;
    
    
    public CPU(int Time, int Memoria){
        this.Time_per_cycle = Time;
        this.Pausa = new Interrupt();
        this.Memoria = Memoria;
        this.Scheduler = new ShortScheduler(Time, this.Pausa, Memoria);
        this.Long = new LongScheduler();
        this.procesoid = 0;
    }
    
    @Override
    public void run(){
        
    }
    
    public void CrearProceso(boolean isCPUBound, String name, int ciclos, int ciclos_para_IO, int ciclos_por_interrupcion){
        PCB Pn = new PCB(this.procesoid, name, ciclos, isCPUBound, 0, ciclos_para_IO, ciclos_por_interrupcion);
        this.procesoid++;
        this.Long.addNuevo(Pn);
    }
    
    public void MoverNewAReady(){
        Lista l = this.Long.vaciar();
        Nodo n = l.getFirst();
        while(n != null){
            this.Scheduler.add(n.getData());
            n = n.getpNext();
        }
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
