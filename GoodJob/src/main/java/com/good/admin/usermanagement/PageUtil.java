package com.good.admin.usermanagement;

import lombok.Data;

@Data
public class PageUtil {
    private int totalCount; // 전체 데이터 개수
    private int pageSize; // 한 페이지에 표시할 데이터 개수
    private int currentPage; // 현재 페이지 번호
    private int totalPages; // 전체 페이지 수
    private int startPage; // 시작 페이지 번호
    private int endPage; // 끝 페이지 번호

    public PageUtil(int totalCount, int pageSize, int currentPage) {
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        calculatePageInfo();
    }

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

    public boolean hasPreviousPage() {
        return currentPage > 1;
    }

    public boolean hasNextPage() {
        return currentPage < totalPages;
    }

    public int calculateStartIndex() {
        return (currentPage - 1) * pageSize;
    }

    public int calculateEndIndex() {
        int endIndex = calculateStartIndex() + pageSize;
        return (endIndex > totalCount) ? totalCount : endIndex;
    }

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