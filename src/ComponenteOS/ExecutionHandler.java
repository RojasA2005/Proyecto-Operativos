/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ComponenteOS;

import EDD.Cola;
import EDD.Nodo;
import ExecutionTypes.FIFO;
import ExecutionTypes.FeedBack;
import ExecutionTypes.HRRN;
import ExecutionTypes.RoundRobin;
import ExecutionTypes.SPN;
import ExecutionTypes.SRT;

/**
 *
 * @author Andrés
 */
public class ExecutionHandler {
    FIFO fcfs;
    FeedBack feed;
    HRRN hrrn;
    RoundRobin RR;
    SPN spn;
    SRT srt;
    private int type;
    private Cola listo;
    Interrupt P;
    
    public ExecutionHandler(Cola l, Interrupt P){
        this.listo = l;
        this.P = P;
    }
    
    public void Initialize(int quantum){ //Ejecutar este en un cambio de metodo de planificación
         switch(this.type){
            case 0:
                this.fcfs = new FIFO(this.listo);
                break;
            case 1:
                this.RR = new RoundRobin(quantum, this.listo, this.P);
                break;
            case 2:
                this.spn = new SPN(this.listo);
                break;
            case 3:
                this.srt = new SRT(this.listo, this.P); //aquí agregar
                break;
            case 4:
                this.hrrn = new HRRN(this.listo, 1000);
                break;
            case 5:
                this.feed = new FeedBack(this.listo, P, quantum);;
                break;
        }
    }
    
    public Nodo Execute(){
        Nodo n = null;
         switch(this.type){
            case 0:
                n = this.fcfs.choose();
                break;
            case 1:
                n = this.RR.choose();
                this.RR.start();
                break;
            case 2:
                n = this.spn.choose();
                break;
            case 3:
                n = this.srt.choose();
                break;
            case 4:
                n = this.hrrn.choose();
                this.hrrn.start();
                break;
            case 5:
                n = this.feed.choose();
                this.feed.start();
                break;
        }
         return n;
    }
    
    public void Añadir(Nodo n){
        if(this.type==3){
            this.srt.Agregar(n);
        } else if(this.type==6){
            this.feed.Agregar(n);
            this.listo.queue(new Nodo(n.getData()));
        } else{
            this.listo.queue(n);
        }
        
    }
    
    public void Eliminar(PCB Pn){
        if(type==6){
            this.feed.quitar(Pn);
        } else{
            this.listo.Eliminate(Pn);
        }
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) {
        if(type < 0 || type > 5){
            type = 0;
        }
        this.type = type;
    }

    /**
     * @param listo the listo to set
     */
    public void setListo(Cola listo) {
        this.listo = listo;
    }
}
