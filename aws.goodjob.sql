select * from vwMainComList;

select * from tblVisitor;
delete from tblVisitor;

insert into tblVisitor values(to_date('2024-05-05','yyyy-mm-dd'), 15);
insert into tblVisitor values(to_date('2024-05-06','yyyy-mm-dd'), 130);
insert into tblVisitor values(to_date('2024-05-07','yyyy-mm-dd'), 142);
insert into tblVisitor values(to_date('2024-05-08','yyyy-mm-dd'), 66);
insert into tblVisitor values(to_date('2024-05-09','yyyy-mm-dd'), 153);


CREATE OR REPLACE VIEW vwDailyPopularPosts AS
SELECT 
    qna_seq AS post_id,
    qna_title AS title,
    qna_content AS content,
    qna_regdate AS regdate,
    id,
    qna_views AS views,
    'QnA' AS board_type
FROM 
    tblQnA
WHERE 
    TRUNC(qna_regdate) = TRUNC(SYSDATE)
    
UNION ALL

SELECT 
    std_seq AS post_id,
    std_title AS title,
    std_content AS content,
    TO_DATE(std_regdate, 'YYYY-MM-DD') AS regdate,
    id,
    CAST(std_views AS NUMBER) AS views,
    'Study' AS board_type
FROM 
    tblStudy
WHERE 
    TO_DATE(std_regdate, 'YYYY-MM-DD') = TRUNC(SYSDATE)
ORDER BY 
    views DESC;

select * from vwDailyPopularPosts;

select * from vwRecruitList;

select * from tblCompanyReview;

CREATE or replace VIEW vwAllReports AS
SELECT 
   'LIVE_CM' AS report_type,
   live_cm_rp_seq AS report_seq,
   id,
   rp_seq AS reason_seq,
   live_seq,
   live_cm_rp_detail AS description,
   live_cm_rp_regdate AS created_at
FROM tblLiveCmReport
UNION ALL
SELECT
   'QNA_CM' AS report_type,
   qna_cm_rp_seq AS report_seq,
   id,
   rp_seq AS reason_seq,
   qna_cm_seq,
   qna_cm_rp_detail AS description,
   qna_cm_rp_regdate AS created_at
FROM tblQnACmReport
UNION ALL
SELECT
   'QNA' AS report_type,
   qna_rp_seq AS report_seq,
   id,
   rp_seq AS reason_seq,
   qna_seq,
   qna_rp_detail AS description,
   qna_rp_regdate AS created_at
FROM tblQnAReport
UNION ALL
SELECT
   'STD_CM' AS report_type,
   std_cm_rp_seq AS report_seq,
   id,
   rp_seq AS reason_seq,
   std_cm_seq,
   std_cm_rp_detail AS description,
   std_cm_rp_regdate AS created_at
FROM tblStdCmReport
UNION ALL
SELECT
   'STD' AS report_type,
   std_rp_seq AS report_seq,
   id,
   rp_seq AS reason_seq,
   std_seq,
   std_rp_detail AS description,
   std_rp_regdate AS created_at
FROM tblStdReport;


select report_type,count(*) from vwAllReports group by report_type;







