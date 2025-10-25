/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ExecutionTypes;

import EDD.Cola;
import EDD.Nodo;

/**
 *
 * @author Andrés
 */
public class FIFO {
    private Cola Listos;
    
    public FIFO(Cola l){
        this.Listos = l;
    }
    
    public Nodo choose(){
        return this.Listos.dequeue();
    }
    
    
}
