create or replace view vw_full_employee
as 
select e.empno, e.empname, t.tno as title_no , t.tname as title_name, e.manager as manager_no,
	   m.empname as manager_name, e. salary, d.deptno ,d.deptname,d.floor 
	from employee e
		join title t on e.title = t.tno 
		left join employee m on e.manager = m.empno 
		join department d on e.dept = d.deptno ;

select * from vw_full_employee;

select empno,empname,title_no,title_name,manager_no,manager_name,salary,deptno,deptname,floor
	from vw_full_employee ;

select empno,empname,title as title_no, manager as manager_no,salary,dept as deptNo
	from employee e where empno = 1003;
	

select * from title;
select * from department d ;
select * from employee e ;

-- 해당 직책을 가지고 있는 사원목록을 검색
select empname, empno from employee e join title t on e.title = t.tno where tno  = 5;

-- 해당 부서에 속한 사원목록 검색
select empname, empno from employee e join department d on e.dept = d.deptno where deptno = 2;

select * from employee where empno = 1003;

-- pass 길이 확인
-- hash 단방향 함수(Hash:MDS)
select password('asdfasdfasdfasdfasdfasdf'), length(password('asdfasdfasdfasdfasdfasdf'))from dual;

select password('1234');