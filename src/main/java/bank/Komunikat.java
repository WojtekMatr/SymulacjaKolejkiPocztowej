package bank;

import jstk.simcore.proc.Simulation;
import jstk.simcore.proc.SimulationTerminationException;

public class Komunikat extends Simulation.Process {

    Poczta p;
    double dt;

    public Komunikat(Poczta p) {

        this.p = p;
        this.dt = p.random.exponential(p.mi);
    }

    public void body() {
        for(int i =0; i<10 ; i++) {

            System.out.println(" " + (500 - simTime()) + " Pozostaly czas dzialania programu ");


            try {
                WAIT_DURATION(dt);
            } catch (SimulationTerminationException e) {

                System.out.println(e.getMessage());
                e.printStackTrace();
            }

        }

    }

    }

