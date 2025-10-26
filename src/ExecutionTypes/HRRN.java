/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ExecutionTypes;

import EDD.Cola;
import EDD.Nodo;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrés
 */
public class HRRN extends Thread{
    Cola Listos;
    private String name;
    int Time;
    private boolean Working;
    
    public HRRN(Cola l, int t){
        this.Working = false;
        this.Listos = l;
        this.Time = t;
        this.name = "Highest Response Ratio Next";
        Nodo n = this.Listos.peek();
        while(n != null){
            n.setContador(0);
            n.setR();
            n = n.getpNext();
        }
    }
    
    @Override
    public void run(){
        while(true){
            if(Working == false){
                continue;
            }
        try {
            Thread.sleep(this.Time);
        } catch (InterruptedException ex) {
            Logger.getLogger(HRRN.class.getName()).log(Level.SEVERE, null, ex);
        }
        Nodo n = this.Listos.peek();
        while(n!=null){
            n.setContador(n.getContador() + 1);
            n.setR();
            n = n.getpNext();
        }
    }
    }
    
    
    public Nodo choose(){
        Nodo n_max = this.Listos.peek();
        int max;
        if(n_max!=null){
            max = n_max.getR();
        } else {
            return null;
        }
        Nodo n = n_max.getpNext();
        while(n != null){
            if(n.getR() > max){
                n_max = n;
            }
            n = n.getpNext();
        }
        return n_max;
    }

    /**
     * @return the name
     */
    public String getmethodName() {
        return name;
    }

    /**
     * @param Working the Working to set
     */
    public void setWorking(boolean Working) {
        this.Working = Working;
    }
}
