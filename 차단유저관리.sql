select * from vwAllReportlist;


select * from tblBanLog;

commit;
delete from tblBanLog where ban_seq = 4;

select b.*, TRUNC(ban_enddate-ban_startdate) as remaining_time from tblBanLog b;

select ban_seq,ban_reason,id,to_char(ban_startdate,'yyyy-mm-dd') as ban_startdate,to_char(ban_enddate,'yyyy-mm-dd') as ban_enddate, TRUNC(ban_enddate-ban_startdate) as remaining_time from tblBanLog b;


create table tblReportCheck(
    rc_seq number primary key,
    type varchar2(30) not null,
    sub_type varchar2(30) not null,
    report_seq number not null,
    id varchar2(30) not null,
    checked_date date default sysdate not null
);

drop table tblReportCheck;

create sequence seqReportCheck;

SELECT type, sub_type FROM vwAllReportlist WHERE report_seq = 1;

select * from tblReportCheck;

select * from vwAllReportList ;
select * from tblReportCheck;

select std_seq as seq from tblStdComment where std_cm_seq = 179;

SELECT *
FROM vwAllReportList
WHERE (TYPE, sub_type, report_seq) NOT IN (
    SELECT TYPE, sub_type, report_seq
    FROM tblReportCheck
);

select * from tblUser;

select * from tblReportCheck;

select * from tblStdComment;

select * from tblReportCheck;


select * from vwAllReportList;

insert into tblReportCheck values(seqReportCheck.nextVal, 'board','qna',61,'admin',sysdate);


create or replace view vwListBanUser as
select ban_seq,ban_reason,id,to_char(ban_startdate,'yyyy-mm-dd') as ban_startdate,to_char(ban_enddate,'yyyy-mm-dd') as ban_enddate, TRUNC(ban_enddate-ban_startdate) as remaining_time from tblBanLog b;


select * from vwListBanUser;

select * from vwDailyPopularPosts;

CREATE OR REPLACE VIEW vwAllReportlist  AS 
  SELECT
    'board' AS type,
    'qna' AS sub_type,
    q.qna_seq AS seq,
    q.qna_title AS title,
    q.qna_regdate AS regdate,
    q.id AS writer_id,
    qr.id AS reporter_id,
    qr.qna_rp_detail AS report_detail,
    qr.rp_seq as rp_seq,
    qr.qna_rp_seq as report_seq,
    MAX(qr.qna_rp_regdate) AS report_regdate,
    COUNT(*) AS report_count
FROM tblQna q
INNER JOIN tblQnAReport qr ON q.qna_seq = qr.qna_seq
GROUP BY q.qna_seq, q.qna_title, q.qna_regdate, q.id, qr.id, qr.qna_rp_detail, qr.rp_seq, qr.qna_rp_seq

UNION ALL

SELECT
    'board' AS type,
    'study' AS sub_type,
    s.std_seq AS seq,
    s.std_title AS title,
    s.std_regdate AS regdate,
    s.id AS writer_id,
    sr.id AS reporter_id,
    sr.std_rp_detail AS report_detail,
    sr.rp_seq as rp_seq,
    sr.std_rp_seq as report_seq,
    MAX(sr.std_rp_regdate) AS report_regdate,
    COUNT(*) AS report_count
FROM tblStudy s
INNER JOIN tblStdReport sr ON s.std_seq = sr.std_seq
GROUP BY s.std_seq, s.std_title, s.std_regdate, s.id, sr.id, sr.std_rp_detail, sr.rp_seq, sr.std_rp_seq

UNION ALL

SELECT
    'comment' AS type,
    'qna' AS sub_type,
    qc.qna_cm_seq AS seq,
    qc.qna_cm_content AS title,
    qc.qna_cm_regdate AS regdate,
    qc.id AS writer_id,
    qcr.id AS reporter_id,
    qcr.qna_cm_rp_detail AS report_detail,
    qcr.rp_seq as rp_seq,
    qcr.qna_cm_rp_seq as report_seq,
    MAX(qcr.qna_cm_rp_regdate) AS report_regdate,
    COUNT(*) AS report_count
FROM tblQnAComment qc
INNER JOIN tblQnACmReport qcr ON qc.qna_cm_seq = qcr.qna_cm_seq
GROUP BY qc.qna_cm_seq, qc.qna_cm_content, qc.qna_cm_regdate, qc.id, qcr.id, qcr.qna_cm_rp_detail, qcr.rp_seq,  qcr.qna_cm_rp_seq

UNION ALL

SELECT
    'comment' AS type,
    'study' AS sub_type,
    sc.std_cm_seq AS seq,
    sc.std_cm_content AS title,
    sc.std_cm_regdate AS regdate,
    sc.id AS writer_id,
    scr.id AS reporter_id,
    scr.std_cm_rp_detail AS report_detail,
    scr.rp_seq as rp_seq,
    scr.std_cm_rp_seq as report_seq,
    MAX(scr.std_cm_rp_regdate) AS report_regdate,
    COUNT(*) AS report_count
FROM tblStdComment sc
INNER JOIN tblStdCmReport scr ON sc.std_cm_seq = scr.std_cm_seq
GROUP BY sc.std_cm_seq, sc.std_cm_content, sc.std_cm_regdate, sc.id, scr.id, scr.std_cm_rp_detail, scr.rp_seq, scr.std_cm_rp_seq

UNION ALL

SELECT
    'comment' AS type,
    'company' AS sub_type,
    lc.live_seq AS seq,
    lc.live_content AS title,
    lc.live_cm_regdate AS regdate,
    lc.id AS writer_id,
    lcr.id AS reporter_id,
    lcr.live_cm_rp_detail AS report_detail,
    lcr.rp_seq as rp_seq,
    lcr.live_cm_rp_seq as report_seq,
    MAX(lcr.live_cm_rp_regdate) AS report_regdate,
    COUNT(*) AS report_count
FROM tblLiveComment lc
INNER JOIN tblLiveCmReport lcr ON lc.live_seq = lcr.live_seq
GROUP BY lc.live_seq, lc.live_content, lc.live_cm_regdate, lc.id, lcr.id, lcr.live_cm_rp_detail, lcr.rp_seq, lcr.live_cm_rp_seq;

