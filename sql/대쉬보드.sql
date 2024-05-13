select count(*) as cnt from tblCompanyReview where cp_rv_confirm = 0 ;

select count(*) as cnt from tblInterview where itv_confirm = 0;


 CREATE OR REPLACE FORCE VIEW VWWEEKLYPOPULARPOSTS ("POST_ID", "TITLE", "CONTENT", "REGDATE", "ID", "VIEWS", "BOARD_TYPE") AS
SELECT
    qna_seq AS post_id,
    qna_title AS title,
    qna_content AS content,
    qna_regdate AS regdate,
    id,
    qna_views AS views,
    'qna' AS board_type
FROM
    tblQnA
WHERE
    qna_regdate >= TRUNC(SYSDATE) - 7
UNION ALL
SELECT
    std_seq AS post_id,
    std_title AS title,
    std_content AS content,
    TO_DATE(std_regdate, 'YYYY-MM-DD') AS regdate,
    id,
    CAST(std_views AS NUMBER) AS views,
    'study' AS board_type
FROM
    tblStudy
WHERE
    TO_DATE(std_regdate, 'YYYY-MM-DD') >= TRUNC(SYSDATE) - 7
ORDER BY
    views DESC;
    
    select * from VWWEEKLYPOPULARPOSTS;
    select * from vwDailyPopularPosts;
    
    select post_id,title,content,to_char(regdate,'yyyy-mm-dd') as regdate ,id ,views,board_type from VWWEEKLYPOPULARPOSTS;
    
    SELECT board_type, COUNT(*) AS cnt FROM viewAllBoards GROUP BY board_type;
    
    select * from tblQna;
    