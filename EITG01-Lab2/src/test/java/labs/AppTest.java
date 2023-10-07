package labs;

// import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
// import static org.junit.jupiter.api.Assertions.*;

class DemoTest extends Global {
    private double SimulationTest(double l) {
        resetGlobals(); // Explicit for clarity

        Signal actSignal;
        new SignalList();

        QS Q1 = new QS();
        Q1.sendTo = null;

        Gen Generator = new Gen();
        Generator.lambda = l; // Needs to be set on the gen object
        Generator.sendTo = Q1;

        SignalList.SendSignal(READY, Generator, time);
        SignalList.SendSignal(MEASURE, Q1, time);

        while (time < 100000) {
            actSignal = SignalList.FetchSignal();
            time = actSignal.arrivalTime;
            actSignal.destination.TreatSignal(actSignal);
        }

        return ((double) Q1.accumulated / Q1.noMeasurements);
    }

    private void resetGlobals() {
        time = 0;
    }

    // Because os the stochastic nature of the simulation, this test may fail
    @Test
    @RepeatedTest(2) // Global state, need to check twice
    void testSimulations() {
        double result = SimulationTest(9);
        assert (result > 3 && result < 4);

        result = SimulationTest(2);
        assert(result > 0.21 && result < 0.22);
    }
}
