package com.good.company.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.test.util.DBUtil;

public class ScrapDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public ScrapDAO() {
		this.conn = DBUtil.open();
	}
}
