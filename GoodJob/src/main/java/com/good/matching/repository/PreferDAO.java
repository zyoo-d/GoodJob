package com.good.matching.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.test.util.DBUtil;

public class PreferDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public PreferDAO() {
		this.conn = DBUtil.open();
	}
	public void close()  {
        try {
            this.conn.close();
        } catch (Exception e) {
            System.out.println("PreferDAO.close 오류");
            e.printStackTrace();
        }
    }
}
