package bank;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jstk.random.RNGenerator;
import jstk.simcore.proc.Simulation;
import jstk.statmon.StatGraph;
import jstk.statmon.StatGraphType;

import java.io.IOException;

public class TestApp extends Application {
    int j = 500;
    @Override
    public void start(Stage stage) throws IOException {
        Button btn1 = new Button("Symulacja procesowa");
        btn1.setOnAction(event -> {
                    Thread thread = new Thread(() -> {
                        Simulation.init(0);

                        Simulation.setEndTime(j);



                        Poczta p = new Poczta();
                        new Przybycie(p);
                        new Komunikat(p);
                        Simulation.start();
                        Simulation.waitForCompletion();

                        System.out.println("Średnia dlugosc kolejki: " + p.dlugoscKolejki.getMean());
                        System.out.println("Średni czas przebywania: " + p.czasPrzebywania.getMean());
                        System.out.println("Średnia liczba zajętych okienek: " + p.zajeteOkienka.getMean());
                        System.out.println("Prawdopodobieńśtwo starty: " + (1 - p.obsluzeni.getValue() / Interestant.ile));

                        StatGraph d1 = new StatGraph(StatGraphType.TIME_FUNCTION, "Długość kolejki");
                        d1.addMonitoredVariable(p.dlugoscKolejki);
                        d1.show();

                        StatGraph d2 = new StatGraph(StatGraphType.DISTRIBUTION, "Dystrybunata dlugości kolejki");
                        d2.addMonitoredVariable(p.dlugoscKolejki);
                        d2.show();

                        StatGraph d3 = new StatGraph(StatGraphType.DISTRIBUTION, "Dystrybunata czasu przebywania");
                        d3.addMonitoredVariable(p.czasPrzebywania);
                        d3.show();

                        StatGraph d4 = new StatGraph(StatGraphType.HISTOGRAM, "Długość kolejki - Histogram");
                        d4.addMonitoredVariable(p.dlugoscKolejki);
                        d4.show();

                        StatGraph d5 = new StatGraph(StatGraphType.TIME_FUNCTION, "Zajęte okienka");
                        d5.addMonitoredVariable(p.zajeteOkienka);
                        d5.show();

                    });
                    thread.start();
                }
        );
        VBox root = new VBox(btn1);
        Scene scene = new Scene(root);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        System.out.println("Czas symulachyjny: " + j);
    }

    public static void main(String[] args) {
        launch();
    }
}
