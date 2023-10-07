package labs;

//Denna klass �rver Proc, det g�r att man kan anv�nda time och signalnamn utan punktnotation
class Gen extends Proc {
	// Generatorn har tv� parametrar:
	public Proc sendTo; // Anger till vilken process de genererade kunderna ska skickas
	public double lambda; // Hur m�nga per sekund som ska generas

	// H�r nedan anger man vad som ska g�ras n�r en signal kommer
	public void TreatSignal(Signal signal) {
		switch (signal.signalType) {
			case READY -> {
				SignalList.SendSignal(ARRIVAL, sendTo, time);
				SignalList.SendSignal(READY, this, time + (2.0 / lambda) * rn.nextDouble());
			}
		}
	}
}