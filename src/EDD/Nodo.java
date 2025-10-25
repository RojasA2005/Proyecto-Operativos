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
    private int Contador; //Para feedback y para hcns
    private int R; //Solo para hrrn
    
    public Nodo(PCB P){
        this.Data = P;
        this.Contador = 0;
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

    /**
     * @return the Contador
     */
    public int getContador() {
        return Contador;
    }

    /**
     * @param Contador the Contador to set
     */
    public void setContador(int Contador) {
        this.Contador = Contador;
    }

    /**
     * @return the R
     */
    public int getR() {
        return R;
    }

    /**
     * @param R the R to set
     */
    public void setR() {
        this.R = (this.Contador + this.Data.getRemaining_cycles())/this.Data.getRemaining_cycles();
    }
}
