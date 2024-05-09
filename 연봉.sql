--7000 이상:100
--6000~7000:90
--5000~6000:80
--4000~5000:70
--3000~4000:60
--3000 이하:50

select count(*) from tblHire where HIRE_AVR_SALARY> 70000000;
select count(*) from tblHire where HIRE_AVR_SALARY between 60000000 and 70000000;
select count(*) from tblHire where HIRE_AVR_SALARY between 50000000 and 60000000;
select count(*) from tblHire where HIRE_AVR_SALARY between 40000000 and 50000000;

select cp_seq, () from tblHire;

select v.*, h.HIRE_AVR_SALARY from vwPOT v inner join tblHire h on v.cp_seq = h.cp_seq;

select v.*, h.HIRE_AVR_SALARY from vwPOT v inner join tblHire h on v.cp_seq = h.cp_seq where HIRE_AVR_SALARY > 70000001; --1
select v.*, h.HIRE_AVR_SALARY from vwPOT v inner join tblHire h on v.cp_seq = h.cp_seq where HIRE_AVR_SALARY between 60000001 and 70000000; --24
select v.*, h.HIRE_AVR_SALARY from vwPOT v inner join tblHire h on v.cp_seq = h.cp_seq where HIRE_AVR_SALARY between 50000001 and 60000000; --97
select v.*, h.HIRE_AVR_SALARY from vwPOT v inner join tblHire h on v.cp_seq = h.cp_seq where HIRE_AVR_SALARY between 40000001 and 50000000; --89
select v.*, h.HIRE_AVR_SALARY from vwPOT v inner join tblHire h on v.cp_seq = h.cp_seq where HIRE_AVR_SALARY between 30000001 and 40000000; -- 3
select v.*, h.HIRE_AVR_SALARY from vwPOT v inner join tblHire h on v.cp_seq = h.cp_seq where HIRE_AVR_SALARY between 20000000 and 30000000; -- 0

create or replace view vwSal
as
SELECT
    cp_seq,
    CASE
        WHEN HIRE_AVR_SALARY >= 70000000 THEN 100
        WHEN HIRE_AVR_SALARY >= 60000000 THEN 90
        WHEN HIRE_AVR_SALARY >= 50000000 THEN 80
        WHEN HIRE_AVR_SALARY >= 40000000 THEN 70
        WHEN HIRE_AVR_SALARY >= 30000000 THEN 60
        ELSE 50
    END AS sal_score
FROM
    tblHire;

select * from vwSal;

