/*DROP DATABASE IF EXISTS timeclock;
CREATE DATABASE timeclock;*/
USE timeclock;

DROP TABLE IF EXISTS user;
Create table user (
id int primary key auto_increment,
firstname varchar(50),
lastname varchar(50),
email varchar(50),
role varchar(10),
username varchar(50),
password varchar(100)
);



DROP TABLE IF EXISTS shift;
Create table shift (
id int primary key auto_increment,
user_id int,
shift_date varchar(50),
start_shift varchar(50),
end_shift varchar(50),
start_break varchar(50),
end_break varchar(50),
start_lunch varchar(50),
end_lunch varchar(50),
shift_active boolean,
break_active boolean,
lunch_active boolean,
foreign key (user_id) references user(id)
);