package com.good.matching;

import java.util.ArrayList;

import com.good.matching.model.MatchingDTO;
/**
 * ScoreMatcher 클래스는 사용자와 다른 엔티티 간의 매칭 점수를 계산하는 메소드를 제공합니다.
 * 이 클래스는 매칭 시스템에서 사용자의 선호도와 기업 또는 기타 엔티티의 속성을 비교하여 매칭 점수를 산출합니다.
 */
public class ScoreMatcher {
	/**
     * 주어진 리스트의 MatchingDTO 객체들과 사용자의 MatchingDTO 객체를 비교하여 매칭 점수를 계산합니다.
     * 이 메소드는 각 항목(연봉, 복지, 안정성, 문화, 잠재력)의 차이를 계산하여 전체 매칭 점수를 산출합니다.
     *
     * @param list 사용자와 비교될 MatchingDTO 객체들의 리스트
     * @param userDTO 사용자의 MatchingDTO 객체
     */
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
