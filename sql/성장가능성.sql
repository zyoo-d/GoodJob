SELECT
v.cp_seq,
CASE
WHEN v.score < 0 THEN 0

WHEN v.score > 100 THEN 100
ELSE v.score * 0.7
END + COALESCE(AVG(cr.GROWTH_SCORE), 0) * 6
AS total_score
FROM vwPotential v
INNER JOIN tblCompany c ON v.cp_seq = c.cp_seq
LEFT JOIN tblCompanyReview cr ON c.cp_seq = cr.cp_seq
GROUP BY v.cp_seq, v.score;


CREATE OR REPLACE VIEW vwPOT
AS
SELECT
    v.cp_seq,
    ROUND(
        CASE
            WHEN v.score < 0 THEN 0
            WHEN v.score > 100 THEN 70
            ELSE v.score * 0.7
        END + COALESCE(AVG(cr.GROWTH_SCORE), 0) * 6
    ) AS pot_score
FROM
    vwPotential v
INNER JOIN
    tblCompany c ON v.cp_seq = c.cp_seq
LEFT JOIN
    tblCompanyReview cr ON c.cp_seq = cr.cp_seq
GROUP BY
    v.cp_seq, v.score;

select * from vwPOT;


CASE
WHEN v.score < 0 THEN 0

WHEN v.score > 100 THEN 100
END 
FROM vwPotential v
INNER JOIN tblCompany c ON v.cp_seq = c.cp_seq
LEFT JOIN tblCompanyReview cr ON c.cp_seq = cr.cp_seq
GROUP BY v.cp_seq, v.score;


select * from vwPotential where cp_seq = 4887;
select * from tblCompanyReview where cp_seq = 4887;


select * from vwPOT;

select p.cp_seq from vwPOT p inner join vwStab s on p.cp_seq = s.cp_seq left join tblCompanyReview cr on p.cp_seq = cr.cp_seq group by p.cp_seq;
