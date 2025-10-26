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
    private Nodo pFirst;
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
            setpFirst(n);
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
    if(pFirst == null) return;
    
    // Caso: eliminar el primer nodo
    if(pFirst.getData().getName().equals(name)){
            setpFirst(pFirst.getpNext());
        return;
    }
    
    // Caso: eliminar nodos intermedios
    Nodo anterior = pFirst;
    Nodo actual = pFirst.getpNext();
    
    while(actual != null){
        if(actual.getData().getName().equals(name)){
            anterior.setpNext(actual.getpNext());
            return;
        }
        anterior = actual;
        actual = actual.getpNext();
    }
}

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @param pFirst the pFirst to set
     */
    public void setpFirst(Nodo pFirst) {
        this.pFirst = pFirst;
    }
}
