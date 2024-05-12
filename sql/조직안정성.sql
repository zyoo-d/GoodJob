create or replace view vwStability
as
SELECT 
    ROUND(months_between(sysdate, founded), 2) AS "설립-현재", 
    hire_avr_year AS 평균근속,
    ROUND((hire_avr_year / months_between(sysdate, founded)) * 100, 2) AS prevscore, 
    ROUND(
        (hire_avr_year / months_between(sysdate, founded)) * 100 
        + CASE 
            WHEN months_between(sysdate, founded) <= 60 THEN 0
            WHEN months_between(sysdate, founded) <= 120 THEN 1
            WHEN months_between(sysdate, founded) <= 180 THEN 5
            WHEN months_between(sysdate, founded) <= 240 THEN 10
            WHEN months_between(sysdate, founded) <= 300 THEN 20
            WHEN months_between(sysdate, founded) <= 360 THEN 30
            ELSE 35
          END
        - CASE WHEN hire_retired > hire_new THEN ((hire_retired - hire_new) / hire_member) * 100 ELSE 0 END
    , 2) AS score, 
    c.cp_seq
FROM 
    tblhire h
    INNER JOIN tblcompany c ON h.cp_seq = c.cp_seq
WHERE 
    h.hire_avr_year > 0;

select * from vwStability;


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
    
select * from vwStab;    