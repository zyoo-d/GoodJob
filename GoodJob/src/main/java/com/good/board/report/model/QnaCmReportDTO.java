package com.good.board.report.model;

import lombok.Getter;

@Getter
public class QnaCmReportDTO {
    private final String qna_cm_rp_seq;
    private final String qna_cm_rp_regdate;
    private final String id;
    private final String rp_seq;
    private final String qna_cm_seq;
    private final String qna_cm_rp_detail;

    private QnaCmReportDTO(Builder builder) {
        this.qna_cm_rp_seq = builder.qna_cm_rp_seq;
        this.qna_cm_rp_regdate = builder.qna_cm_rp_regdate;
        this.id = builder.id;
        this.rp_seq = builder.rp_seq;
        this.qna_cm_seq = builder.qna_cm_seq;
        this.qna_cm_rp_detail = builder.qna_cm_rp_detail;
    }

    public static class Builder {
        private String qna_cm_rp_seq;
        private String qna_cm_rp_regdate;
        private String id;
        private String rp_seq;
        private String qna_cm_seq;
        private String qna_cm_rp_detail;

        public Builder withQna_cm_rp_seq(String qna_cm_rp_seq) {
            this.qna_cm_rp_seq = qna_cm_rp_seq;
            return this;
        }

        public Builder withQna_cm_rp_regdate(String qna_cm_rp_regdate) {
            this.qna_cm_rp_regdate = qna_cm_rp_regdate;
            return this;
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withRp_seq(String rp_seq) {
            this.rp_seq = rp_seq;
            return this;
        }

        public Builder withQna_cm_seq(String qna_cm_seq) {
            this.qna_cm_seq = qna_cm_seq;
            return this;
        }

        public Builder withQna_cm_rp_detail(String qna_cm_rp_detail) {
            this.qna_cm_rp_detail = qna_cm_rp_detail;
            return this;
        }

        public QnaCmReportDTO build() {
            return new QnaCmReportDTO(this);
        }
    }

    // Getters
}
