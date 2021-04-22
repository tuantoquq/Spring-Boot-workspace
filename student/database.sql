use db_student;

create table student (
    id int not null auto_increment,
    name varchar(255) default '',
    address varchar(255) default '',
    score double not null ,
    primary key (id)
)