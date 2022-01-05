use photo_app;

select * from users;

update users set email_verification_status = 'true';
select count(*) from Users where email_verification_status is true;
select * from Users u where u.EMAIL_VERIFICATION_STATUS is true;
commit;
select * from password_reset_tokens;
select * from addresses;

drop table users;

drop table password_reset_tokens;

drop table addresses;

rollback;
commit;







delete from users where id = 95;
delete from addresses where user_id = 95;
