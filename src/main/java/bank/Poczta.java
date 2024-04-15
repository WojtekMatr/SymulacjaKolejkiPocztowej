package bank;

import jstk.random.RNGenerator;
import jstk.statmon.MonitoredVariable;
import jstk.statmon.TimedMonitoredVariable;

import java.util.ArrayList;
import java.util.LinkedList;

public class Poczta {
    RNGenerator random;
    double lambda;
    double mi;

    double a, b;

    double p;


    LinkedList<Interestant> kolejka;
    int maxKolejka;

    ArrayList<Interestant> okienka;
    int ileOkienek;


    TimedMonitoredVariable<Integer> dlugoscKolejki;
    MonitoredVariable<Double> czasPrzebywania;
    TimedMonitoredVariable<Integer> zajeteOkienka;

    MonitoredVariable<Integer> obsluzeni;

    public Poczta() {

        this.random = new RNGenerator();
        this.lambda = 1;
        this.mi = 0.33;
        this.a = 5;
        this.b = 15;
        this.p = 0.25;

        this.kolejka = new LinkedList<>();
        this.maxKolejka = 10;

        this.okienka = new ArrayList<>();
        this.ileOkienek = 5;

        for (int i = 0; i < ileOkienek; i++) {
            okienka.add(null);
        }

        this.dlugoscKolejki = new TimedMonitoredVariable<Integer>(0, 0);
        this.czasPrzebywania = new MonitoredVariable<Double>(0.0);
        this.zajeteOkienka = new TimedMonitoredVariable<Integer>(0,0);
        this.obsluzeni = new MonitoredVariable<Integer>(0);
    }

    String stanSystemu(){
        return "Dlugosc kolejki: " + dlugoscKolejki.getValue() + "\tzajeteOkienka: " + zajeteOkienka.getValue();
    }

}
