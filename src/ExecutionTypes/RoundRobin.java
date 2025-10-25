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
    
    public RoundRobin(int t, Cola l, Interrupt P){
        this.quantum = t;
        this.Listos = l;
        this.Pausa = P;
    }
    
    @Override
    public void run(){
        try {
            Thread.sleep(quantum);
        } catch (InterruptedException ex) {
            Logger.getLogger(RoundRobin.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.Pausa.setHasInterrupt(true);
        this.Pausa.setProcessSwitch(true);
    }
    
    public Nodo choose(){
        return this.Listos.dequeue();
        
    }
}
