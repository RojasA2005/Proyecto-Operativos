/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

import ComponenteOS.PCB;


/**
 *
 * @author Andrés
 */
public class Nodo {
    private PCB Data;
    private Nodo pNext;
    
    public Nodo(PCB P){
        this.Data = P;
    }

    /**
     * @return the Data
     */
    public PCB getData() {
        return Data;
    }

    /**
     * @return the pNext
     */
    public Nodo getpNext() {
        return pNext;
    }

    /**
     * @param pNext the pNext to set
     */
    public void setpNext(Nodo pNext) {
        this.pNext = pNext;
    }
}
