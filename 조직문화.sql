select * from tblCompanyReview;

select cp_seq, round(avg(CULTURE_SCORE)) * 20 from tblCompanyReview group by cp_seq;

SELECT count(p.cp_seq)
FROM (
  SELECT p.cp_seq
  FROM vwPOT p
  INNER JOIN vwStab s ON p.cp_seq = s.cp_seq
) p
LEFT JOIN tblCompanyReview cr ON p.cp_seq = cr.cp_seq
WHERE cr.cp_seq IS NULL;

CREATE OR REPLACE VIEW vwCul AS
SELECT
    cp_seq,
    ROUND(AVG(CULTURE_SCORE)) * 20 AS cul_score
FROM
    tblCompanyReview
GROUP BY
    cp_seq;

select * from vwCul;