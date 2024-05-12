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


select * from tblCompanyReview cr inner join tblCompany c on cr.cp_seq = c.cp_seq group by c.cp_seq, cp_name;