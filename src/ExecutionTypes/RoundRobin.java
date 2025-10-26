/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ExecutionTypes;

import ComponenteOS.Interrupt;
import EDD.Cola;
import EDD.Nodo;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrés
 */
public class RoundRobin extends Thread{
    int quantum;
    Cola Listos;
    Interrupt Pausa;
    private boolean Working;
    public RoundRobin(int t, Cola l, Interrupt P){
        this.Working = false;
        this.quantum = t;
        this.Listos = l;
        this.Pausa = P;
    }
    
    @Override
    public void run(){
        while(true){
            if(Working = false){
                continue;
            }
        try {
            Thread.sleep(quantum);
        } catch (InterruptedException ex) {
            Logger.getLogger(RoundRobin.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.Pausa.setHasInterrupt(true);
        this.Pausa.setProcessSwitch(true);
        Working = false;
    }
    }
    
    public Nodo choose(){
        return this.Listos.dequeue();
        
    }

    /**
     * @param Working the Working to set
     */
    public void setWorking(boolean Working) {
        this.Working = Working;
    }
}
