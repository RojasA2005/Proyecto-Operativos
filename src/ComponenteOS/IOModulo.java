/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ComponenteOS;

import EDD.Lista;
import EDD.Nodo;
import EDD.Semaforo;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrés
 */
public class IOModulo extends Thread{
    private Lista IOlista;
    private final Object listaLock = new Object();
    private int Time;
    Semaforo S;
    Interrupt Pausa;
    private boolean Working;
    private int size;
    
    public IOModulo(int Time, Interrupt P, Semaforo S){
        this.IOlista = new Lista();
        this.Time = Time;
        this.Pausa = P;
        this.S = S;
        this.Working = false;
        size = 0;
    }
    
    @Override
    public void run(){
        while(true){
            if(this.Working==false){
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    Logger.getLogger(IOModulo.class.getName()).log(Level.SEVERE, null, ex);
                }
                continue;
            }
        Nodo n;
        try {
            n = this.IOlista.getFirst();
            System.out.println(this.size);
            while(n != null){
                Thread.sleep(Time);
                n.getData().updatewait();
                if(n.getData().IOEnd() == false){
                    this.Pausa.setHasInterrupt(true);
                }
                n = n.getpNext();
            }
            Thread.sleep(50);
        } catch (InterruptedException ex) {
            Logger.getLogger(IOModulo.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
    
    public void add(PCB Pn) {
    synchronized(listaLock) {
        Nodo n = new Nodo(Pn);
        this.IOlista.add(n);
        size++;
    }
}
    
   public Lista quitar() {
    synchronized(listaLock) {
        Lista l = new Lista();
        Nodo anterior = null;
        Nodo actual = this.IOlista.getFirst();
        
        while (actual != null) {
            PCB proceso = actual.getData();
            
            if (!proceso.IOEnd() && !proceso.isIsSuspended()) {
                l.add(new Nodo(proceso));
                
                if (anterior == null) {
                    this.IOlista.setpFirst(actual.getpNext());
                } else {
                    anterior.setpNext(actual.getpNext());
                }
                size--;
                actual = actual.getpNext();
            } else {
                anterior = actual;
                actual = actual.getpNext();
            }
        }
        return l;
    }
}
    
  public void EliminarSuspended(){
    // PRIMERO recolectar
    Lista procesosAEliminar = new Lista();
    Nodo recolector = this.IOlista.getFirst();
    while(recolector != null){
        PCB proceso = recolector.getData();
        if(!proceso.IOEnd() && proceso.isIsSuspended()){
            procesosAEliminar.add(new Nodo(proceso));
        }
        recolector = recolector.getpNext();
    }
    
    // LUEGO eliminar
    Nodo eliminador = procesosAEliminar.getFirst();
    while(eliminador != null){
        this.IOlista.eliminate(eliminador.getData().getName());
        size--;
        eliminador = eliminador.getpNext();
    }
}
    
    public String NombresBloqueados(){
        String s = "";
        Nodo n = this.IOlista.getFirst();
        while(n != null){
            if(n.getData().isIsSuspended()==false){
                    s = s + n.getData().getAllData() + "\n";
                }
            n = n.getpNext();
        }
        return s;
    }

    /**
     * @return the Time
     */
    public int getTime() {
        return Time;
    }

    /**
     * @param Time the Time to set
     */
    public void setTime(int Time) {
        this.Time = Time;
    }

    /**
     * @return the Working
     */
    public boolean isWorking() {
        return Working;
    }

    /**
     * @param Working the Working to set
     */
    public void setWorking(boolean Working) {
        this.Working = Working;
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }
}
