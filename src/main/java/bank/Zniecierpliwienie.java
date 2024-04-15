package bank;

import jstk.simcore.proc.Simulation;
import jstk.simcore.proc.SimulationTerminationException;

public class Zniecierpliwienie extends Simulation.Process {

    double dt;
    Interestant interesant;
    Poczta p;

    public Zniecierpliwienie(Poczta p, Interestant interesant) {
        this.dt = p.random.uniform(p.a, p.b);
        this.interesant = interesant;
        this.p = p;
    }


    public void body() {
        try {
            WAIT_DURATION(dt);
        } catch (SimulationTerminationException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        if (p.kolejka.contains(interesant)) {
            p.kolejka.remove(interesant);

            p.dlugoscKolejki.setValue(p.kolejka.size(), simTime());

            p.czasPrzebywania.setValue(simTime() - interesant.czasWejscia);

            System.out.println(" " + simTime() + " Interesant sie zniecierpliwil " + interesant.identyfikator + "  " + p.stanSystemu());
        }
    }
}
