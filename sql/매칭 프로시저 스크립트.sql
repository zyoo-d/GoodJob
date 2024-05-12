set serveroutput on;

CREATE OR REPLACE PACKAGE pkg_rc AS
    TYPE mat_cursor IS REF CURSOR;
END pkg_rc;
/
select * from tempResults;
/
CREATE TABLE tempResults (
    cp_seq NUMBER,
    hire_avr_salary NUMBER,
    potential NUMBER,
    stability NUMBER,
    salary NUMBER,
    welfare NUMBER,
    culture NUMBER,
    total_score NUMBER
);
drop table tempresults;
/

CREATE OR REPLACE PROCEDURE procMatching
(
    pid IN VARCHAR2,    -- 첫 번째 매개변수
    pfirst in varchar2,
    psecond in varchar2,
    pthird in varchar2,
    pfourth in varchar2
    --presult OUT pkg_rc.mat_cursor  -- 출력 매개변수
    --pnum out number
)
IS
    vrowuser vwUserSurvey%ROWTYPE; 
BEGIN
    SELECT * INTO vrowuser FROM vwUserSurvey WHERE id = pid;

    delete from tempresults;

    FOR vrow IN (SELECT * FROM vwCompanyScore) LOOP
        -- 조건 확인 후 실행
        IF vrow.hire_avr_salary BETWEEN vrowuser.min_sal AND vrowuser.max_sal THEN
            INSERT INTO tempResults VALUES (
                vrow.cp_seq,
                vrow.hire_avr_salary,
                vrow.potential * vrowuser.potential,
                vrow.stability * vrowuser.stability,
                vrow.salary * vrowuser.salary,
                vrow.welfare * vrowuser.welfare,
                vrow.culture * vrowuser.culture,
                vrow.total_score -- 이 필드는 계산된 점수가 필요할 경우 추가 계산이 필요합니다.
            );
        END IF;
    END LOOP;
    
    DELETE FROM tempResults WHERE ROWID IN (SELECT rid FROM (
        SELECT ROWID as rid, ROWNUM as rnum
        FROM tempResults
        ORDER BY pfirst
    ) WHERE rnum > 30);
    
    DELETE FROM tempResults WHERE ROWID IN (SELECT rid FROM (
        SELECT ROWID as rid, ROWNUM as rnum
        FROM tempResults
        ORDER BY psecond
    ) WHERE rnum > 20);
    
    DELETE FROM tempResults WHERE ROWID IN (SELECT rid FROM (
        SELECT ROWID as rid, ROWNUM as rnum
        FROM tempResults
        ORDER BY pthird
    ) WHERE rnum > 15);
    
    DELETE FROM tempResults WHERE ROWID IN (SELECT rid FROM (
        SELECT ROWID as rid, ROWNUM as rnum
        FROM tempResults
        ORDER BY pfourth
    ) WHERE rnum > 9);
    
    update tempresults
        set potential = potential / vrowuser.potential, stability = stability / vrowuser.stability,
        salary = salary / vrowuser.salary,welfare = welfare / vrowuser.welfare, culture = culture / vrowuser.culture;
    
    commit;
    --select count(*) into pnum from tempresults;
    --DBMS_OUTPUT.PUT_LINE('Number of records: ' || TO_CHAR(pnum));
    -- 결과를 REF CURSOR에 옮겨 담기
--    OPEN presult FOR
--        SELECT 
--            cp_seq,
--            hire_avr_salary,
--            potential / vrowuser.potential,
--            stability / vrowuser.stability,
--            salary / vrowuser.salary,
--            welfare / vrowuser.welfare,
--            culture / vrowuser.culture,
--            total_score       
--        FROM tempResults
--            ORDER BY pfirst DESC, psecond desc, pthird desc, pfourth desc;  -- 정렬을 total_score 기준으로 수행
--            
END procMatching;
/

DECLARE
    v_presult pkg_rc.mat_cursor; -- REF CURSOR 타입의 변수 선언
    v_row tempResults%ROWTYPE; -- tempResults 테이블의 ROWTYPE 구조를 갖는 변수 선언
BEGIN
    -- procMatching 프로시저 호출
    procMatching(
        pid => 'hong123',  -- 유저 ID 입력
        pfirst => 'culture',  -- 첫 번째 정렬 기준
        psecond => 'stability',  -- 두 번째 정렬 기준
        pthird => 'potential',  -- 세 번째 정렬 기준
        pfourth => 'welfare',  -- 네 번째 정렬 기준
        presult => v_presult  -- 결과를 받을 REF CURSOR
    );

    -- 결과를 출력
    LOOP
        FETCH v_presult INTO v_row;
        EXIT WHEN v_presult%NOTFOUND;  -- 더 이상 데이터가 없으면 루프 종료
        DBMS_OUTPUT.PUT_LINE('Seq: ' || v_row.cp_seq || 
                             ', Salary: ' || v_row.hire_avr_salary ||
                             ', Potential: ' || v_row.potential ||
                             ', Stability: ' || v_row.stability ||
                             ', Salary Score: ' || v_row.salary ||
                             ', Welfare: ' || v_row.welfare ||
                             ', Culture: ' || v_row.culture ||
                             ', Total Score: ' || v_row.total_score);
    END LOOP;

    -- 커서 닫기
    CLOSE v_presult;
EXCEPTION
    WHEN OTHERS THEN
        -- 커서가 열려 있는지 확인할 수 없으므로, 안전하게 처리하기
        IF v_presult%ISOPEN THEN
            CLOSE v_presult;
        END IF;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/


declare
    
    
begin
    procMatching('hong123','culture', 'stability', 'potential', 'welfare');
end;
/
commit;

select (POT_SCORE * vrowuser.potential),(STAB_SCORE * vrowuser.stability),(SAL_SCORE * vrowuser.salary), 
(WEL_SCORE * vrowuser.welfare), (CUL_SCORE * vrowuser.culture) from  vwCompanyScore;
select * from tblcompany where cp_seq = 10046;
select * from vwCompanyScore where hire_avr_salary between 34000000 and 44000000;
select * from vwCompanyScore;
select * from vwUserSurvey where id = 'hong123';
