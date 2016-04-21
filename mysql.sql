create table customer2 (
	id bigint(20) not null auto_increment,
    name varchar(255) default null,
    contact varchar(255) default null,
    telephone varchar(255) default null,
    email varchar(255) default null,
    remark text,
    primary key (id)
) engine=InnoDB default charset=utf8;

insert into lin.customer values (1, '客户一', 'Jack', '13012345678', 'it@123.com', null);
insert into lin.customer values (2, '客户二', 'Tom', '13059632487', 'it2@123.com', null);