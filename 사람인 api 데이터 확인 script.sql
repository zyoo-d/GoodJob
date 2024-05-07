SELECT COUNT(*) FROM v$session;
SELECT * FROM v$session;
-- 현재 값 확인
SHOW PARAMETER PROCESSES;
SHOW PARAMETER SESSIONS;

select distinct seq, LPAD(code, 8, '0') as code, name from tblcode where seq > 11852;
select r.rcrt_name, r.cp_seq, c.cp_name, r.rcrt_link, c.ceo 
from tblRecruit r
    inner join tblcompany c
        on r.cp_seq = c.cp_seq
            order by r.cp_seq desc;
select * from tblcompany where cp_seq = 11481;
select * from tblJobRcrt;
delete from tblrecruit where cp_seq = 11481;
commit;



select * from tblhire h
    inner join tblcompany c
        on h.cp_seq = c.cp_seq
            where h.hire_avr_year > 0;

select months_between(sysdate, founded) as diff from tblcompany
    where founded is not null;

select * from tblhire h
    inner join tblcompany c
        on h.cp_seq = c.cp_seq
            where h.hire_retired > h.hire_new and h.hire_member > 50;

select * from tblwelfare;
select * from tblwishjob;
select * from tblUserDetail;


select * from tbluser;
delete from tblstudy where std_seq = 6;
select * from tblstudy;
update tblstudy set std_duedate = std_duedate +20;
UPDATE tblstudy
SET std_duedate = std_duedate + 10;
UPDATE tblstudy
SET std_ing = 'Y'
WHERE std_duedate < TRUNC(SYSDATE);

delete from tblstudy;
commit;
select s.*, c.cp_name, u.nickname from tblstudy s
    inner join tblcompany c
        on s.cp_seq = c.cp_seq
        inner join tblUser u
                on s.id = u.id;
drop view vwStudy;
create view vwStudy
as
select s.*, c.cp_name, u.nickname 
from tblstudy s
    inner join tblcompany c
        on s.cp_seq = c.cp_seq
            inner join tblUser u
                on s.id = u.id;
select * from tblcompany c
    inner join tblfinance f
        on c.cp_seq = f.cp_seq
            where f.fnc_period = 2023;
            select a.*, rownum as rnum from vwstudy a where a.id = 'hong123' order by std_regdate desc;
select *, rownum as rnum from (select * from vwstudy where id = 'hong123' order by std_regdate desc) where rnum between 1 and 5;
SELECT *
FROM (
    SELECT a.*, ROWNUM AS rnum
    FROM (
        SELECT *
        FROM vwstudy
        WHERE id = 'hong123'
        ORDER BY std_regdate DESC
    ) a
)
WHERE rnum BETWEEN 1 AND 5;
SELECT a.*, ROWNUM as rnum
FROM (
    SELECT *
    FROM vwstudy
    WHERE id = 'hong123'
    ORDER BY std_regdate DESC
) a;
select count(*) as cnt from tblstudy where id = 'hong123';
select * from (select a.*, rownum as rnum from vwstudy a) where rnum between 1 and 10;
INSERT INTO tblstudy (STD_SEQ, STD_TITLE, STD_CONTENT, STD_ING, STD_REGDATE, CP_SEQ, ID, STD_DUEDATE)
VALUES (seqStudy.NEXTVAL, 'Effective Java Study', 'Deep dive into Java practices', 'N', SYSDATE, 
2001, 'Ft6Hb3K', TO_DATE('2024-05-05', 'YYYY-MM-DD'));

CREATE TABLE tblwishwelfare (
    id VARCHAR2(50),         -- tbluser의 id, 데이터 타입은 예시이며 실제와 맞춰야 합니다.
    wel_seq NUMBER,          -- tblwelfare의 wel_seq, 데이터 타입은 예시이며 실제와 맞춰야 합니다.
    CONSTRAINT fk_tblwishwelfare_id FOREIGN KEY (id)
        REFERENCES tbluser (id),     -- tbluser의 id를 참조하는 외래키
    CONSTRAINT fk_tblwishwelfare_wel_seq FOREIGN KEY (wel_seq)
        REFERENCES tblwelfare (wel_seq) -- tblwelfare의 wel_seq를 참조하는 외래키
);

select * from tblwishwelfare where id = 'hong123';
select * from tblwishlocation where id = 'hong123';
select count(*) from tbluserdetail where id = 'hong123';
select count(*) as prefer from tbluserprefer where id = 'hong123';
update tbluserdetail set edu_seq = 1, salary_seq = 10, career=3 where id = 'hong123';
select * from tbljobrcrt;

select * from (select count(*) as wish from tbluserdetail d)
    left outer join tbluserprefer p
        on d.id = p.id where d.id = 'hong123';

select * from tblsurvey;
insert into tblsurvey values (seqsurvey.nextval, '기업의 경영상태 및 근속가능성이 부정적이어도 다양한 복지프로그램을 지원하는 회사라면 근무할 의향이 있다.','복지');
update tblsurvey set sv_content = '현재의 급여 수준을 높인다면 조금 더 힘든 팀으로 이직할 수 있다.' where sv_seq = 1;        
SELECT 
    (SELECT COUNT(*) FROM tbluserdetail WHERE id = 'hong123') AS wish,
    (SELECT COUNT(*) FROM tbluserprefer WHERE id = 'hong123') AS prefer
from dual;


commit;