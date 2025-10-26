/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ComponenteOS;

/**
 *
 * @author Andrés
 */
public class Interrupt {
    private boolean HasInterrupt;
    private boolean ProcessSwitch;
    private boolean QuantumWorking;
    private boolean ActualizarInterfaz;
    private boolean tick;
    
    public Interrupt(){
        this.HasInterrupt = false;
        this.ProcessSwitch = false;
        this.QuantumWorking = false;
    }
    
    /**
     * @return the HasInterrupt
     */
    public boolean isHasInterrupt() {
        return HasInterrupt;
    }

    /**
     * @param HasInterrupt the HasInterrupt to set
     */
    public void setHasInterrupt(boolean HasInterrupt) {
        this.HasInterrupt = HasInterrupt;
    }

    /**
     * @return the ProcessSwitch
     */
    public boolean isProcessSwitch() {
        return ProcessSwitch;
    }

    /**
     * @param ProcessSwitch the ProcessSwitch to set
     */
    public void setProcessSwitch(boolean ProcessSwitch) {
        this.ProcessSwitch = ProcessSwitch;
    }

    /**
     * @return the QuantumWorking
     */
    public boolean isQuantumWorking() {
        return QuantumWorking;
    }

    /**
     * @param QuantumWorking the QuantumWorking to set
     */
    public void setQuantumWorking(boolean QuantumWorking) {
        this.QuantumWorking = QuantumWorking;
    }

    /**
     * @return the ActualizarInterfaz
     */
    public boolean isActualizarInterfaz() {
        return ActualizarInterfaz;
    }

    /**
     * @param ActualizarInterfaz the ActualizarInterfaz to set
     */
    public void setActualizarInterfaz(boolean ActualizarInterfaz) {
        this.ActualizarInterfaz = ActualizarInterfaz;
    }

    /**
     * @return the tick
     */
    public boolean isTick() {
        return tick;
    }

    /**
     * @param tick the tick to set
     */
    public void setTick(boolean tick) {
        this.tick = tick;
    }
    
    
}
