select * from tblhire h
    inner join tblcompany c
        on h.cp_seq = c.cp_seq
            where h.hire_avr_year > 0 and cp_name='Î°??ç∞?ù¥?Ö∏Î≤†Ïù¥?ä∏';
            
select months_between(sysdate, founded) as diff, ((hire_avr_year / months_between(sysdate, founded)) *100 ) as score, cp_name from tblhire h
    inner join tblcompany c
        on h.cp_seq = c.cp_seq
            where h.hire_avr_year > 0;
            
SELECT 
    ROUND(months_between(sysdate, founded), 2) as "?Ñ§Î¶?-?òÑ?û¨", 
    hire_avr_year as ?èâÍ∑†Í∑º?Üç,
    ROUND((hire_avr_year / months_between(sysdate, founded)) * 100, 2) as score, 
    cp_name as Í∏∞ÏóÖÎ™?
FROM 
    tblhire h
    INNER JOIN tblcompany c ON h.cp_seq = c.cp_seq
WHERE 
    h.hire_avr_year > 0;            

select * from tblCompany where cp_name='Î°??ç∞?ù¥?Ö∏Î≤†Ïù¥?ä∏';
update tblCompany set founded=to_date('1996-12-28', 'yyyy-mm-dd') where cp_seq = 2969;

-- Í∑ºÎ¨¥?ïà?†ï?Ñ± ?†ê?àò Î∑? ?†ú?ûë?ï¥?ïº?ï®-----------------------------------------------------------
SELECT 
    ROUND(months_between(sysdate, founded), 2) as "?Ñ§Î¶?-?òÑ?û¨", 
    hire_avr_year as ?èâÍ∑†Í∑º?Üç,
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
            
    cp_name as Í∏∞ÏóÖÎ™?
FROM 
    tblhire h
    INNER JOIN tblcompany c ON h.cp_seq = c.cp_seq
WHERE 
    h.hire_avr_year > 0;          
-----------------------------------------------------------------------------
-- ?á¥?Ç¨?ú®ÍπåÏ? ?ôÑÎ£åÌïú ÏøºÎ¶¨ > Î∑∞Î°ú ÎßåÎì§?ñ¥?ïº?ï® Í∑ºÏÜçÎ•? ?†ê?àò
SELECT 
    ROUND(months_between(sysdate, founded), 2) AS "?Ñ§Î¶?-?òÑ?û¨", 
    hire_avr_year AS ?èâÍ∑†Í∑º?Üç,
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
    cp_name AS Í∏∞ÏóÖÎ™?
FROM 
    tblhire h
    INNER JOIN tblcompany c ON h.cp_seq = c.cp_seq
WHERE 
    h.hire_avr_year > 0;
    
-------------------------------------------------------------------------------------------    

select ((hire_retired-hire_new)/hire_member) * 100 from tblHire where hire_retired>hire_new;


select months_between(sysdate, founded) as diff, cp_name from tblcompany
    where founded is not null and cp_name like '%?Çº?Ñ±%';
    
    
select months_between(sysdate, founded) as diff, cp_name from tblcompany
    where founded is not null;    

select * from tblhire h
    inner join tblcompany c
        on h.cp_seq = c.cp_seq
            where h.hire_retired > h.hire_new and h.hire_member > 50 ;
            
            
select * from tblFinance;   

-- ?òÅ?óÖ?ù¥?ùµ?úºÎ°úÎßå ?ï†Í±∞ÏûÑ
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



CREATE OR REPLACE VIEW vwPotential AS
SELECT
    cp_seq,
    CASE
        WHEN fnc_ebit_2023 > fnc_ebit_2022 THEN TO_CHAR(ROUND(ABS((fnc_ebit_2023 - fnc_ebit_2022) / fnc_ebit_2022) * 100))
        WHEN fnc_ebit_2023 < fnc_ebit_2022 THEN '-' || TO_CHAR(ROUND(ABS((fnc_ebit_2022 - fnc_ebit_2023) / fnc_ebit_2022) * 100))
        ELSE '0'
    END AS operating_profit_growth
