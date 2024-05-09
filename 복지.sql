SELECT
    wf.cp_seq,
    (COUNT(*) + (COALESCE(AVG(cr.WELFARE_SCORE), 0) * 6)) AS stabscore
FROM
    tblwelfarelist wf
INNER JOIN
    tblCompanyReview cr ON wf.cp_seq = cr.cp_seq
GROUP BY
    wf.cp_seq;

select cp_seq, avg(WELFARE_SCORE) from tblCompanyReview  group by cp_seq;

create or replace view vwWelfare
as
SELECT
    wf.cp_seq,
    COUNT(*) AS welscore
FROM
    tblwelfarelist wf
GROUP BY
    wf.cp_seq;

select vw.cp_seq, (welscore + COALESCE(AVG(cr.STABILITY_SCORE), 0) * 6)) from vwWelfare vw
inner join tblCompanyReview cr
on vw.cp_seq = cr.cp_seq 
group by vw.cp_seq;

create or replace view vwWel
as
SELECT
    vw.cp_seq,
    round(vw.welscore + COALESCE(AVG(cr.STABILITY_SCORE), 0) * 6) AS wel_score
FROM
    vwWelfare vw
LEFT JOIN
    tblCompanyReview cr ON vw.cp_seq = cr.cp_seq 
GROUP BY
    vw.cp_seq, vw.welscore;


select * from tblCompanyReview;



select * from vwWel;    
    
    
    
    

    