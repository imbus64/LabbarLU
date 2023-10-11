package labs;

import java.util.*;

class State extends GlobalSimulation{
	
	public int nrQueueA = 0, nrQueueB = 0, accumulated = 0, noMeasurements = 0;
	
	private EventList myEventList;

	Random slump = new Random();
	
	State(EventList x){
		myEventList = x;
	}
	
	private void InsertEvent(int event, double timeOfEvent){
		myEventList.InsertEvent(event, timeOfEvent);
	}
	
	public void TreatEvent(Event x){
		switch (x.eventType){
			case ARRIVAL_A:
					arrivalA();
				break;
			case ARRIVAL_B:
					arrivalB();
				break;
			case READY_A:
					readyA();
				break;
			case READY_B:
					readyB();
				break;
			case MEASURE:
				measure();
				break;
		}
	}

	// Rule at arrival N:= N +1; If N = 1 then add departure to event list; Add a new arrival to event list;
	private void arrivalA() {
		if (nrQueueA + nrQueueB == 0)
			InsertEvent(READY_A, time + 0.002);
		nrQueueA++;
		InsertEvent(ARRIVAL_A, time + generateMean(1.0/150.0));
	}

	// Rule at departure N := N -1; If N > 0 then add departure to event list;
	private void readyA() {
		nrQueueA--;
		if (nrQueueB > 0)
			InsertEvent(READY_B, time + 0.004);
		else if (nrQueueA > 0)
			InsertEvent(READY_A, time + 0.002);
		InsertEvent(ARRIVAL_B, time + 1.0);
	}

	private void arrivalB() {
		if (nrQueueB + nrQueueA == 0)
			InsertEvent(READY_B, time + 0.004);
		nrQueueB++;
	}

	private void readyB() {
		nrQueueB--;
		if (nrQueueB > 0)
			InsertEvent(READY_B, time + 0.004);
		else if (nrQueueA > 0)
			InsertEvent(READY_A, time + 0.002);
	}
	
	// Rule at measurement Write(N); Add a new measurement to event list;
	private void measure(){
		accumulated = accumulated + nrQueueA + nrQueueB;
		noMeasurements++;
		InsertEvent(MEASURE, time + 0.1);
	}

	private double generateMean(double mean){
		return 2*mean*slump.nextDouble();
	}
}