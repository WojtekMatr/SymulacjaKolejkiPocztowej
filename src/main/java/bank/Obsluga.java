package bank;

import jstk.simcore.proc.Simulation;
import jstk.simcore.proc.SimulationTerminationException;

public class Obsluga extends Simulation.Process {

    Poczta p;
    Interestant interesant;
    double dt;

    public Obsluga(Poczta p, Interestant interesant) {

        this.p = p;
        this.interesant = interesant;

        this.dt = p.random.exponential(p.mi);
    }

    public void body() {

        System.out.println(" " + simTime() + " Rozpoczęcie obsługi interesanta " + interesant.identyfikator + "  " + p.stanSystemu());



        try {
            WAIT_DURATION(dt);
        } catch (SimulationTerminationException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        int pozycja = -1;

        if (p.okienka.contains(interesant)) {
            pozycja = p.okienka.indexOf(interesant);

            if (p.random.binomial(p.p, 1) == 1) {
                p.okienka.set(pozycja, null);

                System.out.println(" " + simTime() + " Koniec obsługi interesanta " + interesant.identyfikator + "  " + p.stanSystemu());

                p.obsluzeni.setValue(p.obsluzeni.getValue() + 1);

                p.czasPrzebywania.setValue(simTime() - interesant.czasWejscia);



            } else {

                p.okienka.set(pozycja, null);

                if (p.kolejka.size() < p.maxKolejka)
                {
                    p.kolejka.add(interesant);

                    System.out.println(" " + simTime() + " Koniec obsługi interesanta i odeslanie do z powrotem do kolejki " + interesant.identyfikator + "  " + p.stanSystemu());
                }
                else
                {
                    System.out.println(" " + simTime() + " Koniec obsługi interesanta i odeslanie do z powrotem do kolejki (opuscil poczte) " + interesant.identyfikator + "  " + p.stanSystemu());

                    p.czasPrzebywania.setValue(simTime() - interesant.czasWejscia);
                }


                new Zniecierpliwienie(p, interesant);

            }

            // POBRANIE KOLEJNEGO

            if ( !p.kolejka.isEmpty())
            {
                Interestant nowy = p.kolejka.removeFirst();

                new Obsluga(p, nowy);

                p.okienka.set(p.okienka.indexOf(null), nowy);

                System.out.println(" " + simTime() + "Rozpoczęcie obsługi interesanta " + interesant.identyfikator + "  " + p.stanSystemu());
            }
            else
            {
                p.zajeteOkienka.setValue(p.zajeteOkienka.getValue() - 1, simTime());
            }

            p.dlugoscKolejki.setValue(p.kolejka.size(), simTime());

        }
    }
}