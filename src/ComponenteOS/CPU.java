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
    private boolean Working;
    
    
    public CPU(int Time, int Memoria){
        this.Time_per_cycle = Time;
        this.Pausa = new Interrupt();
        this.Memoria = Memoria;
        this.Scheduler = new ShortScheduler(Time, this.Pausa, Memoria);
        this.Long = new LongScheduler();
        this.procesoid = 0;
        this.Working = false;
        this.running = null;
    }
    
    @Override
    public void run(){
        Nodo n;
        while(true){
            if(this.isWorking()==false){
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    Logger.getLogger(CPU.class.getName()).log(Level.SEVERE, null, ex);
                }
                continue;
            }
            if(this.running==null){
                this.Pausa.setHasInterrupt(true);
                this.Pausa.setProcessSwitch(true);
            }
            if(this.Pausa.isHasInterrupt()){
                if(this.Pausa.isProcessSwitch()){  
                    n = this.Scheduler.choose();
                    if(this.running!=null){
                        this.running.getPCandMAR();
                        if(this.running.getRemaining_cycles()==0){
                            this.MoverRunningAExit();
                        } else{
                            this.running.setStatus(1);
                            this.Scheduler.add(running);
                        }
                    }
                    if(n!=null){
                        this.running = n.getData();
                        this.running.setStatus(2);
                    } else{
                        this.running = null;
                    }
                }
                this.MoverNewAReady();
                this.Scheduler.MoverBlockedAReady();
                this.Scheduler.MoverSuspendedANoSuspended();
                this.Pausa.setHasInterrupt(false);
                this.Pausa.setProcessSwitch(false);
            }
            if(running!=null){
                if(running.getRemaining_cycles()>0){         
                    try {
                        Thread.sleep(this.Time_per_cycle);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(CPU.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    running.update();
                    if(running.getStatus()==3){
                        this.Scheduler.MoverRunningABlocked(running);
                        this.Pausa.setHasInterrupt(true);
                        this.Pausa.setProcessSwitch(true);
                    }
                } else{
                    System.out.println("terminado");
                this.Pausa.setHasInterrupt(true);
                this.Pausa.setProcessSwitch(true);
                }
                
            }
        }
    }
    
    public void iniciar(int quantum){
        this.Scheduler.Bloqueados.setWorking(true);
        this.Scheduler.Bloqueados.start();
        this.setWorking(true);
        this.MoverNewAReady();
        this.Scheduler.MoverBlockedAReady();
        this.Scheduler.MoverSuspendedANoSuspended();
        this.Scheduler.initialize(quantum);
        Nodo n = this.Scheduler.choose();
        if(n != null){
        this.running = n.getData();
        } else {
            this.Working = false;
            this.Scheduler.Bloqueados.setWorking(false);
        }
        this.start();
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
        this.Scheduler.LiberarMemoria(running);
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
        this.Scheduler.setTime(Time_per_cycle);
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
    
    public void changeType(int i){
        this.Scheduler.setTipo(i);
    }

    /**
     * @return the Working
     */
    public boolean isWorking() {
        return Working;
    }

    /**
     * @param Working the Working to set
     */
    public void setWorking(boolean Working) {
        this.Working = Working;
    }
    
}
