package com.good.board.report.repository;

import java.time.LocalDate;
import java.util.ArrayList;

public interface ReportDAO<T>{
	
	public void close();
	public int create(T dto);
	public int getReportCountByDate(LocalDate date);
	public ArrayList<T> list();
	public void delete(String seq);
	public void view(String seq);
	public int totalCount();
	public int isReport(String id, String seq);
}
