package bank;

import jstk.simcore.proc.Simulation;

public class Interestant {
    static int ile = 0;
    int identyfikator;

    double czasWejscia;


    public Interestant (Poczta p)
    {
        czasWejscia = Simulation.simTime();

        identyfikator = ++ile;

        new Zniecierpliwienie(p, this);
        new Sprawa(p, this);
    }



}
