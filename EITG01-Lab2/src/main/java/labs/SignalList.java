package labs;

import java.util.PriorityQueue;

// Static data members are a crime against humanity
public class SignalList {
	// See comparator in Signal.java
	static PriorityQueue<Signal> list = new PriorityQueue<>();

	public static void SendSignal(int type, Proc dest, double arrtime) {
		list.add(new Signal(type, dest, arrtime));
	}

	static public Signal FetchSignal() {
		return list.poll();
	}
}