INSERT INTO tblVisitor VALUES (to_date('2024-01-01','yyyy-mm-dd'), 299);
INSERT INTO tblVisitor VALUES (to_date('2024-01-02','yyyy-mm-dd'), 59);
INSERT INTO tblVisitor VALUES (to_date('2024-01-03','yyyy-mm-dd'), 64);
INSERT INTO tblVisitor VALUES (to_date('2024-01-04','yyyy-mm-dd'), 298);
INSERT INTO tblVisitor VALUES (to_date('2024-01-05','yyyy-mm-dd'), 292);
INSERT INTO tblVisitor VALUES (to_date('2024-01-06','yyyy-mm-dd'), 55);
INSERT INTO tblVisitor VALUES (to_date('2024-01-07','yyyy-mm-dd'), 172);
INSERT INTO tblVisitor VALUES (to_date('2024-01-08','yyyy-mm-dd'), 172);
INSERT INTO tblVisitor VALUES (to_date('2024-01-09','yyyy-mm-dd'), 279);
INSERT INTO tblVisitor VALUES (to_date('2024-01-10','yyyy-mm-dd'), 298);
INSERT INTO tblVisitor VALUES (to_date('2024-01-11','yyyy-mm-dd'), 224);
INSERT INTO tblVisitor VALUES (to_date('2024-01-12','yyyy-mm-dd'), 72);
INSERT INTO tblVisitor VALUES (to_date('2024-01-13','yyyy-mm-dd'), 109);
INSERT INTO tblVisitor VALUES (to_date('2024-01-14','yyyy-mm-dd'), 230);
INSERT INTO tblVisitor VALUES (to_date('2024-01-15','yyyy-mm-dd'), 86);
INSERT INTO tblVisitor VALUES (to_date('2024-01-16','yyyy-mm-dd'), 174);
INSERT INTO tblVisitor VALUES (to_date('2024-01-17','yyyy-mm-dd'), 226);
INSERT INTO tblVisitor VALUES (to_date('2024-01-18','yyyy-mm-dd'), 178);
INSERT INTO tblVisitor VALUES (to_date('2024-01-19','yyyy-mm-dd'), 210);
INSERT INTO tblVisitor VALUES (to_date('2024-01-20','yyyy-mm-dd'), 98);
INSERT INTO tblVisitor VALUES (to_date('2024-01-21','yyyy-mm-dd'), 290);
INSERT INTO tblVisitor VALUES (to_date('2024-01-22','yyyy-mm-dd'), 74);
INSERT INTO tblVisitor VALUES (to_date('2024-01-23','yyyy-mm-dd'), 115);
INSERT INTO tblVisitor VALUES (to_date('2024-01-24','yyyy-mm-dd'), 82);
INSERT INTO tblVisitor VALUES (to_date('2024-01-25','yyyy-mm-dd'), 65);
INSERT INTO tblVisitor VALUES (to_date('2024-01-26','yyyy-mm-dd'), 209);
INSERT INTO tblVisitor VALUES (to_date('2024-01-27','yyyy-mm-dd'), 105);
INSERT INTO tblVisitor VALUES (to_date('2024-01-28','yyyy-mm-dd'), 156);
INSERT INTO tblVisitor VALUES (to_date('2024-01-29','yyyy-mm-dd'), 62);
INSERT INTO tblVisitor VALUES (to_date('2024-01-30','yyyy-mm-dd'), 72);
INSERT INTO tblVisitor VALUES (to_date('2024-01-31','yyyy-mm-dd'), 170);
INSERT INTO tblVisitor VALUES (to_date('2024-02-01','yyyy-mm-dd'), 91);
INSERT INTO tblVisitor VALUES (to_date('2024-02-02','yyyy-mm-dd'), 92);
INSERT INTO tblVisitor VALUES (to_date('2024-02-03','yyyy-mm-dd'), 110);
INSERT INTO tblVisitor VALUES (to_date('2024-02-04','yyyy-mm-dd'), 266);
INSERT INTO tblVisitor VALUES (to_date('2024-02-05','yyyy-mm-dd'), 127);
INSERT INTO tblVisitor VALUES (to_date('2024-02-06','yyyy-mm-dd'), 298);
INSERT INTO tblVisitor VALUES (to_date('2024-02-07','yyyy-mm-dd'), 243);
INSERT INTO tblVisitor VALUES (to_date('2024-02-08','yyyy-mm-dd'), 227);
INSERT INTO tblVisitor VALUES (to_date('2024-02-09','yyyy-mm-dd'), 104);
INSERT INTO tblVisitor VALUES (to_date('2024-02-10','yyyy-mm-dd'), 266);
INSERT INTO tblVisitor VALUES (to_date('2024-02-11','yyyy-mm-dd'), 289);
INSERT INTO tblVisitor VALUES (to_date('2024-02-12','yyyy-mm-dd'), 215);
INSERT INTO tblVisitor VALUES (to_date('2024-02-13','yyyy-mm-dd'), 159);
INSERT INTO tblVisitor VALUES (to_date('2024-02-14','yyyy-mm-dd'), 106);
INSERT INTO tblVisitor VALUES (to_date('2024-02-15','yyyy-mm-dd'), 209);
INSERT INTO tblVisitor VALUES (to_date('2024-02-16','yyyy-mm-dd'), 51);
INSERT INTO tblVisitor VALUES (to_date('2024-02-17','yyyy-mm-dd'), 244);
INSERT INTO tblVisitor VALUES (to_date('2024-02-18','yyyy-mm-dd'), 95);
INSERT INTO tblVisitor VALUES (to_date('2024-02-19','yyyy-mm-dd'), 165);
INSERT INTO tblVisitor VALUES (to_date('2024-02-20','yyyy-mm-dd'), 71);
INSERT INTO tblVisitor VALUES (to_date('2024-02-21','yyyy-mm-dd'), 270);
INSERT INTO tblVisitor VALUES (to_date('2024-02-22','yyyy-mm-dd'), 120);
INSERT INTO tblVisitor VALUES (to_date('2024-02-23','yyyy-mm-dd'), 148);
INSERT INTO tblVisitor VALUES (to_date('2024-02-24','yyyy-mm-dd'), 222);
INSERT INTO tblVisitor VALUES (to_date('2024-02-25','yyyy-mm-dd'), 96);
INSERT INTO tblVisitor VALUES (to_date('2024-02-26','yyyy-mm-dd'), 294);
INSERT INTO tblVisitor VALUES (to_date('2024-02-27','yyyy-mm-dd'), 50);
INSERT INTO tblVisitor VALUES (to_date('2024-02-28','yyyy-mm-dd'), 59);
INSERT INTO tblVisitor VALUES (to_date('2024-02-29','yyyy-mm-dd'), 89);
INSERT INTO tblVisitor VALUES (to_date('2024-03-01','yyyy-mm-dd'), 122);
INSERT INTO tblVisitor VALUES (to_date('2024-03-02','yyyy-mm-dd'), 86);
INSERT INTO tblVisitor VALUES (to_date('2024-03-03','yyyy-mm-dd'), 276);
INSERT INTO tblVisitor VALUES (to_date('2024-03-04','yyyy-mm-dd'), 164);
INSERT INTO tblVisitor VALUES (to_date('2024-03-05','yyyy-mm-dd'), 129);
INSERT INTO tblVisitor VALUES (to_date('2024-03-06','yyyy-mm-dd'), 60);
INSERT INTO tblVisitor VALUES (to_date('2024-03-07','yyyy-mm-dd'), 201);
INSERT INTO tblVisitor VALUES (to_date('2024-03-08','yyyy-mm-dd'), 286);
INSERT INTO tblVisitor VALUES (to_date('2024-03-09','yyyy-mm-dd'), 261);
INSERT INTO tblVisitor VALUES (to_date('2024-03-10','yyyy-mm-dd'), 81);
INSERT INTO tblVisitor VALUES (to_date('2024-03-11','yyyy-mm-dd'), 111);
INSERT INTO tblVisitor VALUES (to_date('2024-03-12','yyyy-mm-dd'), 72);
INSERT INTO tblVisitor VALUES (to_date('2024-03-13','yyyy-mm-dd'), 275);
INSERT INTO tblVisitor VALUES (to_date('2024-03-14','yyyy-mm-dd'), 143);
INSERT INTO tblVisitor VALUES (to_date('2024-03-15','yyyy-mm-dd'), 104);
INSERT INTO tblVisitor VALUES (to_date('2024-03-16','yyyy-mm-dd'), 216);
INSERT INTO tblVisitor VALUES (to_date('2024-03-17','yyyy-mm-dd'), 173);
INSERT INTO tblVisitor VALUES (to_date('2024-03-18','yyyy-mm-dd'), 122);
INSERT INTO tblVisitor VALUES (to_date('2024-03-19','yyyy-mm-dd'), 97);
INSERT INTO tblVisitor VALUES (to_date('2024-03-20','yyyy-mm-dd'), 151);
INSERT INTO tblVisitor VALUES (to_date('2024-03-21','yyyy-mm-dd'), 224);
INSERT INTO tblVisitor VALUES (to_date('2024-03-22','yyyy-mm-dd'), 86);
INSERT INTO tblVisitor VALUES (to_date('2024-03-23','yyyy-mm-dd'), 77);
INSERT INTO tblVisitor VALUES (to_date('2024-03-24','yyyy-mm-dd'), 220);
INSERT INTO tblVisitor VALUES (to_date('2024-03-25','yyyy-mm-dd'), 116);
INSERT INTO tblVisitor VALUES (to_date('2024-03-26','yyyy-mm-dd'), 172);
INSERT INTO tblVisitor VALUES (to_date('2024-03-27','yyyy-mm-dd'), 141);
INSERT INTO tblVisitor VALUES (to_date('2024-03-28','yyyy-mm-dd'), 55);
INSERT INTO tblVisitor VALUES (to_date('2024-03-29','yyyy-mm-dd'), 190);
INSERT INTO tblVisitor VALUES (to_date('2024-03-30','yyyy-mm-dd'), 204);
INSERT INTO tblVisitor VALUES (to_date('2024-03-31','yyyy-mm-dd'), 110);
INSERT INTO tblVisitor VALUES (to_date('2024-04-01','yyyy-mm-dd'), 257);
INSERT INTO tblVisitor VALUES (to_date('2024-04-02','yyyy-mm-dd'), 50);
INSERT INTO tblVisitor VALUES (to_date('2024-04-03','yyyy-mm-dd'), 54);
INSERT INTO tblVisitor VALUES (to_date('2024-04-04','yyyy-mm-dd'), 289);
INSERT INTO tblVisitor VALUES (to_date('2024-04-05','yyyy-mm-dd'), 161);
INSERT INTO tblVisitor VALUES (to_date('2024-04-06','yyyy-mm-dd'), 180);
INSERT INTO tblVisitor VALUES (to_date('2024-04-07','yyyy-mm-dd'), 193);
INSERT INTO tblVisitor VALUES (to_date('2024-04-08','yyyy-mm-dd'), 63);
INSERT INTO tblVisitor VALUES (to_date('2024-04-09','yyyy-mm-dd'), 135);
INSERT INTO tblVisitor VALUES (to_date('2024-04-10','yyyy-mm-dd'), 158);
INSERT INTO tblVisitor VALUES (to_date('2024-04-11','yyyy-mm-dd'), 233);
INSERT INTO tblVisitor VALUES (to_date('2024-04-12','yyyy-mm-dd'), 241);
INSERT INTO tblVisitor VALUES (to_date('2024-04-13','yyyy-mm-dd'), 291);
INSERT INTO tblVisitor VALUES (to_date('2024-04-14','yyyy-mm-dd'), 290);
INSERT INTO tblVisitor VALUES (to_date('2024-04-15','yyyy-mm-dd'), 222);
INSERT INTO tblVisitor VALUES (to_date('2024-04-16','yyyy-mm-dd'), 254);
INSERT INTO tblVisitor VALUES (to_date('2024-04-17','yyyy-mm-dd'), 193);
INSERT INTO tblVisitor VALUES (to_date('2024-04-18','yyyy-mm-dd'), 113);
INSERT INTO tblVisitor VALUES (to_date('2024-04-19','yyyy-mm-dd'), 63);
INSERT INTO tblVisitor VALUES (to_date('2024-04-20','yyyy-mm-dd'), 244);
INSERT INTO tblVisitor VALUES (to_date('2024-04-21','yyyy-mm-dd'), 114);
INSERT INTO tblVisitor VALUES (to_date('2024-04-22','yyyy-mm-dd'), 288);
INSERT INTO tblVisitor VALUES (to_date('2024-04-23','yyyy-mm-dd'), 146);
INSERT INTO tblVisitor VALUES (to_date('2024-04-24','yyyy-mm-dd'), 138);
INSERT INTO tblVisitor VALUES (to_date('2024-04-25','yyyy-mm-dd'), 291);
INSERT INTO tblVisitor VALUES (to_date('2024-04-26','yyyy-mm-dd'), 213);
INSERT INTO tblVisitor VALUES (to_date('2024-04-27','yyyy-mm-dd'), 191);
INSERT INTO tblVisitor VALUES (to_date('2024-04-28','yyyy-mm-dd'), 85);
INSERT INTO tblVisitor VALUES (to_date('2024-04-29','yyyy-mm-dd'), 175);
INSERT INTO tblVisitor VALUES (to_date('2024-04-30','yyyy-mm-dd'), 148);
INSERT INTO tblVisitor VALUES (to_date('2024-05-01','yyyy-mm-dd'), 257);
INSERT INTO tblVisitor VALUES (to_date('2024-05-02','yyyy-mm-dd'), 152);
INSERT INTO tblVisitor VALUES (to_date('2024-05-03','yyyy-mm-dd'), 93);
INSERT INTO tblVisitor VALUES (to_date('2024-05-04','yyyy-mm-dd'), 54);
INSERT INTO tblVisitor VALUES (to_date('2024-05-05','yyyy-mm-dd'), 296);
commit;