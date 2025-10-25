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
    int tipo; //Del 0 al 5, para cada tipo de organización
    Cola Ready;
    Cola LRU;
    IOModulo Bloqueados;
    Nodo running;
    MidScheduler Suspended;
    Interrupt Pausa;
    int Memoria;
    int MemDisponible;
    Semaforo S;
    ExecutionHandler E;
    
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
        return respuesta;
    }
    
    public void initialize(int t){
        this.E.Initialize(t);
    }
    
    public void add(PCB Pn){
        Nodo n = new Nodo(Pn);
        if(this.MemDisponible >= Pn.GetCiclos()){
            this.Ready.queue(n);
            this.MemDisponible = this.MemDisponible - Pn.GetCiclos();
        } else {
            this.Suspended.add(Pn);
        }
    }
    
    public void MoverRunningABlocked(PCB Pn){
        this.Bloqueados.add(Pn);
    }
    
    public void MoverBlockedAReady(){
        this.S.waitSem();
        Lista l = this.Bloqueados.quitar();
        this.S.signal();
        Nodo n = l.getFirst();
        while(n != null){
            this.E.Añadir(n);
            n = n.getpNext();
        }
    }


}
