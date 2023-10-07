package labs;

import org.junit.jupiter.api.Test;
// import static org.junit.jupiter.api.Assertions.*;

class DemoTest extends Global {
    @Test void SimulationTest() {

		Signal actSignal;
		new SignalList();

		QS Q1 = new QS();
		Q1.sendTo = null;

		Gen Generator = new Gen();
		Generator.lambda = 9;
		Generator.sendTo = Q1;

		SignalList.SendSignal(READY, Generator, time);
		SignalList.SendSignal(MEASURE, Q1, time);

		while (time < 100000) {
			actSignal = SignalList.FetchSignal();
			time = actSignal.arrivalTime;
			actSignal.destination.TreatSignal(actSignal);
		}

		double result = (double) Q1.accumulated / Q1.noMeasurements;

        // Unlikely to fail, but possible
        assert(result > 3.0 && result < 4.0);
    }
}
