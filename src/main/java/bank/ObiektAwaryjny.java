package bank;

import jstk.random.RNGenerator;
import jstk.statmon.TimedMonitoredVariable;

public class ObiektAwaryjny {
    TimedMonitoredVariable<Integer> stanZdatności = new TimedMonitoredVariable<>(1, 0);
    int liczbaAwarii;
    double alfa;
    double beta;

    RNGenerator random = new RNGenerator(31);

    public ObiektAwaryjny(int liczbaAwarii, double alfa, double beta) {
        this.liczbaAwarii = liczbaAwarii;
        this.alfa = alfa;
        this.beta = beta;
    }
}
