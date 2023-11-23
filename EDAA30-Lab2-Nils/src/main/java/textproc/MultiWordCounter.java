package textproc;

import java.util.Map;
import java.util.TreeMap;
import java.util.HashMap;

public class MultiWordCounter implements TextProcessor{
	private Map<String, Integer> map = new TreeMap<String, Integer>();
	
	public MultiWordCounter(String [] a) {
		for(String word : a) {
			this.map.put(word, 0);
		}
	}
	
	public void process(String word) {
		if(map.containsKey(word)) {
			int nbr = map.get(word);
			map.replace(word, nbr += 1);
		}
	}
	
	public void report() {
		for(String name : map.keySet()) {
			System.out.println(name + ": " + map.get(name));
		}
	}
	
}
