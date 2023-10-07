package labs;

// Denna klass definerar vad som ska finnas i en signal. Det som finns här är ett minimum. Man kan lägga till mer
// om man vill att en signal ska kunna skicka mer information.
class Signal implements Comparable<Signal> {
	public Proc destination;
	public double arrivalTime;
	public int signalType;
	public Signal next;

	Signal(int type, Proc dest, double time) {
		signalType = type;
		destination = dest;
		arrivalTime = time;
		next = null;
	}

	// Tells various Collection classes how to sort Signals
	@Override
	public int compareTo(Signal other) {
		return Double.compare(arrivalTime, other.arrivalTime);
	}
}
