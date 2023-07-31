create table t_clazz (
    cid int primary key auto_increment,
    cname varchar(255)
)engine=INNODB default charset=utf8

create table t_student (
    sid int primary key auto_increment,
    sname varchar(255),
    cid int,
    foreign key (cid) references t_clazz(cid)
)engine=INNODB default charset=utf8