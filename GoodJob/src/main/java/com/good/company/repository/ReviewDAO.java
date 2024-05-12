package com.good.company.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.good.board.model.StudyDTO;
import com.good.company.model.ReviewDTO;
import com.test.util.DBUtil;

public class ReviewDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public ReviewDAO() {
		this.conn = DBUtil.open();
	}

	public void close() {
		try {
			this.conn.close();
		} catch (Exception e) {
			System.out.println("ReviewDAO.close 오류");
			e.printStackTrace();
		}
	}

	/**
	 * 지유)등록된 리뷰수 불러오는 메서드
	 * 
	 * @return 리뷰수
	 */
	public int countRiview(String input) {
		try {
			String sql = "select count(*) as cnt from tblCompanyReview where cp_seq = ?";

			pstat = conn.prepareStatement(sql);
			String cp_seq = input;

			pstat.setString(1, cp_seq);
			rs = pstat.executeQuery();

			while (rs.next()) {
				int cnt = rs.getInt("cnt");
				return cnt;
			}

		} catch (Exception e) {
			System.out.println("ReviewDAO.countRiview");
			e.printStackTrace();
		}

		return 0;
	}


	/**
	 * 지유)기업리뷰 목록 조회하는 메서드
	 * 
	 * @param cp_seq
	 * @return
	 */
	public ArrayList<ReviewDTO> listReview(String cp_seq) {
		try {
			String sql = "select * from vwCompanyReview where cp_seq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, cp_seq);

			rs = pstat.executeQuery();

			ArrayList<ReviewDTO> listReview = new ArrayList<ReviewDTO>();
			while (rs.next()) {
				ReviewDTO dto = new ReviewDTO();

				dto.setCp_seq(rs.getString("cp_seq"));
				dto.setId(rs.getString("id"));
				dto.setCp_rv_seq(rs.getString("cp_rv_seq"));
				dto.setSalary_score(rs.getDouble("salary_score"));
				dto.setWelfare_score(rs.getDouble("welfare_score"));
				dto.setStability_score(rs.getDouble("stability_score"));
				dto.setCulture_score(rs.getDouble("culture_score"));
				dto.setGrowth_score(rs.getDouble("growth_score"));
				dto.setLinereview(rs.getString("linereview"));
				dto.setGood(rs.getString("good"));
				dto.setBad(rs.getString("bad"));
				dto.setCp_rv_regdate(rs.getString("cp_rv_regdate"));
				dto.setCp_rv_confirm(rs.getInt("cp_rv_confirm"));
				dto.setNickname(rs.getString("nickname"));

				listReview.add(dto);
			}
			return listReview;

		} catch (Exception e) {
			System.out.println("ReviewDAO.listReview");
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 지유)기업별 태그리스트를 호출하는 메서드
	 * 
	 * @param cp_seq
	 * @return
	 */
	public ArrayList<ReviewDTO> tagList(String cp_seq) {
		ArrayList<ReviewDTO> tagList = new ArrayList<>();
		String sql = "SELECT cr.cp_seq, t.tag_keyword, COUNT(*) as tag_count " + "FROM tblCompanyReview cr "
				+ "JOIN tblReviewTag rt ON cr.cp_rv_seq = rt.cp_rv_seq " + "JOIN tblTag t ON t.tag_seq = rt.tag_seq "
				+ "WHERE cr.cp_seq = ? " + "GROUP BY cr.cp_seq, t.tag_keyword " + "ORDER BY tag_count DESC";

		try (PreparedStatement pstat = conn.prepareStatement(sql)) {
			pstat.setString(1, cp_seq);
			try (ResultSet rs = pstat.executeQuery()) {
				while (rs.next()) {
					ReviewDTO dto = new ReviewDTO();
					dto.setCp_seq(rs.getString("cp_seq"));
					ArrayList<String> tlist = new ArrayList<>();
					tlist.add(rs.getString("tag_keyword"));
					dto.setTag_list(tlist);
					tagList.add(dto);
				}
			}
		} catch (Exception e) {
			System.out.println("ReviewDAO.tagList");
			e.printStackTrace();
		}
		return tagList;
	}

	/**
	 * 등록수 기준 상위 태그 조회 메서드
	 * 
	 * @return
	 */
	public ArrayList<String> showTagList() {
		try {
			ArrayList<String> showTagList = new ArrayList<>();
			String sql = "select tag_keyword from (select * from tblTag t left join (select tag_seq ,count(*) as cnt from tblreviewTag group by tag_seq order by cnt desc) rt on t.tag_seq = rt.tag_seq) where rownum <=10";

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			while (rs.next()) {
				showTagList.add(rs.getString("tag_keyword"));
			}

			return showTagList;

		} catch (Exception e) {
			System.out.println("ReviewDAO.showTagList");
			e.printStackTrace();
			return null;
		}
	}

	public int addReview(ReviewDTO bdto) {
		try {
			String sql = "insert into tblCompanyReview "
					+ "(CP_RV_SEQ,SALARY_SCORE,WELFARE_SCORE,STABILITY_SCORE,CULTURE_SCORE,GROWTH_SCORE,LINEREVIEW,GOOD,BAD,CP_RV_REGDATE,CP_SEQ,ID,CP_RV_CONFIRM) VALUES "
					+ "(SEQCOMPANYREVIEW.nextVal,?,?,?,?,?,?,?,?,sysdate,?,?,0)"; // cp_rv_confirm=0(대기)

			pstat = conn.prepareStatement(sql);
			pstat.setDouble(1, bdto.getSalary_score());
			pstat.setDouble(2, bdto.getWelfare_score());
			pstat.setDouble(3, bdto.getStability_score());
			pstat.setDouble(4, bdto.getCulture_score());
			pstat.setDouble(5, bdto.getGrowth_score());
			pstat.setString(6, bdto.getLinereview());
			pstat.setString(7, bdto.getGood());
			pstat.setString(8, bdto.getBad());
			pstat.setString(9, bdto.getCp_seq());
			pstat.setString(10, bdto.getId());

			return pstat.executeUpdate();
		} catch (Exception e) {
			System.out.println("ReivewDAO.addReview");
			e.printStackTrace();
		}
		return 0;
	}


	public ReviewDTO getReviewByCpRvSeq(String cp_rv_seq) {
		try {
			String sql = "SELECT * FROM tblCompanyReview WHERE cp_rv_seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, cp_rv_seq);
			rs = pstat.executeQuery();

			if (rs.next()) {
				ReviewDTO dto = new ReviewDTO();
				dto.setCp_seq(rs.getString("cp_seq"));
				dto.setId(rs.getString("id"));
				dto.setCp_rv_seq(rs.getString("cp_rv_seq"));
				dto.setSalary_score(rs.getDouble("salary_score"));
				dto.setWelfare_score(rs.getDouble("welfare_score"));
				dto.setStability_score(rs.getDouble("stability_score"));
				dto.setCulture_score(rs.getDouble("culture_score"));
				dto.setGrowth_score(rs.getDouble("growth_score"));
				dto.setLinereview(rs.getString("linereview"));
				dto.setGood(rs.getString("good"));
				dto.setBad(rs.getString("bad"));
				dto.setCp_rv_regdate(rs.getString("cp_rv_regdate"));
				dto.setCp_rv_confirm(rs.getInt("cp_rv_confirm"));

				return dto;
			}
		} catch (Exception e) {
			System.out.println("ReviewDAO.getReviewByCpRvSeq");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 관리자 리뷰 승인여부 변경 메서드
	 * 
	 * @param cp_seq
	 * @param confirm
	 */
	public boolean updateReviewConfirm(String cp_rv_seq, int confirm) {
		try {
			String sql = "UPDATE tblCompanyReview SET cp_rv_confirm = ? WHERE cp_rv_seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, confirm);
			pstat.setString(2, cp_rv_seq);
			int result = pstat.executeUpdate();
			return result > 0;
		} catch (Exception e) {
			System.out.println("ReviewDAO.updateReviewConfirm()");
			e.printStackTrace();
		} finally {
			close();
		}
		return false;
	}

	/**
	 * 리뷰 삭제
	 * 
	 * @param cp_seq
	 * @param id
	 */
	public void deleteReview(String cp_rv_seq) {
		try {

			String sql ="";
			sql = "delete from tblReviewTag where cp_rv_seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, cp_rv_seq);
			pstat.executeUpdate();

			sql = "delete from tblCompanyReview where cp_rv_seq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, cp_rv_seq);
			pstat.executeUpdate();






		} catch (Exception e) {
			System.out.println("ReviewDAO.deleteReview");
			e.printStackTrace();
		}
	}

	/**
	 * 리뷰 수정
	 * 
	 * @param rdto
	 */
	public void updateReviewByCpRvSeq(ReviewDTO rdto) {
		try {
			String sql = "UPDATE tblCompanyReview SET "
					+ "salary_score = ?, welfare_score = ?, stability_score = ?, culture_score = ?, growth_score = ?, "
					+ "linereview = ?, good = ?, bad = ? " + "WHERE cp_rv_seq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setDouble(1, rdto.getSalary_score());
			pstat.setDouble(2, rdto.getWelfare_score());
			pstat.setDouble(3, rdto.getStability_score());
			pstat.setDouble(4, rdto.getCulture_score());
			pstat.setDouble(5, rdto.getGrowth_score());
			pstat.setString(6, rdto.getLinereview());
			pstat.setString(7, rdto.getGood());
			pstat.setString(8, rdto.getBad());
			pstat.setString(9, rdto.getCp_rv_seq());

			pstat.executeUpdate();
		} catch (Exception e) {
			System.out.println("ReviewDAO.updateReviewByCpRvSeq");
			e.printStackTrace();
		}
	}

	public ArrayList<ReviewDTO> myReview(HashMap<String, String> map) {
		try {
			String sql = String.format(
					"select * from (select a.*, rownum as rnum from (select * from vwmyreview where id = '%s' order by cp_rv_regdate desc) a) where rnum between %s and %s",
					map.get("id"), map.get("begin"), map.get("end"));

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			ArrayList<ReviewDTO> list = new ArrayList<>();
			while (rs.next()) {
				ReviewDTO dto = new ReviewDTO();
				dto.setCp_seq(rs.getString("cp_seq"));
				dto.setCp_name(rs.getString("cp_name"));
				dto.setId(rs.getString("id"));
				dto.setRnum(rs.getString("rnum"));
				dto.setCp_rv_seq(rs.getString("cp_rv_seq"));
				dto.setLinereview(rs.getString("linereview"));
				dto.setCp_rv_regdate(rs.getString("cp_rv_regdate"));
				dto.setCp_rv_confirm(rs.getInt("cp_rv_confirm"));

				list.add(dto);
			}
			return list;

		} catch (Exception e) {
			System.out.println("ReviewDAO.myReview");
			e.printStackTrace();
		}
		return null;
	}

	public int getCount(String id) {
		try {
			String sql = "select count(*) as cnt from vwmyreview where id = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, id);

			rs = pstat.executeQuery();

			if (rs.next()) {
				return rs.getInt("cnt");
			}

		} catch (Exception e) {
			System.out.println("ReviewDAO.getCount");
			e.printStackTrace();
		}
		return 0;
	}


	public int getPendingInterviewCount() {

		try {

			String sql = "select count(*) as cnt from tblInterview where itv_confirm = 0";

			stat = conn.createStatement();
			rs= stat.executeQuery(sql);

			if(rs.next()) {
				return rs.getInt("cnt");
			}

		} catch (Exception e) {
			System.out.println("(관리자) 면접후기 승인 대기 수 로드 실패");
			e.printStackTrace();
		}

		return 0;


	}

	public int getPendingReviewsCount() {

		try {

			String sql = "select count(*) as cnt from tblCompanyReview where cp_rv_confirm = 0 ";

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			if (rs.next()) {
				return rs.getInt("cnt");
			}

		} catch (Exception e) {
			System.out.println("(관리자) 승인대기 리뷰수 로드 실패");
			e.printStackTrace();
		}

		return 0;

	}

	/**
	 * 모든 리뷰 목록을 출력하는 메서드
	 * @return
	 */
	public ArrayList<ReviewDTO> getAllReviews() {

		try {

			String sql = "select * from vwAllReview";

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			ArrayList<ReviewDTO> list = new ArrayList<>();

			while (rs.next()) {
				ReviewDTO dto = new ReviewDTO();
				dto.setCp_rv_seq(rs.getString("cp_rv_seq"));
				dto.setId(rs.getString("id"));
				dto.setSalary_score(rs.getDouble("salary_score"));
				dto.setWelfare_score(rs.getDouble("welfare_score"));
				dto.setStability_score(rs.getDouble("stability_score"));
				dto.setCulture_score(rs.getDouble("culture_score"));
				dto.setGrowth_score(rs.getDouble("growth_score"));
				dto.setLinereview(rs.getString("linereview"));
				dto.setGood(rs.getString("good"));
				dto.setBad(rs.getString("bad"));
				dto.setCp_rv_regdate(rs.getString("cp_rv_regdate"));
				dto.setCp_rv_confirm(rs.getInt("cp_rv_confirm"));
				dto.setCp_name(rs.getString("cp_name"));

				list.add(dto);
			}
			return list;	

		}catch (Exception e) {
			System.out.println("ReviewDAO.getAllReviews");
			e.printStackTrace();
		}

		return null;
	}
	public boolean insertRejectReview(String cp_rv_seq, String rejectReason) {
		try {
			String sql = "INSERT INTO tblRejectReview (cp_rv_seq, rj_reason) VALUES (?, ?)";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, cp_rv_seq);
			pstat.setString(2, rejectReason);
			int result = pstat.executeUpdate();
			return result > 0;
		} catch (Exception e) {
			System.out.println("ReviewDAO.insertRejectReview()");
			e.printStackTrace();
		} finally {
			close();
		}
		return false;
	}
	public ArrayList<String> showComTagList() {
		try {
			ArrayList<String> showTagList = new ArrayList<>();
			String sql = "SELECT tag_keyword FROM tblTag";

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			while (rs.next()) {
				showTagList.add(rs.getString("tag_keyword"));
			}

			return showTagList;

		} catch (Exception e) {
			System.out.println("ReviewDAO.showTagList");
			e.printStackTrace();
			return null;
		}
	}

	//태그 등록
	public String getLastInsertedCpRvSeq() {
		try {
			String sql = "SELECT cp_rv_seq FROM tblCompanyReview WHERE ROWNUM = 1 ORDER BY cp_rv_seq DESC";
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("cp_rv_seq");
			}
		} catch (Exception e) {
			System.out.println("ReviewDAO.getLastInsertedCpRvSeq");
			e.printStackTrace();
		}
		return null;
	}


}
