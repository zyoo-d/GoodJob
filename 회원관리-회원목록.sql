select u.id, u.name, nvl(v.cnt,0) "board", nvl(vcc.cnt,0) "comment", nvl(vr.cnt,0) as "review", to_char(u.regdate,'yyyy-mm-dd') regdate,  from tblUser u 
    left join vwWriterBoardCount v on u.id = v.id
    left join vwWriterReview vr on vr.id = u.id
    left join vwWriterCommentCount vcc on vcc.id = u.id;
    left join vwallreportlist vrl on vrl.id = u.id

select * from tblUser;
select * from vwWriterBoardCount;
select * from vwWriterReview;
select * from vwWriterCommentCount;


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

create or replace view vwWriterBoardCount as
select id, count(*) as cnt from tblStudy
group by id
UNION ALL

select id, count(*) as cnt from tblQnA
group by id

UNION ALL
select id, count(*) as cnt from tblInterview
group by id;

select * from vwWriterBoardCount; 

create or replace view vwWriterReview as
select id,count(*) as cnt from tblCompanyReview
group by id;



create or replace view vwWriterCommentCount as
select id, count(*) as cnt from tblStdComment
group by id
UNION ALL

select id, count(*) as cnt from tblQnAComment
group by id

UNION ALL
select id, count(*) as cnt from tblLiveComment
group by id;


select * from vwWriterCommentCount;



select REPORTER_ID, count(*) from vwallreportlist
group by REPORTER_ID;

