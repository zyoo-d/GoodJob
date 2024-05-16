select count(*) from vwPOT;
select count(*) from vwStab;
select count(*) from vwSal;
select count(*) from vwWel;
select count(*) from vwCul;

select p.cp_seq, p.pot_score, st.stab_score, sa.sal_score, w.wel_score, c.cul_score, sum(p.pot_score,st.stab_score,sa.sal_score,w.wel_score,c.cul_score) from vwPOT p
inner join vwStab st
    on p.cp_seq =  st.cp_seq
inner join vwSal sa
    on p.cp_seq = sa.cp_seq
inner join vwWel w
    on p.cp_seq = w.cp_seq
inner join vwCul c
    on p.cp_seq = c.cp_seq;
    
    
create or replace view vwCompanyScore
as
SELECT 
    p.cp_seq,
    h.hire_avr_salary,
    p.pot_score as potential,
    st.stab_score as stability,
    sa.sal_score as salary,
    w.wel_score as welfare,
    c.cul_score as culture,
    p.pot_score + st.stab_score + sa.sal_score + w.wel_score + c.cul_score AS total_score
FROM 
    vwPOT p
INNER JOIN 
    vwStab st ON p.cp_seq = st.cp_seq
INNER JOIN 
    vwSal sa ON p.cp_seq = sa.cp_seq
INNER JOIN 
    vwWel w ON p.cp_seq = w.cp_seq
INNER JOIN 
    vwCul c ON p.cp_seq = c.cp_seq
INNER JOIN
    tblHire h on p.cp_seq = h.cp_seq;    
 
    
select (POT_SCORE * -3),(STAB_SCORE * 4),(SAL_SCORE * -7), (WEL_SCORE * -11), (CUL_SCORE * 11)  from  vwCompanyScore;   

select * from  vwCompanyScore; 



create or replace view vwUserMatching as
select up.id,up.WELFARE, up.SALARY,up.STABILITY,up.CULTURE,up.POTENTIAL, ud.salary_seq, s.salary_type from tblUserPrefer up
    inner join tblUserDetail ud
    on up.id=ud.id
    inner join tblSalary s
    on ud.salary_seq = s.salary_seq;

select * from vwUserMatching;


select * from tblSalary;

select min(hire_avr_salary) from tblHire;



CREATE OR REPLACE VIEW vwUserSurvey AS
SELECT
id,
(welfare+20)*2.5 as welfare,
(salary+20)*2.5 as salary,
(stability+20) *2.5 as stability,
(culture+20) * 2.5 as culture,
(potential+20) * 2.5 as potential,
SALARY_SEQ,
SALARY_TYPE,
CASE
WHEN SALARY_SEQ != 0 AND salary < 0 THEN
CAST(REPLACE(REPLACE(SALARY_TYPE, ',', ''), '만원 이상', '') AS INT) * 10000 - 3000000
WHEN SALARY_SEQ != 0 AND salary >= 0 THEN
CAST(REPLACE(REPLACE(SALARY_TYPE, ',', ''), '만원 이상', '') AS INT) * 10000
ELSE (SELECT min(hire_avr_salary) FROM tblHire)
END AS min_sal,
CASE
WHEN SALARY_SEQ != 0 AND salary < 0 THEN
CAST(REPLACE(REPLACE(SALARY_TYPE, ',', ''), '만원 이상', '') AS INT) * 10000 + 10000000
WHEN SALARY_SEQ != 0 AND salary >= 0 THEN
CAST(REPLACE(REPLACE(SALARY_TYPE, ',', ''), '만원 이상', '') AS INT) * 10000 + 20000000
WHEN SALARY_SEQ = 0 THEN
CASE
WHEN salary BETWEEN 14 AND 20 THEN 70800000
WHEN salary BETWEEN 10 AND 13 THEN 65000000
WHEN salary BETWEEN 6 AND 8 THEN 60000000
WHEN salary BETWEEN 1 AND 4 THEN 55000000
WHEN salary = 0 THEN 50000000
WHEN salary BETWEEN -4 AND -1 THEN 47000000
WHEN salary BETWEEN -8 AND -6 THEN 45000000
WHEN salary BETWEEN -13 AND -10 THEN 42000000
WHEN salary BETWEEN -20 AND -14 THEN 40000000
END
END AS max_sal
FROM (
SELECT
id,
welfare,
salary,
stability,
culture,
potential,
SALARY_SEQ,
CASE SALARY_SEQ
WHEN 0 THEN '상관없음'
WHEN 11 THEN '3,000만원 이상'
WHEN 12 THEN '3,200만원 이상'
WHEN 13 THEN '3,400만원 이상'
WHEN 14 THEN '3,600만원 이상'
WHEN 15 THEN '3,800만원 이상'
WHEN 16 THEN '4,000만원 이상'
WHEN 17 THEN '5,000만원 이상'
WHEN 18 THEN '6,000만원 이상'
END AS SALARY_TYPE
FROM 
vwUserMatching
) ;

select * from tblIndustry;

select * from tblHire;

select * from tblInterview;

select * from tblCompany where cp_seq = 11852;

select * from vwUserSurvey;


select * from vwUserSurvey where id = 'hong123';


select * from tblIndustry;

update tblIndustry set idst_name = '기타 정보 서비스' where idst_code = 722004;
