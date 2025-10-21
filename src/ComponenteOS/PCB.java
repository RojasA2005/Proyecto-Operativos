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
    private int id;
    private int status; //Será un valor del 0 al 4, para determinar running, ready o blocked
    private boolean IsSuspended;
    private String name;
    private int PC;
    private int MAR;
    private Proceso_CPU_Bound ProcesoCPU;
    private Proceso_IO_Bound ProcesoIO;
    
    public PCB(int id, String name, int cycles, boolean IsCPUBound, int first_memory, int instrucciones, int ins_per_IO, int ins_to_io){
        status = 1; //Inicializa como ready
        PC = 0; //Inicializa como 0
        MAR = 0; //Igual
        this.name = name;
        this.id = id;
        if(IsCPUBound == true){
            this.ProcesoIO = null;
            this.ProcesoCPU = new Proceso_CPU_Bound(instrucciones, first_memory);
        } else{
            this.ProcesoCPU = null;
            this.ProcesoIO = new Proceso_IO_Bound(instrucciones, first_memory, ins_per_IO, ins_to_io);
        }
        
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
}
