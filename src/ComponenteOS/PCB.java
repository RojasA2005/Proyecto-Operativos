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
            this.ProcesoCPU = null;
            this.ProcesoIO = new Proceso_IO_Bound(cycles, ins_per_IO, ins_to_io);
        } else{
            this.ProcesoIO = null;
            this.ProcesoCPU = new Proceso_CPU_Bound(cycles);
        }
        this.IsSuspended = false;
        this.remaining_cycles = cycles;
        
    }
    
    public void update(){
        if(this.ProcesoCPU==null){
            this.ProcesoIO.updatecycles();
            this.remaining_cycles = this.ProcesoIO.getInst_faltantes();
            if(this.ProcesoIO.isStartInterrupt()){
                this.status = 3;
            }
        } else{
            this.ProcesoCPU.updatecycles();
            this.remaining_cycles = this.ProcesoCPU.inst_faltantes;
        }
    }
    
    public void getPCandMAR(){
        if(this.ProcesoCPU==null){
            this.PC = this.ProcesoIO.getPC();
            this.MAR = this.ProcesoIO.getMAR();            
        } else{
            this.PC = this.ProcesoCPU.getPC();
            this.MAR = this.ProcesoCPU.getMAR();  
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
    
    public String getAllData(){
        String d = this.id + " | " + this.name + " | " + this.PC + " | " + this.MAR;
        return d;
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
         if(this.ProcesoCPU==null){
            this.ProcesoIO.setPC(PC);
        } else{
            this.ProcesoCPU.setPC(PC);
        }
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
         if(this.ProcesoCPU==null){
            this.ProcesoIO.setMAR(MAR);
        } else{
            this.ProcesoCPU.setMAR(MAR);
        }
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
