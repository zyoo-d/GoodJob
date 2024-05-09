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


CREATE OR REPLACE VIEW vwStab AS
SELECT
    s.cp_seq,
    Round(CASE
        WHEN s.score > 70 THEN 70
        ELSE s.score
    END +
    COALESCE(AVG(cr.STABILITY_SCORE), 0) * 6) AS stab_score
FROM
    vwStability s
LEFT JOIN
    tblCompanyReview cr ON s.cp_seq = cr.cp_seq
GROUP BY
    s.cp_seq, s.score;
    
select * from vwWel;    
    
    
    
    
    
SELECT p.cp_seq
FROM (
  SELECT p.cp_seq
  FROM vwPOT p
  INNER JOIN vwStab s ON p.cp_seq = s.cp_seq
) p
WHERE p.cp_seq NOT IN (
  SELECT wf.cp_seq
  FROM tblwelfarelist wf
  INNER JOIN tblCompanyReview cr ON wf.cp_seq = cr.cp_seq
  GROUP BY wf.cp_seq
);    
    
    