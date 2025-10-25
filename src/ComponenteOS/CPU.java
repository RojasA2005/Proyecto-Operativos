/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ComponenteOS;

import EDD.Lista;
import EDD.Nodo;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    PCB running;
    boolean Working;
    
    
    public CPU(int Time, int Memoria){
        this.Time_per_cycle = Time;
        this.Pausa = new Interrupt();
        this.Memoria = Memoria;
        this.Scheduler = new ShortScheduler(Time, this.Pausa, Memoria);
        this.Long = new LongScheduler();
        this.procesoid = 0;
        this.Working = false;
    }
    
    @Override
    public void run(){
        while(Working){
            if(this.Pausa.isHasInterrupt()){
                if(this.Pausa.isProcessSwitch()){
                    Nodo n = this.Scheduler.choose();
                    this.running = n.getData();
                    this.running.setStatus(1);
                } else{
                    this.Scheduler.MoverBlockedAReady();
                }
            }
            while(running.getRemaining_cycles()>0){
                try {
                    Thread.sleep(this.Time_per_cycle);
                } catch (InterruptedException ex) {
                    Logger.getLogger(CPU.class.getName()).log(Level.SEVERE, null, ex);
                }
                running.update();
                if(running.isIsSuspended()){
                    this.Scheduler.MoverRunningABlocked(running);
                    this.Pausa.setHasInterrupt(true);
                    this.Pausa.setProcessSwitch(true);
                }
            }
        }
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
    
    public void MoverRunningAExit(){
        this.Long.addCompletado(running);
        this.Pausa.setHasInterrupt(true);
        this.Pausa.setProcessSwitch(true);
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
