insert into account (name,startdate) values('CURRENT ACCOUNT',sysdate());
insert into account (name,startdate) values('SAVING ACCOUNT',sysdate());

insert into currency (name,account_id) values('SOLES',1);
insert into currency (name,account_id) values('DOLARES',1);
insert into currency (name,account_id) values('DOLARES',2);
insert into currency (name,account_id) values('SOLES',2);
insert into currency (name,account_id) values('LIBRAS',2);