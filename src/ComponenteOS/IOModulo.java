/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ComponenteOS;

import EDD.Lista;
import EDD.Nodo;
import EDD.Semaforo;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrés
 */
public class IOModulo extends Thread{
    private Lista IOlista;
    private int Time;
    Semaforo S;
    Interrupt Pausa;
    
    public IOModulo(int Time, Interrupt P, Semaforo S){
        this.IOlista = new Lista();
        this.Time = Time;
        this.Pausa = P;
        this.S = S;
    }
    
    @Override
    public void run(){
        Nodo n;
        S.waitSem();
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
        S.signal();
    }
    
    public void add(PCB Pn){
        Nodo n = new Nodo(Pn);
        this.IOlista.add(n);
    }
    
    public Lista quitar(){
        Lista l = new Lista();
        Nodo n = this.IOlista.getFirst();
        Nodo m;
        while(n!=null){
        if(n.getData().IOEnd()==true){
            m = n.getpNext();
            this.IOlista.eliminate(m.getData().getName());
            l.add(m);
            continue;
        }
        n = n.getpNext();
        }
        return l;
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
