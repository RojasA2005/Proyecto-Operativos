/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 *
 * @author Andrés
 */
public class ColaMultinivel {
    private Cola Data;
    private ColaMultinivel pNext;
    
    public ColaMultinivel(Cola Inicial){
        this.Data = Inicial;
    }
    

    /**
     * @return the Data
     */
    public Cola getData() {
        return Data;
    }

    /**
     * @return the pNext
     */
    public ColaMultinivel getpNext() {
        return pNext;
    }

    /**
     * @param pNext the pNext to set
     */
    public void setpNext(ColaMultinivel pNext) {
        this.pNext = pNext;
    }
}
