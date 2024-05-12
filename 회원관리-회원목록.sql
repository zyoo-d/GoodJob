
create or replace view vwListUser as
select u.id, u.name, nvl(v.cnt,0) "board", nvl(vcc.cnt,0) "comment", nvl(vr.cnt,0) as "review", to_char(u.regdate,'yyyy-mm-dd') regdate, nvl(vur.cnt,0) as "report", CASE 
        WHEN u.lv = 1 THEN 'Á¤»ó'
        WHEN u.lv = 3 THEN 'Á¤Áö'
        WHEN u.lv = 4 THEN 'Å»Åð'
        ELSE '¿À·ù' 
    END AS status from tblUser u 
    left join vwWriterBoardCount v on u.id = v.id
    left join vwWriterReview vr on vr.id = u.id
    left join vwWriterCommentCount vcc on vcc.id = u.id
    left join vwUserReportCount vur on vur.id = u.id
    where u.lv in(1,3,4);
 
select * from vwListUser; 

select * from tblLiveCmReport;



select * from tblQnAReport;
    
select * from tblUser;
select * from vwWriterBoardCount;
select * from vwWriterReview;
select * from vwWriterCommentCount;
select * from vwUserReportCount;

select * from tblUser u;

SELECT 
    u.id id,
    u.name name,
    NVL(v.cnt, 0) board,
    NVL(vcc.cnt, 0) comment,
    NVL(vr.cnt, 0) review,
    TO_CHAR(u.regdate, 'yyyy-mm-dd') regdate
FROM 
    tblUser u
    LEFT JOIN vwWriterBoardCount v ON u.id = v.id
    LEFT JOIN vwWriterReview vr ON vr.id = u.id
    LEFT JOIN vwWriterCommentCount vcc ON vcc.id = u.id;


SELECT 
    u.id AS id,
    u.name AS name,
    NVL(v.cnt, 0) AS board,
    NVL(vcc.cnt, 0) AS comment,
    NVL(vr.cnt, 0) AS review,
    TO_CHAR(u.regdate, 'yyyy-mm-dd') AS regdate
FROM 
    tblUser u
    LEFT JOIN vwWriterBoardCount v ON u.id = v.id
    LEFT JOIN vwWriterReview vr ON vr.id = u.id
    LEFT JOIN vwWriterCommentCount vcc ON vcc.id = u.id;

CREATE OR REPLACE VIEW vwWriterBoardCount AS
SELECT id, SUM(cnt) AS cnt
FROM (
    SELECT id, COUNT(*) AS cnt FROM tblStudy
    GROUP BY id
    UNION ALL
    SELECT id, COUNT(*) AS cnt FROM tblQnA
    GROUP BY id
    UNION ALL
    SELECT id, COUNT(*) AS cnt FROM tblInterview
    GROUP BY id
) t
GROUP BY id;

select * from vwWriterBoardCount; 

create or replace view vwWriterReview as
select id,count(*) as cnt from tblCompanyReview
group by id;



CREATE OR REPLACE VIEW vwWriterCommentCount AS
SELECT id, SUM(cnt) AS cnt
FROM (
    SELECT id, COUNT(*) AS cnt FROM tblStdComment
    GROUP BY id
    UNION ALL
    SELECT id, COUNT(*) AS cnt FROM tblQnAComment 
    GROUP BY id
    UNION ALL
    SELECT id, COUNT(*) AS cnt FROM tblLiveComment
    GROUP BY id
) t
GROUP BY id;


select * from vwWriterCommentCount;


create or replace view vwUserReportCount as
select REPORTER_ID as id, count(*) as cnt from vwallreportlist
group by REPORTER_ID;

select * from vwUserReportCount;