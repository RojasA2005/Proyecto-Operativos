/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ComponenteOS;

import EDD.Lista;
import EDD.Nodo;

/**
 *
 * @author Andrés
 */
public class MidScheduler {
    Lista BlockedSuspend;
    Lista ReadySuspend;
    
    public MidScheduler(){
        this.BlockedSuspend = new Lista();
        this.ReadySuspend = new Lista();
    }
    
    public void AddBlockedSuspend(PCB Pn){
        Nodo n = new Nodo(Pn);
        this.BlockedSuspend.add(n);
    }
    
    public void MigrateToReady(){
        
    }
}
