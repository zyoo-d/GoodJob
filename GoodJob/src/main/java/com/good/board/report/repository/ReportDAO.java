package com.good.board.report.repository;

import java.util.ArrayList;

public interface ReportDAO{
	
	public void create();
	public <T> ArrayList<T> list();
	public void delete();
	public void view();
	public int totalCount();
}
