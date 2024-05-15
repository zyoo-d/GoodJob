package com.good.matching.model;

import com.good.company.model.CompanyDTO;

import lombok.Data;
/**
 * 사용자와 회사 간의 매칭 정보를 담는 데이터 전송 객체입니다.
 * 이 클래스는 급여, 복지, 안정성, 기업 문화, 성장 가능성과 같은 다양한 기준에 따라 계산된 매칭 점수와 회사의 세부 정보를 저장합니다.
 * 또한, 이 클래스는 매칭 점수에 따라 객체를 비교하고 평가하는 메소드를 제공합니다.
 */
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
	/**
     * 매칭 DTO 객체를 매칭 점수에 따라 비교합니다.
     *
     * @param other 다른 MatchingDTO 객체
     * @return 이 객체의 매칭 점수가 높으면 양수, 낮으면 음수, 같으면 0을 반환
     */
	@Override
	public int compareTo(MatchingDTO other) {
		
		return Double.compare(this.matchScore, other.matchScore);
		
	}
	/**
     * 가장 높은 점수의 카테고리를 찾습니다.
     *
     * @return 가장 높은 점수를 가진 카테고리의 이름
     */
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
	/**
     * 가장 낮은 점수의 카테고리를 찾습니다.
     *
     * @return 가장 낮은 점수를 가진 카테고리의 이름
     */
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
