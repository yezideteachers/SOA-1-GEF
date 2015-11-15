package fr.unice.polytech.soa1.shop3000.flows.utils;


import java.util.Map;

public class LetterWriter {

	public String write(Map<Integer, Object> m){
		String bill ="";
		for (int i = 0; i < m.size(); i++) {
			bill+=m.get(i);
		}
		bill +='\n';
		bill +="\n";
		return bill;
	}
}
