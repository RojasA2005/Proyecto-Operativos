/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ExecutionTypes;

import ComponenteOS.Interrupt;
import EDD.Cola;
import EDD.Nodo;

/**
 *
 * @author Andrés
 */
public class SRT {
    Cola Listos;
    private String name;
    Interrupt Pausa;
    
    public SRT(Cola l, Interrupt P ){
        this.Listos = l;
        this.Pausa = P;
        this.name = "Shortest Remaining Time";
    }
    
    public void Agregar(Nodo n){
        this.Listos.queue(n);
        this.Pausa.setProcessSwitch(true);
        this.Pausa.setHasInterrupt(true);
    }
    
    public Nodo choose(){
        Nodo n_min = this.Listos.peek();
        int min;
        if(n_min!=null){
            min = n_min.getData().getRemaining_cycles();
        } else {
            return null;
        }
        Nodo n = n_min.getpNext();
        while(n != null){
            if(n.getData().getRemaining_cycles() < min){
                n_min = n;
            }
            n = n.getpNext();
        }
        if(n_min!=null){
            System.out.println("Se eligió " + n_min.getData().getAllData());
        }
        return n_min;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
}
