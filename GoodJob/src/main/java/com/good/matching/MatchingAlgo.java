package com.good.matching;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatchingAlgo {
	
	
	public void matchingAlgo(){
		
		
		
		
		
	}

	public String[] sort(HashMap<Integer, String> scoreMap) {
		
		List<Map.Entry<Integer, String>> list = new ArrayList<>(scoreMap.entrySet());
		list.sort(Map.Entry.comparingByKey(Comparator.reverseOrder()));
		
		String[] columnNames = new String[list.size()];
		
		
		
		for(int i=0; i<list.size(); i++) {
			
			columnNames[i] = list.get(i).getValue();
			
		}
		
		return columnNames;
		
		
	}
	
	

}
