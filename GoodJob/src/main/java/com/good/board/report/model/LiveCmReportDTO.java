package com.good.board.report.model;

import lombok.Getter;

@Getter
public class LiveCmReportDTO {
    private final String live_cm_rp_seq;
    private final String live_cm_rp_regdate;
    private final String id;
    private final String rp_seq;
    private final String live_seq;
    private final String live_cm_rp_detail;

    private LiveCmReportDTO(Builder builder) {
        this.live_cm_rp_seq = builder.live_cm_rp_seq;
        this.live_cm_rp_regdate = builder.live_cm_rp_regdate;
        this.id = builder.id;
        this.rp_seq = builder.rp_seq;
        this.live_seq = builder.live_seq;
        this.live_cm_rp_detail = builder.live_cm_rp_detail;
    }

    public static class Builder {
        private String live_cm_rp_seq;
        private String live_cm_rp_regdate;
        private String id;
        private String rp_seq;
        private String live_seq;
        private String live_cm_rp_detail;

        public Builder withLive_cm_rp_seq(String live_cm_rp_seq) {
            this.live_cm_rp_seq = live_cm_rp_seq;
            return this;
        }

        public Builder withLive_cm_rp_regdate(String live_cm_rp_regdate) {
            this.live_cm_rp_regdate = live_cm_rp_regdate;
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

        public Builder withLive_seq(String live_seq) {
            this.live_seq = live_seq;
            return this;
        }

        public Builder withLive_cm_rp_detail(String live_cm_rp_detail) {
            this.live_cm_rp_detail = live_cm_rp_detail;
            return this;
        }

        public LiveCmReportDTO build() {
            return new LiveCmReportDTO(this);
        }
    }

}
