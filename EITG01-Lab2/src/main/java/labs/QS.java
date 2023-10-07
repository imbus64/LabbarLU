package labs;

class QS extends Proc {
	public int numberInQueue = 0, accumulated, noMeasurements;
	public Proc sendTo;

	public void TreatSignal(Signal signal) {
		switch (signal.signalType) {

			case ARRIVAL -> {
				numberInQueue++;
				if (numberInQueue == 1) SignalList.SendSignal(READY, this, time + 0.2 * rn.nextDouble());
			}
			case READY -> {
				numberInQueue--;
				if (sendTo != null) SignalList.SendSignal(ARRIVAL, sendTo, time);
				if (numberInQueue > 0) SignalList.SendSignal(READY, this, time + 0.2 * rn.nextDouble());
			}
			case MEASURE -> {
				noMeasurements++;
				accumulated += numberInQueue;
				SignalList.SendSignal(MEASURE, this, time + 2 * rn.nextDouble());
			}
		}
	}
}