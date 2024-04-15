package bank;


import jstk.simcore.proc.Simulation;
import jstk.simcore.proc.SimulationTerminationException;

public class FailureAndRepairProcess extends Simulation.Process {

    ObiektAwaryjny ob;
    public FailureAndRepairProcess( ObiektAwaryjny ob) {
        this.ob = ob;
    }

    @Override
    public void body() {
        for (int i = 0; i < ob.liczbaAwarii; i++) {
            // Przygotowanie awarii okienka.
            double dt = ob.random.exponential(ob.alfa);
            try {
                WAIT_DURATION(dt);
            } catch (SimulationTerminationException e) {
                // Zakończyć proces.
                break;
            }
            ob.stanZdatności.setValue(0, simTime());
            // Wystąpiła awaria okienka.
            Simulation.log(this.getClass().getSimpleName() + " :: " + ob + " >> " + " - AWARIA");

            // Przygotowanie naprawy okienka.
            dt = ob.random.exponential(ob.beta);
            try {
                WAIT_DURATION(dt);
            } catch (SimulationTerminationException e) {
                // Zakończyć proces.
                break;
            }
            ob.stanZdatności.setValue(1, simTime());
            Simulation.log(this.getClass().getSimpleName() + " :: " + ob + " >> " + " - NAPRAWA");
        }
    }
}
