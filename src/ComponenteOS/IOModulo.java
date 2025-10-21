/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ComponenteOS;

import EDD.Lista;
import EDD.Nodo;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrés
 */
public class IOModulo extends Thread{
    private Lista IOlista;
    private int Time;
    Semaphore semaforo;
    Interrupt Pausa;
    
    public IOModulo(int Time, Interrupt P){
        this.IOlista = new Lista();
        this.Time = Time;
        this.Pausa = P;
    }
    
    @Override
    public void run(){
        Nodo n = null;
        try {
            n = this.IOlista.getFirst();
            while(n != null){
                Thread.sleep(Time);
                n.getData().updatewait();
                if(n.getData().IOEnd() == false){
                    this.Pausa.setHasInterrupt(true);
                }
                n = n.getpNext();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(IOModulo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void add(PCB Pn){
        Nodo n = new Nodo(Pn);
        this.IOlista.add(n);
    }
    
    public PCB quitar(String name){
        Nodo n = this.IOlista.search(name);
        this.IOlista.eliminate(name);
        if(n != null){
            return n.getData();
        }
        return null;
    }

    /**
     * @return the Time
     */
    public int getTime() {
        return Time;
    }

    /**
     * @param Time the Time to set
     */
    public void setTime(int Time) {
        this.Time = Time;
    }
}
