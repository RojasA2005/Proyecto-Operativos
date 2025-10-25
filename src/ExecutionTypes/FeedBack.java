/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ExecutionTypes;

import ComponenteOS.Interrupt;
import EDD.Cola;
import EDD.ColaMultinivel;
import EDD.Nodo;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrés
 */
public class FeedBack extends Thread{
    ColaMultinivel pFirst;
    Interrupt Pausa;
    int quantum;
    private String name;
    
    public FeedBack(Cola l, Interrupt P, int t){
        this.name = "FeedBack";
        this.pFirst = new ColaMultinivel(l);
        this.Pausa = P;
        this.quantum = t;
        Nodo n = this.pFirst.getData().peek();
        while(n != null){
            n.setContador(0);
            n = n.getpNext();
        }
    }
    
    public void Agregar(Nodo n){
        ColaMultinivel C = this.pFirst;
        int i = n.getContador();
        while(i > 0){
            if(C.getpNext()==null){
                C.setpNext(new ColaMultinivel(new Cola()));
            }
            i--;
            C = C.getpNext();
        }
        n.setContador(n.getContador()+1);
        C.getData().queue(n);
    }
    
    public Nodo Choose(){
        Nodo n = this.pFirst.getData().dequeue();
        if(this.pFirst.getData().getSize()==0){
            this.pFirst = this.pFirst.getpNext();
        }
        return n;
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

    /**
     * @return the name
     */
    public String getmethodName() {
        return name;
    }
}
