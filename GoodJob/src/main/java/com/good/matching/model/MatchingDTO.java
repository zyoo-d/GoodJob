package com.good.matching.model;

import lombok.Data;

@Data
public class MatchingDTO implements Comparable<MatchingDTO> {
	
	private String cp_seq;
	private int salary;
	private int welfare;
	private int stability;
	private int culture;
	private int potential;
	private double matchScore;
	@Override
	public int compareTo(MatchingDTO other) {
		
		return Double.compare(this.matchScore, other.matchScore);
		
	}
	

}
