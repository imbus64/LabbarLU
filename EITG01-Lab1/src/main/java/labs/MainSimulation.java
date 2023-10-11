package labs;

import java.io.*;

public class MainSimulation extends GlobalSimulation{
    public static void main(String[] args) throws IOException {
    	Event actEvent;
    	EventList myEventList = new EventList();
    	State actState = new State(myEventList);
		/* When the simulation is initiated Time = 0 N = 0,
		one arrival event and one measurement event has to be added to the list in order to start the simulation */
        myEventList.InsertEvent(ARRIVAL_A, 0);
        myEventList.InsertEvent(MEASURE, 0.1);
    	while (actState.noMeasurements < 1000){
    		actEvent = myEventList.FetchEvent();
    		time = actEvent.eventTime;
    		actState.TreatEvent(actEvent);
			System.out.println("Tid: " + time);
			// System.out.println("Nummer b: " + actState.nrQueueB);
			// System.out.println("Nummer a: " + actState.nrQueueA);
    	}
    	System.out.println("Mean number of customers: " + 1.0*actState.accumulated/actState.noMeasurements);
    	System.out.println("Number of measurements done: " + actState.noMeasurements);
    }
}