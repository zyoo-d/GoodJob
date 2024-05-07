select c.cp_name, cr.* from tblcompanyreview cr
    inner join tblcompany c 
        on cr.cp_seq = c.cp_seq where c.cp_seq = 5942;
        commit;
update tblcompanyreview set growth_score = 5 where growth_score = 6;
insert into tblcompanyreview values 
(seqcompanyreview.nextval, 5, 4, 3, 4, 4, '전반적으로 괜찮아요.', '연봉이 맛있음', 
'야근이 맛없음', '2024-04-10', 2105, 'wantjob', 1);
select * from tblhire h
    inner join tblcompany c
        on h.cp_seq = c.cp_seq
            where c.cp_name = '에스넷시스템';

select * from tblwelfare;
update tbluser set pw = 123 where id = 'wantjob';
commit;

select months_between(sysdate, founded) as diff from tblcompany
    where founded is not null;

select * from tblhire h
    inner join tblcompany c
        on h.cp_seq = c.cp_seq
            where h.hire_retired > h.hire_new and h.hire_member > 50;