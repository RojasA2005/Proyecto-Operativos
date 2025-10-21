/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ComponenteOS;


/**
 *
 * @author Andrés
 */
public class Proceso_CPU_Bound extends Thread {
    int inst_total;
    int inst_faltantes;
    private boolean complete;
    private int PC;
    private int MAR;
    
    public Proceso_CPU_Bound(int ins){
        this.inst_total = ins;
        this.inst_faltantes = this.inst_total;
        this.complete = false;
    }
    
    public void updatecycles(){
        this.inst_faltantes--;
        this.PC++;
        this.MAR++;
    }

    /**
     * @return the complete
     */
    public boolean isComplete() {
        return complete;
    }

    /**
     * @return the PC
     */
    public int getPC() {
        return PC;
    }

    /**
     * @return the MAR
     */
    public int getMAR() {
        return MAR;
    }

    /**
     * @param PC the PC to set
     */
    public void setPC(int PC) {
        this.PC = PC;
    }

    /**
     * @param MAR the MAR to set
     */
    public void setMAR(int MAR) {
        this.MAR = MAR;
    }
    
}
