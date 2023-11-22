package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Holgersson {

	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };

	public static void main(String[] args) throws FileNotFoundException {
		long time1 = System.nanoTime();
		ArrayList<TextProcessor> wordList = new ArrayList<>();
		Set<String> nonCountedWords = new HashSet<String>();
		
		wordList.add(new SingleWordCounter("nils"));
		wordList.add(new SingleWordCounter("norge"));
		wordList.add(new MultiWordCounter(REGIONS));
		wordList.add(new GeneralWordCounter(nonCountedWords));
		
		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning
		Scanner undantag = new Scanner(new File("undantagsord.txt"));
		undantag.useDelimiter(" ");
		
		while(undantag.hasNext()) {
			String word = undantag.next().toLowerCase();
			nonCountedWords.add(word);
		}
		
		while (s.hasNext()) {
			String word = s.next().toLowerCase();
			for (int i = 0; i < wordList.size(); i++) {
				wordList.get(i).process(word);
			}
		}

		s.close();
		undantag.close();
		
		for(int i = 0; i < wordList.size(); i++) {
			wordList.get(i).report();
		}
		
		long time2 = System.nanoTime();
		System.out.println("Tid: " + (time2 - time1)/ + 1000000000.0 + " sekunder");
	}
}