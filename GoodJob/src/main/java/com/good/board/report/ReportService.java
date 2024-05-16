package com.good.board.report;

import com.good.board.report.model.LiveCmReportDTO;
import com.good.board.report.model.QnaCmReportDTO;
import com.good.board.report.model.QnaReportDTO;
import com.good.board.report.model.StdCmReportDTO;
import com.good.board.report.model.StdReportDTO;
import com.good.board.report.repository.LiveCmReportDAO;
import com.good.board.report.repository.QnaCmReportDAO;
import com.good.board.report.repository.QnaReportDAO;
import com.good.board.report.repository.ReportDAO;
import com.good.board.report.repository.StdCmReportDAO;
import com.good.board.report.repository.StdReportDAO;

/**
 * 신고 생성을 처리하는 서비스 클래스입니다.
 */
public class ReportService {

	/**
     * 새로운 신고를 생성합니다.
     *
     * @param id          신고자 ID
     * @param seq         신고 대상 게시물/댓글 번호
     * @param rp_seq      신고 사유 번호
     * @param description 신고 상세 설명
     * @param boardType   게시판 타입
     * @return 신고 생성 결과 (성공: 1, 실패: 0)
     */
	public int createReport(String id, String seq, String rp_seq, String description, String boardType) {

		ReportDAO<?> dao = null ;
		
		int result = 0;

		switch (boardType) {
		case "qna":
			dao = new QnaReportDAO();
			QnaReportDTO qnaDto = new QnaReportDTO.Builder()
					.withId(id)
					.withQna_Seq(seq)
					.withQna_Rp_Detail(description)
					.withRp_Seq(rp_seq)
					.build();
			result = ((QnaReportDAO)dao).create(qnaDto);
			break;
		case "std":
			dao = new StdReportDAO();
			StdReportDTO stdDto = new StdReportDTO.Builder()
					.withId(id)
					.withRp_seq(rp_seq)
					.withStd_seq(seq)
					.withStd_rp_detail(description)
					.build();
			result = ((StdReportDAO)dao).create(stdDto);
			break;
		case "qna_cm":
			dao = new QnaCmReportDAO();
			QnaCmReportDTO qna_cmDto = new QnaCmReportDTO.Builder()
					.withId(id)
					.withRp_seq(rp_seq)
					.withQna_cm_seq(seq)
					.withQna_cm_rp_detail(description)
					.build();
			result = ((QnaCmReportDAO)dao).create(qna_cmDto);
			break;
		case "std_cm":
			dao = new StdCmReportDAO();
			StdCmReportDTO std_cmDto = new StdCmReportDTO.Builder()
					.withId(id)
					.withRp_seq(rp_seq)
					.withStd_cm_seq(seq)
					.withStd_cm_rp_detail(description)
					.build();
			result = ((StdCmReportDAO)dao).create(std_cmDto);
			break;
		case "live":
			dao = new LiveCmReportDAO();
			LiveCmReportDTO liveDto = new LiveCmReportDTO.Builder()
					.withId(id)
					.withRp_seq(rp_seq)
					.withLive_seq(seq)
					.withLive_cm_rp_detail(description)
					.build();
			result = ((LiveCmReportDAO)dao).create(liveDto);
			break;
		}
		
		if(dao != null) {
			dao.close();
		}
		return result;


	}

}
