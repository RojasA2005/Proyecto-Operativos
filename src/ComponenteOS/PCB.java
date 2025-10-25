/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ComponenteOS;

/**
 *
 * @author Andrés
 */
public class PCB {
    private int id; //                                               0      1       2       3      4
    private int status; //Será un valor del 0 al 4, para determinar new, running, ready, blocked, exit
    private int ciclos;
    private int remaining_cycles;
    private boolean IsSuspended;
    private String name;
    private int PC;
    private int MAR;
    private Proceso_CPU_Bound ProcesoCPU;
    private Proceso_IO_Bound ProcesoIO;
    
    public PCB(int id, String name, int cycles, boolean IsCPUBound, int first_memory, int ins_per_IO, int ins_to_io){
        status = 0; //Inicializa como new
        PC = 0; //Inicializa como 0
        MAR = 0; //Igual
        this.name = name;
        this.ciclos = cycles;
        this.id = id;
        if(IsCPUBound == true){
            this.ProcesoIO = null;
            this.ProcesoCPU = new Proceso_CPU_Bound(cycles);
        } else{
            this.ProcesoCPU = null;
            this.ProcesoIO = new Proceso_IO_Bound(cycles, ins_per_IO, ins_to_io);
        }
        this.IsSuspended = false;
        this.remaining_cycles = cycles;
        
    }
    
    public void update(){
        if(this.ProcesoCPU==null){
            this.ProcesoIO.updatecycles();
        } else{
            this.ProcesoCPU.updatecycles();
        }
    }
    
    public void updatewait(){
        if(this.ProcesoIO != null){
            this.ProcesoIO.IOwait();
            if(this.ProcesoIO.getInst_per_IO_faltante()==0){
                this.ProcesoIO.finishinterrupt();
            }
        }
    }
    
    public boolean IOEnd(){
        if(this.ProcesoIO != null){
            return this.ProcesoIO.isStartInterrupt();
        }
        return false;
    }
    
    public int GetCiclos(){
        return this.ciclos;
    }
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the PC
     */
    public int getPC() {
        return PC;
    }

    /**
     * @param PC the PC to set
     */
    public void setPC(int PC) {
        this.PC = PC;
    }

    /**
     * @return the MAR
     */
    public int getMAR() {
        return MAR;
    }

    /**
     * @param MAR the MAR to set
     */
    public void setMAR(int MAR) {
        this.MAR = MAR;
    }

    /**
     * @return the IsSuspended
     */
    public boolean isIsSuspended() {
        return IsSuspended;
    }

    /**
     * @param IsSuspended the IsSuspended to set
     */
    public void setIsSuspended(boolean IsSuspended) {
        this.IsSuspended = IsSuspended;
    }

    /**
     * @return the remaining_cycles
     */
    public int getRemaining_cycles() {
        return remaining_cycles;
    }

    /**
     * @param remaining_cycles the remaining_cycles to set
     */
    public void setRemaining_cycles(int remaining_cycles) {
        this.remaining_cycles = remaining_cycles;
    }
}
