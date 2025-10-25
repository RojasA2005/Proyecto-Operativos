/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ExecutionTypes;

import ComponenteOS.Interrupt;
import ComponenteOS.PCB;
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
        Cola copy = new Cola();
        Nodo m = l.peek();
        while(m != null){
            copy.queue(new Nodo(m.getData()));
            m = m.getpNext();
        }
        this.pFirst = new ColaMultinivel(copy);
        this.Pausa = P;
        this.quantum = t;
        Nodo n = this.pFirst.getData().peek();
        while(n != null){
            n.setContador(0);
            n = n.getpNext();
        } //esto se podría eliminar, chequear despues
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
    
    public Nodo choose(){
        Nodo n = this.pFirst.getData().dequeue();
        if(this.pFirst.getData().getSize()==0){
            this.pFirst = this.pFirst.getpNext();
        }
        return n;
    }
    
    public void quitar(PCB Pn){
        ColaMultinivel iter = this.pFirst;
        while(iter!=null){
            iter.getData().Eliminate(Pn);
            iter = iter.getpNext();
        }
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
