(select cp_name,count(*) as cnt  from tblCompanyReview cr inner join tblCompany c on cr.cp_seq = c.cp_seq group by c.cp_seq, c.cp_name order by cnt desc);

SELECT cp_name, cnt
FROM (
    SELECT cp_name, cnt, ROWNUM AS rnum
    FROM (
        SELECT cp_name, COUNT(*) AS cnt
        FROM tblCompanyReview cr
        INNER JOIN tblCompany c ON cr.cp_seq = c.cp_seq
        GROUP BY c.cp_seq, c.cp_name
        ORDER BY cnt DESC
    )
)
WHERE rnum <= 5;

create or replace view vwCompanyReviewAvg as
select c.cp_seq, c.cp_name, ROUND((AVG(SALARY_SCORE) + AVG(WELFARE_SCORE) + AVG(STABILITY_SCORE) + AVG(CULTURE_SCORE) + AVG(GROWTH_SCORE)) / 5, 2) as avg  from tblCompanyReview cr inner join tblCompany c on cr.cp_seq = c.cp_seq group by c.cp_seq, c.cp_name;

select * from tblCompanyReview;

select * from vwCompanyReviewAvg;

SELECT rnum, cp_name, avg
FROM (
    SELECT ROWNUM AS rnum, cp_name, avg
    FROM (
        SELECT cp_name, avg
        FROM vwCompanyReviewAvg
        ORDER BY avg DESC
    )
)
WHERE rnum <= 5;

select to_char(CP_RV_REGDATE,'yyyy-mm') as "date", count(*) as cnt  from tblCompanyReview group by to_char(CP_RV_REGDATE,'yyyy-mm');
select * from tblCompanyReview;

SELECT * FROM (SELECT ROWNUM AS rnum, t.* FROM (SELECT * FROM vwAllReview where CP_RV_CONFIRM = 0 ORDER BY CP_RV_REGDATE) t) WHERE rnum BETWEEN ? AND ?;


create or replace view vwYearlyReviewStats as
SELECT 
    TO_CHAR(CP_RV_REGDATE, 'YYYY-MM') AS "date",
    COUNT(*) AS cnt
FROM
    tblCompanyReview
WHERE
    CP_RV_REGDATE >= ADD_MONTHS(TRUNC(SYSDATE, 'MM'), -12) AND
    CP_RV_REGDATE <= TRUNC(SYSDATE, 'MM')
GROUP BY
    TO_CHAR(CP_RV_REGDATE, 'YYYY-MM')
ORDER BY
    "date" asc;

select * from vwYearlyReviewStats;
select * from vwAllReview;

select nvl(sum(report_count),0) as cnt from vwallreportlist WHERE TRUNC(REPORT_REGDATE) = TRUNC(SYSDATE);

SELECT cp_name, cnt
					FROM (SELECT cp_name, COUNT(*) AS cnt, ROWNUM AS rnum
					     FROM tblCompanyReview cr
					              INNER JOIN tblCompany c ON cr.cp_seq = c.cp_seq
				     GROUP BY c.cp_seq, c.cp_name
				     ORDER BY cnt DESC)
					WHERE ROWNUM <= 5;

SELECT cp_name, cnt
FROM (
    SELECT cp_name, cnt, ROWNUM AS rnum
    FROM (
        SELECT c.cp_name, COUNT(*) AS cnt
        FROM tblCompanyReview cr
        INNER JOIN tblCompany c ON cr.cp_seq = c.cp_seq
        GROUP BY c.cp_seq, c.cp_name
        ORDER BY cnt DESC
    )
)
WHERE rnum <= 5;
            
            
            select * from vwYearlyReviewStats;   
            
            
 CREATE OR REPLACE FORCE VIEW VWALLREVIEW AS 
  select r."CP_RV_SEQ",r."SALARY_SCORE",r."WELFARE_SCORE",r."STABILITY_SCORE",r."CULTURE_SCORE",r."GROWTH_SCORE",r."LINEREVIEW",r."GOOD",r."BAD",r."CP_RV_REGDATE",r."CP_SEQ",r."ID",r."CP_RV_CONFIRM", c.cp_name from tblCompanyReview r left join tblCompany c on r.cp_seq = c.cp_seq
    left outer join tblUser u on r.id = u.id order by r.cp_rv_regdate desc;  
    
    
CREATE OR REPLACE FORCE VIEW VWALLREVIEW AS
SELECT 
    r.CP_RV_SEQ,
    r.SALARY_SCORE,
    r.WELFARE_SCORE,
    r.STABILITY_SCORE,
    r.CULTURE_SCORE,
    r.GROWTH_SCORE,
    r.LINEREVIEW,
    r.GOOD,
    r.BAD,
    to_char(r.CP_RV_REGDATE,'yyyy-mm-dd') as cp_rv_regdate,
    r.CP_SEQ,
    r.ID,
    r.CP_RV_CONFIRM,
    c.CP_NAME
FROM 
    tblCompanyReview r
    LEFT JOIN tblCompany c ON r.CP_SEQ = c.CP_SEQ
    LEFT OUTER JOIN tblUser u ON r.ID = u.ID
ORDER BY 
    r.CP_RV_REGDATE asc;    

select * from VWALLREVIEW;