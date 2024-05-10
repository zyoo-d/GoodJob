package com.good.board.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.test.util.DBUtil;

public class TagDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public TagDAO() {
		this.conn = DBUtil.open();
	}

	public void close() {
		try {
			this.conn.close();
		} catch (Exception e) {
			System.out.println("TagDAO.close 오류");
			e.printStackTrace();
		}
	}
	public void insertTag(String tagKeyword) {
        try {
            String sql = "MERGE INTO tblTag t " +
                         "USING (SELECT ? AS tag_keyword FROM dual) s " +
                         "ON (t.tag_keyword = s.tag_keyword) " +
                         "WHEN NOT MATCHED THEN " +
                         "INSERT (t.tag_seq, t.tag_keyword) VALUES (SEQTAG.nextVal, s.tag_keyword)";
            pstat = conn.prepareStatement(sql);
            pstat.setString(1, tagKeyword);
            pstat.executeUpdate();
        } catch (Exception e) {
            System.out.println("TagDAO.insertTag");
            e.printStackTrace();
        }
    }

    public void insertReviewTag(String tagSeq, String cpRvSeq) {
        try {
            String sql = "INSERT INTO tblReviewTag (rv_tag_seq, tag_seq, cp_rv_seq) VALUES (SEQREVIEWTAG.nextVal, ?, ?)";
            pstat = conn.prepareStatement(sql);
            pstat.setString(1, tagSeq);
            pstat.setString(2, cpRvSeq);
            pstat.executeUpdate();
        } catch (Exception e) {
            System.out.println("TagDAO.insertReviewTag");
            e.printStackTrace();
        }
    }

    public String getTagSeq(String tagKeyword) {
        try {
            String sql = "SELECT tag_seq FROM tblTag WHERE tag_keyword = ?";
            pstat = conn.prepareStatement(sql);
            pstat.setString(1, tagKeyword);
            rs = pstat.executeQuery();

            if (rs.next()) {
                return rs.getString("tag_seq");
            }
        } catch (Exception e) {
            System.out.println("TagDAO.getTagSeq");
            e.printStackTrace();
        }
        return null;
    }
	
	
	
	
	
}
