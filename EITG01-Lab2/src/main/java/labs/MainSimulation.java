package labs;

import java.io.*;

//Denna klass �rver Global s� att man kan anv�nda time och signalnamnen utan punktnotation
public class MainSimulation extends Global {
	public static void main(String[] args) throws IOException {

		// Signallistan startas och actSignal deklareras. actSignal �r den senast
		// utplockade signalen i huvudloopen nedan.
		Signal actSignal;
		new SignalList();

		// H�r nedan skapas de processinstanser som beh�vs och parametrar i dem ges
		// v�rden.
		QS Q1 = new QS();
		Q1.sendTo = null;

		Gen Generator = new Gen();
		Generator.lambda = 9; // Generator ska generera nio kunder per sekund
		Generator.sendTo = Q1; // De genererade kunderna ska skickas till k�systemet QS

		// H�r nedan skickas de f�rsta signalerna f�r att simuleringen ska komma ig�ng.
		SignalList.SendSignal(READY, Generator, time);
		SignalList.SendSignal(MEASURE, Q1, time);

		// Detta �r simuleringsloopen:
		while (time < 100000) {
			actSignal = SignalList.FetchSignal();
			time = actSignal.arrivalTime;
			actSignal.destination.TreatSignal(actSignal);
		}

		// Slutligen skrivs resultatet av simuleringen ut nedan:
		System.out.println("Medelantal kunder i k�system: " + 1.0 * Q1.accumulated / Q1.noMeasurements);
	}
}