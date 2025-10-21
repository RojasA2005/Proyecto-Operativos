/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ComponenteOS;

/**
 *
 * @author Andrés
 */
public class Proceso_IO_Bound{
    private int inst_total;
    private int inst_faltantes;
    private int inst_per_IO;
    private int inst_per_IO_faltante;    
    private int inst_to_IO;
    private int inst_to_IO_faltante;
    private boolean complete;
    private boolean StartInterrupt;
    private int PC;
    private int MAR;
    
    public Proceso_IO_Bound(int ins, int inst_per_io, int inst_to_io){
        this.inst_total = ins;
        this.complete = false;
        this.inst_faltantes = this.inst_total;
        this.inst_per_IO = inst_per_io;
        this.inst_per_IO_faltante = this.inst_per_IO;
        this.inst_to_IO = inst_to_io;
        this.inst_to_IO_faltante = this.inst_to_IO;
        

    }
    
    public void updatecycles(){
        this.inst_faltantes--;
        this.inst_to_IO_faltante--;
        setPC(getPC() + 1);
        MAR++;
        if(this.getInst_faltantes()==0){
            this.complete = true;
        }
        if(this.getInst_to_IO_faltante()==0){
            this.setStartInterrupt(true);
        }
    }
    
    public void finishinterrupt(){
        this.inst_to_IO_faltante = this.inst_to_IO;
        this.setStartInterrupt(false);
    }
    
    public void IOwait(){
        if(this.inst_per_IO_faltante>0){
            this.inst_per_IO_faltante--;
        }
        if(this.inst_per_IO_faltante==0){
            this.finishinterrupt();
        }
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
     * @return the inst_faltantes
     */
    public int getInst_faltantes() {
        return inst_faltantes;
    }

    /**
     * @return the inst_to_IO
     */
    public int getInst_to_IO() {
        return inst_to_IO;
    }

    /**
     * @return the StartInterrupt
     */
    public boolean isStartInterrupt() {
        return StartInterrupt;
    }

    /**
     * @return the inst_per_IO_faltante
     */
    public int getInst_per_IO_faltante() {
        return inst_per_IO_faltante;
    }

    /**
     * @return the inst_to_IO_faltante
     */
    public int getInst_to_IO_faltante() {
        return inst_to_IO_faltante;
    }

    /**
     * @param StartInterrupt the StartInterrupt to set
     */
    public void setStartInterrupt(boolean StartInterrupt) {
        this.StartInterrupt = StartInterrupt;
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
