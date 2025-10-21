/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ComponenteOS;

import EDD.Cola;

/**
 *
 * @author Andrés
 */
public class ShortScheduler {
    int tipo; //Del 0 al 5, para cada tipo de organización
    Cola Ready;
    Cola LRU;
    IOModulo Bloqueados;
    PCB running;
    MidScheduler Suspended;
    
    public ShortScheduler(int Time){
        this.running = null;
        this.Ready = new Cola();
        this.LRU = new Cola();
        this.Bloqueados = new IOModulo(Time);
        this.tipo = 0;
        this.Suspended = new MidScheduler();
    }
    
    public PCB choose(){
        PCB respuesta = null;
        switch(tipo){
            case 0:
                
                break;
            case 1:
                
                break;
            case 2:
                
                break;
            case 3:
                
                break;
            case 4:
                
                break;
            case 5:
                
                break;
        }
        this.running = respuesta;
        return respuesta;
    }
    
    public void add(PCB Pn){
        
    }

}