FROM
    (SELECT
        cp_seq,
        MAX(CASE WHEN fnc_period = 2022 THEN fnc_ebit END) AS fnc_ebit_2022,
        MAX(CASE WHEN fnc_period = 2023 THEN fnc_ebit END) AS fnc_ebit_2023
    FROM
        tblFinance
    WHERE
        fnc_period IN (2022, 2023)
    GROUP BY
        cp_seq
    ) sub
WHERE
    fnc_ebit_2022 IS NOT NULL AND fnc_ebit_2023 IS NOT NULL;
        
select * from tblCompany where cp_name ='≥◊¿Ãπˆ';        
select v.*, c.cp_name from vwPotential v inner join tblCompany c on v.cp_seq = c.cp_seq;
select * from tblFinance;

select * from tblWelfareList;

select * from tblFinance;

select * from tblCompany c left join tblHire h on c.cp_seq = h.cp_seq left join tblFinance f on c.cp_seq = f.cp_seq order by hire_member desc;

select * from tblCompany where cp_seq = 3750;
update tblCompany set ceo = '«¸≈¬¡ÿ' , founded = to_date('1997-04-29', 'yyyy-mm-dd') where cp_seq = 2814;
update tblCompany set cp_name= 'SOOP',ceo = '¡§¬˘øÎ' , founded = to_date('1996-04-22', 'yyyy-mm-dd') where cp_seq = 3750;

select * from tblCompany where cp_seq = 5942;

select * from tblCompany where cp_name like '%∞ÌµÓ%';

insert into tblFinance values(seqFinance.nextVal,5018667294374, 2021 ,sysdate, 2231, 1558728780279,1524755009961);
insert into tblFinance values(seqFinance.nextVal,5512586322305, 2022 ,sysdate, 2231, 1553794848099,1092157607856);
insert into tblFinance values(seqFinance.nextVal,5609799005883, 2023 ,sysdate, 2231, 1713205368367,1414312847932);
commit;

insert into tblFinance values(seqFinance.nextVal,526089047774, 2021 ,sysdate, 2814, 35527094479,31911791735);
insert into tblFinance values(seqFinance.nextVal,588156344440, 2022 ,sysdate, 2814, 37071917485,90361191530);
insert into tblFinance values(seqFinance.nextVal,603889516054, 2023 ,sysdate, 2814, 38505526701,29225437894);

insert into tblFinance values(seqFinance.nextVal,262651005409, 2021 ,sysdate, 3750, 94191463377,69907454311);
insert into tblFinance values(seqFinance.nextVal,304340328627, 2022 ,sysdate, 3750, 89475000152,57987228955);
insert into tblFinance values(seqFinance.nextVal,326178821375, 2023 ,sysdate, 3750, 94198864283,78317711997);

insert into tblFinance values(seqFinance.nextVal,0, 2021 ,sysdate, 2232, 3274726697095,2129575996924);
insert into tblFinance values(seqFinance.nextVal,0, 2022 ,sysdate, 2232, 811371378457,270907553506);
insert into tblFinance values(seqFinance.nextVal,0, 2023 ,sysdate, 2232, 677714327692,781191355738);

update tblCompany set image='/good/asset/images/logo/SOOP.jpg' where cp_seq = 3750;



update tblCompany set ceo = '¿Ø»´¡ÿ, ¿Â∫¥∞≠' , founded = to_date('1999-02-28', 'yyyy-mm-dd') where cp_seq = 5942;

insert into tblFinance values(seqFinance.nextVal,181749063262, 2021 ,sysdate, 5942, -1812799267,-8945557289);
insert into tblFinance values(seqFinance.nextVal,253875421899, 2022 ,sysdate, 5942, 5374196124,-1091737869);
insert into tblFinance values(seqFinance.nextVal,307638510989, 2023 ,sysdate, 5942, 9318054761,14701505035);