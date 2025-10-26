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
public class LongScheduler {
    Lista Nuevos;
    Lista Completados;
    
    public LongScheduler(){
        this.Nuevos = new Lista();
        this.Completados = new Lista();
    }
    
    public void addNuevo(PCB Pn){
        Nodo n = new Nodo(Pn);
        this.Nuevos.add(n);
    }
    
    public Lista vaciar(){
        Lista l = this.Nuevos;
        this.Nuevos = new Lista();
        return l;
    }
    
    public void addCompletado(PCB Pn){
        Pn.setStatus(4);
        Nodo n = new Nodo(Pn);
        this.Completados.add(n);
    }
    
    public Lista Completados(){
        return this.Completados;
    }
    
    public String NombreCompletados(){
        String s = "";
        Nodo n = this.Completados.getFirst();
        while(n != null){
            if(n.getData().isIsSuspended()==false){
                    s = s + n.getData().getAllData() + "\n";
                }
            n = n.getpNext();
        }
        return s;
    }
}
