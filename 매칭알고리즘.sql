select * from tblhire h
    inner join tblcompany c
        on h.cp_seq = c.cp_seq
            where h.hire_avr_year > 0 and cp_name='롯데이노베이트';
            
select months_between(sysdate, founded) as diff, ((hire_avr_year / months_between(sysdate, founded)) *100 ) as score, cp_name from tblhire h
    inner join tblcompany c
        on h.cp_seq = c.cp_seq
            where h.hire_avr_year > 0;
            
SELECT 
    ROUND(months_between(sysdate, founded), 2) as "설립-현재", 
    hire_avr_year as 평균근속,
    ROUND((hire_avr_year / months_between(sysdate, founded)) * 100, 2) as score, 
    cp_name as 기업명
FROM 
    tblhire h
    INNER JOIN tblcompany c ON h.cp_seq = c.cp_seq
WHERE 
    h.hire_avr_year > 0;            

select * from tblCompany where cp_name='롯데이노베이트';
update tblCompany set founded=to_date('1996-12-28', 'yyyy-mm-dd') where cp_seq = 2969;

-- 근무안정성 점수 뷰 제작해야함-----------------------------------------------------------
SELECT 
    ROUND(months_between(sysdate, founded), 2) as "설립-현재", 
    hire_avr_year as 평균근속,
    ROUND((hire_avr_year / months_between(sysdate, founded)) * 100, 2) as prevscore, 
    ROUND((hire_avr_year / months_between(sysdate, founded)) * 100 
          + CASE 
                WHEN months_between(sysdate, founded) <= 60 THEN 0
                WHEN months_between(sysdate, founded) <= 120 THEN 1
                WHEN months_between(sysdate, founded) <= 180 THEN 5
                WHEN months_between(sysdate, founded) <= 240 THEN 10
                WHEN months_between(sysdate, founded) <= 300 THEN 20
                WHEN months_between(sysdate, founded) <= 360 THEN 30
                ELSE 40
            END, 2) as score, 
            
    cp_name as 기업명
FROM 
    tblhire h
    INNER JOIN tblcompany c ON h.cp_seq = c.cp_seq
WHERE 
    h.hire_avr_year > 0;          
-----------------------------------------------------------------------------
-- 퇴사율까지 완료한 쿼리 > 뷰로 만들어야함 근속률 점수
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
            ELSE 40
          END
        - CASE WHEN hire_retired > hire_new THEN ((hire_retired - hire_new) / hire_member) * 100 ELSE 0 END
    , 2) AS score, 
    cp_name AS 기업명
FROM 
    tblhire h
    INNER JOIN tblcompany c ON h.cp_seq = c.cp_seq
WHERE 
    h.hire_avr_year > 0;
    
-------------------------------------------------------------------------------------------    

select ((hire_retired-hire_new)/hire_member) * 100 from tblHire where hire_retired>hire_new;


select months_between(sysdate, founded) as diff, cp_name from tblcompany
    where founded is not null and cp_name like '%삼성%';
    
    
select months_between(sysdate, founded) as diff, cp_name from tblcompany
    where founded is not null;    

select * from tblhire h
    inner join tblcompany c
        on h.cp_seq = c.cp_seq
            where h.hire_retired > h.hire_new and h.hire_member > 50 ;
            
            
select * from tblFinance;   

-- 영업이익으로만 할거임
CREATE or replace VIEW company_growth AS
SELECT
    cp_seq,
    ((fnc_income_2023 - fnc_income_2022) / fnc_income_2022) AS net_income_growth_rate,
    ((fnc_sales_2023 - fnc_sales_2022) / fnc_sales_2022) AS sales_growth_rate,
    ((fnc_ebit_2023 - fnc_ebit_2022) / fnc_ebit_2022) AS operating_profit_growth_rate,
    (((fnc_income_2023 - fnc_income_2022) / fnc_income_2022) * 0.3) +
    (((fnc_sales_2023 - fnc_sales_2022) / fnc_sales_2022) * 0.3) +
    (((fnc_ebit_2023 - fnc_ebit_2022) / fnc_ebit_2022) * 0.4) AS weighted_growth_rate
FROM
    (SELECT
        cp_seq,
        MAX(CASE WHEN fnc_period = 2022 THEN fnc_income ELSE 0 END) AS fnc_income_2022,
        MAX(CASE WHEN fnc_period = 2023 THEN fnc_income ELSE 0 END) AS fnc_income_2023,
        MAX(CASE WHEN fnc_period = 2022 THEN fnc_sales ELSE 0 END) AS fnc_sales_2022,
        MAX(CASE WHEN fnc_period = 2023 THEN fnc_sales ELSE 0 END) AS fnc_sales_2023,
        MAX(CASE WHEN fnc_period = 2022 THEN fnc_ebit ELSE 0 END) AS fnc_ebit_2022,
        MAX(CASE WHEN fnc_period = 2023 THEN fnc_ebit ELSE 0 END) AS fnc_ebit_2023
    FROM
        tblFinance
    GROUP BY
        cp_seq) sub;
        
select * from company_growth;       
        