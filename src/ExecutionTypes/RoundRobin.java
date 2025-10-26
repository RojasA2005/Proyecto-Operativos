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
    private int quantum;
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
            if(this.Pausa.isQuantumWorking() == true){            
                try {
                    Thread.sleep(quantum);
                } catch (InterruptedException ex) {
                    Logger.getLogger(RoundRobin.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.Pausa.setQuantumWorking(false);
                System.out.println("paro");
                this.Pausa.setHasInterrupt(true);
                this.Pausa.setProcessSwitch(true);
        } else{
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    Logger.getLogger(RoundRobin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        
    }
    }
    
    public Nodo choose(){
        Nodo n = this.Listos.dequeue();
        if(n!=null){
            System.out.println("Se eligió " + n.getData().getAllData());
        }
        return n;
    }

    /**
     * @param Working the Working to set
     */
    public void setWorking(boolean Working) {
        this.Working = Working;
    }

    /**
     * @param quantum the quantum to set
     */
    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }

    /**
     * @return the Working
     */
    public boolean isWorking() {
        return Working;
    }
}
