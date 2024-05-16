package com.good.matching.model;

import com.good.company.model.CompanyDTO;

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
	private String matchText;
	
	private CompanyDTO dto;
	
	@Override
	public int compareTo(MatchingDTO other) {
		
		return Double.compare(this.matchScore, other.matchScore);
		
	}
	
	public String findMax() {
        int max = salary;
        String maxField = "salary";

        if (welfare > max) {
            max = welfare;
            maxField = "welfare";
        }
        if (stability > max) {
            max = stability;
            maxField = "stability";
        }
        if (culture > max) {
            max = culture;
            maxField = "culture";
        }
        if (potential > max) {
            max = potential;
            maxField = "potential";
        }

        return maxField;
    }

    // 최소값 찾기
    public String findMin() {
        int min = salary;
        String minField = "salary";

        if (welfare < min) {
            min = welfare;
            minField = "welfare";
        }
        if (stability < min) {
            min = stability;
            minField = "stability";
        }
        if (culture < min) {
            min = culture;
            minField = "culture";
        }
        if (potential < min) {
            min = potential;
            minField = "potential";
        }

        return minField;
    }
	

}
