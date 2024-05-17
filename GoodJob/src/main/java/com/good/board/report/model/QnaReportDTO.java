package com.good.board.report.model;

import lombok.Getter;

/**
 * QnA 게시판 신고 정보를 담는 DTO 클래스입니다.
 */
@Getter
public class QnaReportDTO {
	private final String qna_rp_seq;
	private final String qna_rp_regdate;
	private final String qna_seq;
	private final String rp_seq;
	private final String id;
	private final String qna_rp_detail;

	/**
     * QnaReportDTO 객체의 생성자입니다.
     *
     * @param builder QnaReportDTO.Builder 객체
     */
	private QnaReportDTO(Builder builder) {
		this.qna_rp_seq = builder.qna_rp_seq;
		this.qna_rp_regdate = builder.qna_rp_regdate;
		this.qna_seq = builder.qna_seq;
		this.rp_seq = builder.rp_seq;
		this.id = builder.id;
		this.qna_rp_detail = builder.qna_rp_detail;
	}

	/**
     * QnaReportDTO 객체를 생성하는 Builder 클래스입니다.
     */
	public static class Builder {
		private String qna_rp_seq;
		private String qna_rp_regdate;
		private String qna_seq;
		private String rp_seq;
		private String id;
		private String qna_rp_detail;

		public Builder withQna_Rp_Seq(String qna_rp_seq) {
			this.qna_rp_seq = qna_rp_seq;
			return this;
		}

		public Builder withQna_Rp_Regdate(String qna_rp_regdate) {
			this.qna_rp_regdate = qna_rp_regdate;
			return this;
		}

		public Builder withQna_Seq(String qna_seq) {
			this.qna_seq = qna_seq;
			return this;
		}

		public Builder withRp_Seq(String rp_seq) {
			this.rp_seq = rp_seq;
			return this;
		}

		public Builder withId(String id) {
			this.id = id;
			return this;
		}

		public Builder withQna_Rp_Detail(String qna_rp_detail) {
			this.qna_rp_detail = qna_rp_detail;
			return this;
		}

		/**
         * QnaReportDTO 객체를 생성합니다.
         *
         * @return 생성된 QnaReportDTO 객체
         */
		public QnaReportDTO build() {
			return new QnaReportDTO(this);
		}
	}
}
