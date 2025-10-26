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
public class MidScheduler extends Thread{
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
    // PRIMERO recolectar los procesos que cumplen
    Lista procesosAMover = new Lista();
    Nodo recolector = this.BlockedSuspend.getFirst();
    while(recolector != null){
        if(recolector.getData().IOEnd() == false){  // Con false como especificas
            procesosAMover.add(new Nodo(recolector.getData()));
        }
        recolector = recolector.getpNext();  // ← ¡IMPORTANTE!
    }
    
    // LUEGO moverlos
    Nodo movil = procesosAMover.getFirst();
    while(movil != null){
        this.BlockedSuspend.eliminate(movil.getData().getName());
        this.ReadySuspend.add(new Nodo(movil.getData()));
        movil = movil.getpNext();
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
    
    public String NombreSuspendidosReady(){
        String s = "";
        Nodo n = this.ReadySuspend.getFirst();
        while(n != null){
            if(n.getData().isIsSuspended()==false){
                    s = s + n.getData().getAllData() + "\n";
                }
            n = n.getpNext();
        }
        return s;
    }
    
    public String NombreSuspendidoBlocked(){
        String s = "";
        Nodo n = this.BlockedSuspend.getFirst();
        while(n != null){
            if(n.getData().isIsSuspended()==false){
                    s = s + n.getData().getAllData() + "\n";
                }
            n = n.getpNext();
        }
        return s;
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }
}
