package bank;

import jstk.simcore.proc.Simulation;
import jstk.simcore.proc.SimulationTerminationException;

public class Przybycie extends Simulation.Process {

    Poczta p;

    public Przybycie(Poczta p) {
        this.p = p;
    }

    @Override
    public void body() {

        int maxLiczbaPrzybyc = 1000;

        for (int i = 0; i < maxLiczbaPrzybyc; i++) {

            Interestant interesant = new Interestant(p);

            //jest puste okienko
            if (p.okienka.contains(null)) {
                for (int j = 0; j < p.ileOkienek; i++) {
                    if (p.okienka.contains(null)) {
                        p.okienka.set(p.okienka.indexOf(null), interesant);

                        new Obsluga(p, interesant);

                        System.out.println(" " + simTime() + " Przybycie i rozpoczęcie obsługi interesanta " + interesant.identyfikator + "  " + p.stanSystemu());

                        p.zajeteOkienka.setValue(p.zajeteOkienka.getValue() + 1, simTime());

                        break;
                    }
                }
            } else {
                if (p.kolejka.size() < p.maxKolejka) {
                    p.kolejka.add(interesant);

                    System.out.println(" " + simTime() + " Przybycie interesanta " + interesant.identyfikator + "  " + p.stanSystemu());
                    p.dlugoscKolejki.setValue(p.kolejka.size(), simTime());

                } else {
                    System.out.println(" " + simTime() + " Odrzucenie interesanta " + interesant.identyfikator + "  " + p.stanSystemu());
                    p.dlugoscKolejki.setValue(p.kolejka.size(), simTime());
                }
            }

            double dt = p.random.exponential(p.lambda);

            try {
                WAIT_DURATION(dt);
            } catch (SimulationTerminationException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }

    }
}