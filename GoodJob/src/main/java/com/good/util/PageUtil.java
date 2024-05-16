package com.good.util;

import lombok.Data;

/**
* PageUtil 클래스는 페이징 처리에 필요한 정보를 계산하고 제공하는 유틸리티 클래스입니다.
* Lombok의 @Data 애노테이션을 사용하여 게터, 세터, toString, equals, hashCode 메서드를 자동으로 생성합니다.
*/
@Data
public class PageUtil {
	/**
	    * 전체 데이터 개수
	    */
	   private int totalCount;

	   /**
	    * 한 페이지에 표시할 데이터 개수
	    */
	   private int pageSize;

	   /**
	    * 현재 페이지 번호
	    */
	   private int currentPage;

	   /**
	    * 전체 페이지 수
	    */
	   private int totalPages;

	   /**
	    * 시작 페이지 번호
	    */
	   private int startPage;

	   /**
	    * 끝 페이지 번호
	    */
	   private int endPage;


    /**
     * PageUtil 생성자입니다.
     * 전체 데이터 개수, 페이지 크기, 현재 페이지 번호를 입력받아 페이징 정보를 계산합니다.
     *
     * @param totalCount  전체 데이터 개수
     * @param pageSize    한 페이지에 표시할 데이터 개수
     * @param currentPage 현재 페이지 번호
     */
    public PageUtil(int totalCount, int pageSize, int currentPage) {
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        calculatePageInfo();
    }

    /**
     * 페이징 정보를 계산하는 메서드입니다.
     * 전체 페이지 수, 시작 페이지 번호, 끝 페이지 번호를 계산합니다.
     */
    private void calculatePageInfo() {
        // 전체 페이지 수 계산
        totalPages = (int) Math.ceil((double) totalCount / pageSize);

        // 시작 페이지 번호 계산
        startPage = ((currentPage - 1) / 5) * 5 + 1;

        // 끝 페이지 번호 계산
        endPage = startPage + 4;
        if (endPage > totalPages) {
            endPage = totalPages;
        }
    }

    /**
     * 이전 페이지가 있는지 확인하는 메서드입니다.
     *
     * @return 이전 페이지가 있으면 true, 없으면 false
     */
    public boolean hasPreviousPage() {
        return currentPage > 1;
    }

    /**
     * 다음 페이지가 있는지 확인하는 메서드입니다.
     *
     * @return 다음 페이지가 있으면 true, 없으면 false
     */
    public boolean hasNextPage() {
        return currentPage < totalPages;
    }

    /**
     * 현재 페이지의 시작 인덱스를 계산하는 메서드입니다.
     *
     * @return 현재 페이지의 시작 인덱스
     */
    public int calculateStartIndex() {
        return (currentPage - 1) * pageSize;
    }

    /**
     * 현재 페이지의 끝 인덱스를 계산하는 메서드입니다.
     *
     * @return 현재 페이지의 끝 인덱스
     */
    public int calculateEndIndex() {
        int endIndex = calculateStartIndex() + pageSize;
        return (endIndex > totalCount) ? totalCount : endIndex;
    }

    /**
     * 요청 파라미터에서 현재 페이지 번호를 파싱하는 메서드입니다.
     *
     * @param pageParam 현재 페이지 번호 파라미터
     * @return 파싱된 현재 페이지 번호 (기본값: 1)
     */
    public static int parseCurrentPage(String pageParam) {
        int currentPage = 1;
        if (pageParam != null && !pageParam.isEmpty()) {
            try {
                currentPage = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                currentPage = 1;
            }
        }
        return currentPage;
    }
}