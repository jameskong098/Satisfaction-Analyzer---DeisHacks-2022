/**
 * Benjamin Kamen
 * benjaminkamen@brandeis.edu
 * February 6, 2022
 * SatisFaction Analyzer: DeisHacks 2022
 * Calculates magnitude score, includes several modifiers like negations and multipliers for quantitative metrics in the text
 * Known Bugs: FileNotFoundException
 */

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class CalculateMagnitude {
	
	private String[] posPhrases;
	private String[] negPhrases;
	private String[] negations = {"not", "rarely", "never", "can't", "cant", "didn't", "didnt", "not very", "rarely", "rarely feel", "don't feel"};
	private String[] releventMetrics = {"teenagers", "youths", "people", };
	File textFile;
	
	public CalculateMagnitude(File file) {
		this.textFile = file;
		posPhrases = takeFile(new File("positiveWords.txt")).split("\n");
		negPhrases = takeFile(new File("negativeWords.txt")).split("\n");
	}
	
	public double getImpact() {
		return impact(takeFile(textFile));
	}
	
	public String[] getNegations() {
		return negations;
	}
	
	public double impact(String speech) {
		return stringValue(breakSentences(speech));
	}
  
	public String takeFile(File text_txt) {
    //this method should take the text file that contains the interview text
		String parseFile = "";
		try {
			Scanner input = new Scanner(text_txt);
			while (input.hasNextLine()) {
				parseFile += input.nextLine().trim().toLowerCase() + "\n";
			}
			input.close();
			int length = parseFile.length();
			parseFile = parseFile.substring(0, length - 1);
			return parseFile;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return parseFile;
		}
	}
  
	public ArrayList<String> breakSentences(String paragraph){
    //this method should take the String text paragraph and return a list of sentences it contains
	    ArrayList<String> parced = new ArrayList<String>();
	    int place = 0;
	    for (int i=0; i < paragraph.length(); i++) {
	    	if (paragraph.charAt(i) == '.' || paragraph.charAt(i) == '?' || paragraph.charAt(i) == '!') {
	    		parced.add(paragraph.substring(place, i));
	    		place = i + 1;
	    	}
        
	    }
	    parced.add(paragraph.substring(Math.min(place, paragraph.length()-1), paragraph.length()-1));
	    return parced;
	}
    
	public double stringValue(ArrayList<String> input) {
		double states = 0;
		double numberImpacted = Findnumber(input);
		for (int i = 0; i < input.size() ; i++) {
			states += sentiment(input.get(i));
		}
		return states * numberImpacted;
  }
  

	public double sentiment(String sentence){
		int res = 0;
		for(int i=0; i < posPhrases.length; i++) {
			if (sentence.contains(posPhrases[i])) {
				if(negated(sentence, posPhrases[i])) {
					res += -2;
				}
				res ++;
			}
		}
		for (int i=0; i < negPhrases.length; i++) {
			if(sentence.contains(negPhrases[i])) {
				if(negated(sentence, negPhrases[i])){
					res += 2;
				}
				res --;
			}
          
		}
		return res;
    }
    
                                 
    public double Findnumber(ArrayList<String> paragraph){
    	for (int i=0; i < paragraph.size(); i++) {
    		if (quantititaive(paragraph.get(i))) {
    			return findint(paragraph.get(i));
    		}
    	}
    	return 1;
    }
        
    public Boolean negated(String seen, String word){
    	for (int i=0; i < negations.length; i++) {
    		if (seen.contains(negations[i] + " " + word)){
    			return true;
    		}
    	}
      return false;
    }
    
  	public double findint(String tosearch){
  		double number = 0;
  		String numb = "";
  		int count = 0;
  		for(int i=0; i < tosearch.length(); i++) {
  			if (tosearch.charAt(i) == '0' || tosearch.charAt(i) == '1' || tosearch.charAt(i) == '2' || tosearch.charAt(i) == '3' || tosearch.charAt(i) == '4' || tosearch.charAt(i) == '5' || tosearch.charAt(i) == '6' || tosearch.charAt(i) == '7' || tosearch.charAt(i) == '8' || tosearch.charAt(i) == '9') {
  				numb += tosearch.charAt(i);
  				count = 1000;
  			}	
  			else {
    		  if (count > 1) {
    			  i += 100000;
    		  }
  			}
  		}
  		if (numb != "") {
  			number = Double.parseDouble(numb);
  		} 
  		else {
  			number = 1;
  		}	
  		return number;
  	
  	}
  	
    public Boolean quantititaive(String toTest){
    	for (int i=0; i < releventMetrics.length; i++) {
    		if (toTest.contains(releventMetrics[i])) {
    			return true;
    		}
    	}
    	return false;
    }
      
    
}
 
