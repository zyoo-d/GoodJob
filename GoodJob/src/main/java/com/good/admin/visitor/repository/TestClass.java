package com.good.admin.visitor.repository;

import java.util.Calendar;

public class TestClass {
	
	public static void main(String[] args) {
		
		
//		LocalDate nowDate = LocalDate.now();
//
//		VisitorDAO dao = new VisitorDAO();
//		dao.insertTblVisitor(nowDate);
		
		 Calendar calendar = Calendar.getInstance();
	        calendar.set(2024, Calendar.JANUARY, 1);

	        // 2024년 1월 1일부터 5월 5일까지 루프를 돌면서 INSERT 문 출력
	        for (int i = 0; i < 126; i++) {
	            // INSERT 문 출력
	            System.out.println("INSERT INTO tblVisitor VALUES (to_date('" +
	                    formatDate(calendar.getTime()) + "','yyyy-mm-dd'), " +
	                    generateRandomNumber(50, 300) + ");");

	            // 다음 날짜로 이동
	            calendar.add(Calendar.DAY_OF_YEAR, 1);
	        }

	}//main
	
	
	// 날짜를 "yyyy-mm-dd" 형식으로 포맷팅하는 메서드
    private static String formatDate(java.util.Date date) {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    // 지정된 범위 내에서 랜덤한 숫자를 생성하는 메서드
    private static int generateRandomNumber(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }

}
