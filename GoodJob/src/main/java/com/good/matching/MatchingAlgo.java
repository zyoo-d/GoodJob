package com.good.matching;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MatchingAlgo 클래스는 매칭 알고리즘을 구현하는 데 사용됩니다. 이 클래스는 점수에 따른 정렬과 같은 기능을 제공하여, 매칭
 * 시스템에서 특정 기준에 따라 항목들을 정렬할 수 있게 합니다.
 */
public class MatchingAlgo {

	/**
	 * 점수 맵을 입력받아 값에 따라 내림차순으로 정렬하고, 키를 배열로 반환합니다. 이 메소드는 매칭 점수에 따라 항목들을 정렬하는 데
	 * 사용됩니다.
	 *
	 * @param scoreMap 항목과 해당 점수를 담고 있는 HashMap
	 * @return 점수가 높은 순서대로 정렬된 키의 배열
	 */
	public String[] sort(HashMap<String, Integer> scoreMap) {

		List<Map.Entry<String, Integer>> list = new ArrayList<>(scoreMap.entrySet());
		list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

		String[] columnNames = new String[list.size()];

		for (int i = 0; i < list.size(); i++) {

			columnNames[i] = list.get(i).getKey();

		}

		return columnNames;

	}

}
