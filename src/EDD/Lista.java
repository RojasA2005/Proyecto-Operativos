/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 *
 * @author Andrés
 */
public class Lista {
    Nodo pFirst;
    private int size;
    
    public Lista(){
        pFirst = null;
        size = 0;
    }
    
    public Nodo getFirst(){
        if(pFirst == null){
            return null;
        }
        return pFirst;
    }
    
    public void add(Nodo n){
        if(pFirst == null){
            pFirst = n;
        } else{
            Nodo aux = pFirst;
            while(aux.getpNext() != null){
                aux = aux.getpNext();
            }
            aux.setpNext(n);
        }
        size++;
    }
    
    public Nodo search(String name){
        Nodo n = this.pFirst;
        while(n != null){
            if(n.getData().getName().equals(name)){
                return n;
            }
            n = n.getpNext();
        }
        return null;
    }
    
    public void eliminate(String name){
        Nodo n = this.pFirst;
        if(n.getData().getName().equals(name) && n != null){
            pFirst = pFirst.getpNext();
        } else{
            while(n != null){
                if(n.getpNext().getData().getName().equals(name)){
                    n.setpNext(n.getpNext().getpNext());
                }
                n = n.getpNext();
            }
        }
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }
}
