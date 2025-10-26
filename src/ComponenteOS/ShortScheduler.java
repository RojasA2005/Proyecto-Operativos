/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ComponenteOS;

import EDD.Cola;
import EDD.Lista;
import EDD.Nodo;
import EDD.Semaforo;

/**
 *
 * @author Andrés
 */
public class ShortScheduler {
    private int tipo; //Del 0 al 5, para cada tipo de organización
    Cola Ready;
    Cola LRU;
    IOModulo Bloqueados;
    Nodo running;
    MidScheduler Suspended;
    Interrupt Pausa;
    private int Memoria;
    private int MemDisponible;
    Semaforo S;
    ExecutionHandler E;
    private int Time;
    
    public ShortScheduler(int Time, Interrupt P, int Memoria){
        this.running = null;
        this.Ready = new Cola();
        this.LRU = new Cola();
        this.S = new Semaforo(1);
        this.Bloqueados = new IOModulo(Time, P, S);
        this.E = new ExecutionHandler(this.Ready, P);
        this.tipo = 0;
        this.Suspended = new MidScheduler();
        this.Pausa = P;
        this.Memoria = Memoria;
        this.MemDisponible = Memoria;
    }
    
    public Nodo choose(){
        Nodo respuesta = null;
        this.running = this.E.Execute();
        respuesta = this.running;
        if(this.running != null){
            this.LRU.Eliminate(this.running.getData().getId());
            this.LRU.queue(new Nodo(this.running.getData()));
        }
        return respuesta;
    }
    
    public void initialize(int t){
        this.E.Initialize(t);
    }
    
    public void add(PCB Pn){
        Nodo n = new Nodo(Pn);
        if(this.MemDisponible >= Pn.GetCiclos()){
            this.E.Añadir(n);
            n.getData().setMAR(this.MemDisponible - Pn.GetCiclos());
            n.getData().setPC(this.MemDisponible - Pn.GetCiclos());
            this.setMemDisponible(this.MemDisponible - Pn.GetCiclos());
        } else {
            while(this.MemDisponible < Pn.GetCiclos()){
                this.MoverASuspended();
            }
            this.E.Añadir(n);
            n.getData().setMAR(this.MemDisponible - Pn.GetCiclos());
            n.getData().setPC(this.MemDisponible - Pn.GetCiclos());
            this.setMemDisponible(this.MemDisponible - Pn.GetCiclos());
        }
        this.LRU.queue(new Nodo(Pn));
    }
    
    public void MoverSuspendedANoSuspended(){
        int i = this.Suspended.getSize();
        PCB Pn = null;
        while(i > 0){
            Pn = this.Suspended.quitar();
            if(this.MemDisponible >= Pn.GetCiclos()){
                if(Pn.getStatus()!=3){
                    this.E.Añadir(new Nodo(Pn));
                } else{
                
                }
                this.setMemDisponible(this.MemDisponible - Pn.GetCiclos());
                Pn.setMAR(this.MemDisponible);
                Pn.setPC(this.MemDisponible);
                Pn.setIsSuspended(false);
                this.LRU.queue(new Nodo(Pn));
            } else{
                this.Suspended.add(Pn);
            }
            i--;
        }
    }
    
    public void MoverASuspended(){
        Nodo n = this.LRU.dequeue();
        this.E.Eliminar(n.getData());
        this.LiberarMemoria(n.getData());
        this.Suspended.add(n.getData());
    }
    
    public void LiberarMemoria(PCB Pn){
        this.setMemDisponible(this.MemDisponible + Pn.GetCiclos());
        if(this.MemDisponible > this.Memoria){ //Caso extremo
            this.setMemDisponible(this.Memoria);
        }
    }
    
    public void MoverRunningABlocked(PCB Pn){
        this.Bloqueados.add(Pn);
    }
    
    public void MoverBlockedAReady(){
        this.Suspended.MigrateToReady();
        Lista l = this.Bloqueados.quitar();
        Nodo n = l.getFirst();
        
        while(n != null){
            this.E.Añadir(n);
            n = n.getpNext();
        }
    }

    /**
     * @param Memoria the Memoria to set
     */
    public void setMemoria(int Memoria) {
        this.setMemDisponible(this.MemDisponible + (Memoria - this.Memoria));
        this.Memoria = Memoria;
        
    }


    /**
     * @param Time the Time to set
     */
    public void setTime(int Time) {
        this.Time = Time;
        this.Bloqueados.setTime(Time);
    }

    /**
     * @param MemDisponible the MemDisponible to set
     */
    public void setMemDisponible(int MemDisponible) {
        this.MemDisponible = MemDisponible;
    }

    /**
     * @return the tipo
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
        this.E.setType(tipo);
        this.E.Initialize(tipo);
    }


}
