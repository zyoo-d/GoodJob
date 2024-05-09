package com.good.matching;

import java.util.ArrayList;

import com.good.matching.model.MatchingDTO;

public class ScoreMatcher {

	public static void calculateMatchingRate(ArrayList<MatchingDTO> list, MatchingDTO userDTO) {
	    int[] userScores = {userDTO.getSalary(), userDTO.getWelfare(), userDTO.getStability(), userDTO.getCulture(), userDTO.getPotential()};

	    for (int i = 0; i < list.size(); i++) {
	        MatchingDTO dto = list.get(i);
	        int[] dtoScores = {dto.getSalary(), dto.getWelfare(), dto.getStability(), dto.getCulture(), dto.getPotential()};

	        int totalDifference = 0;
	        int maxScore = 100;

	        for (int j = 0; j < userScores.length; j++) {
	            int difference = Math.abs(userScores[j] - dtoScores[j]);
	            totalDifference += difference;
	        }

	        double avgDifference = (double) totalDifference / (userScores.length * maxScore);
	        double matchingRate = (1 - avgDifference) * 100;
	        double roundedMatchingRate = Math.round(matchingRate * 100.0) / 100.0;

	        dto.setMatchScore(roundedMatchingRate);
	    }
	}
	
}
