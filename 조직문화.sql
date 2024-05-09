select * from tblCompanyReview;

select cp_seq, round(avg(CULTURE_SCORE)) * 20 from tblCompanyReview group by cp_seq;



CREATE OR REPLACE VIEW vwCul AS
SELECT
    cp_seq,
    ROUND(AVG(CULTURE_SCORE)) * 20 AS cul_score
FROM
    tblCompanyReview
GROUP BY
    cp_seq;

select * from vwCul;