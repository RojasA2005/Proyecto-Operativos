/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ComponenteOS;

import EDD.Cola;
import EDD.Lista;
import EDD.Nodo;

/**
 *
 * @author Andrés
 */
public class MidScheduler {
    Lista BlockedSuspend;
    Lista ReadySuspend;
    Cola AllSuspended;
    private int size;
    
    public MidScheduler(){
        size = 0;
        this.BlockedSuspend = new Lista();
        this.ReadySuspend = new Lista();
        this.AllSuspended = new Cola();
    }
    
    public void add(PCB Pn){
        Nodo n = new Nodo(Pn);
        this.AllSuspended.queue(n);
        if(Pn.getStatus()==3){
            this.AddBlockedSuspend(Pn);
        } else{
            this.AddReadySuspend(Pn);
        }
        size++;
    }
    
    private void AddBlockedSuspend(PCB Pn){
        Nodo n = new Nodo(Pn);
        this.BlockedSuspend.add(n);
    }
    
    public void MigrateToReady(){
        Nodo n = this.BlockedSuspend.getFirst();
        while(n != null){
            if(n.getData().IOEnd()==true){
                this.BlockedSuspend.eliminate(n.getData().getName());
                n.setpNext(null);
                this.ReadySuspend.add(n);
            }
        }
    }
    
    private void AddReadySuspend(PCB Pn){
        Nodo n = new Nodo(Pn);
        this.ReadySuspend.add(n);
    }
    
    public PCB quitar(){
        if(getSize() > 0){
            size--;
        }
        Nodo n = this.AllSuspended.dequeue();
        if(n != null){
            this.BlockedSuspend.eliminate(n.getData().getName());
            this.ReadySuspend.eliminate(n.getData().getName());
            return n.getData();
        }
        return null;
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }
}
