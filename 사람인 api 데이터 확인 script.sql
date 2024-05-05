SELECT COUNT(*) FROM v$session;
SELECT * FROM v$session;
-- 현재 값 확인
SHOW PARAMETER PROCESSES;
SHOW PARAMETER SESSIONS;

select distinct seq, LPAD(code, 8, '0') as code, name from tblcode where seq between 6000 and 6500;
select r.rcrt_name, r.cp_seq, c.cp_name, r.rcrt_link, c.ceo from tblRecruit r
    inner join tblcompany c
        on r.cp_seq = c.cp_seq
            order by r.cp_seq desc;
select * from tblJobRcrt;
delete from tblrecruit where cp_seq = 5970;
commit;
select * from tbluser;
delete from tblstudy where std_seq = 6;
select * from tblstudy;
INSERT INTO tblstudy (STD_SEQ, STD_TITLE, STD_CONTENT, STD_ING, STD_REGDATE, CP_SEQ, ID, STD_DUEDATE)
VALUES (seqStudy.NEXTVAL, 'Effective Java Study', 'Deep dive into Java practices', 'N', SYSDATE, 
2001, 'Ft6Hb3K', TO_DATE('2024-05-05', 'YYYY-MM-DD'));