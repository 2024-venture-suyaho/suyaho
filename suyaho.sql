drop table if exists book;
drop table if exists chat_log;
drop table if exists chat_room;
drop table if exists lost_item;
drop table if exists others;
drop table if exists trade_board;
drop table if exists users;

create table users
(
	user_no BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(255) NOT NULL,
    user_phone VARCHAR(255) NOT NULL,
    user_email VARCHAR(255) NOT NULL,
    user_major VARCHAR(255) NOT NULL,
    user_school_num INT NOT NULL,
    user_pwd VARCHAR(255) NOT NULL,
    user_rights CHAR NOT NULL DEFAULT 'N',
    user_point INT NOT NULL,
    user_made_time DATETIME NOT NULL,
    user_img LONGBLOB
) ENGINE=INNODB;

create table trade_board(
	trade_num BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    trade_category VARCHAR(255) NOT NULL,
    trade_title VARCHAR(255) NOT NULL,
    trade_product VARCHAR(255) NOT NULL,
    trade_quantity INT NOT NULL,
    trade_price INT NOT NULL,
    trade_text VARCHAR(255) NOT NULL,
    trade_condition VARCHAR(255) NOT NULL,
    trade_photo LONGBLOB NOT NULL,
    trade_time DATETIME NOT NULL,
    trade_complete CHAR NOT NULL DEFAULT 'N',
    user_no BIGINT NOT NULL,
    FOREIGN KEY(user_no) REFERENCES users(user_no)
		ON DELETE CASCADE
		ON UPDATE CASCADE
) ENGINE=INNODB;

create table book(
	trade_num BIGINT NOT NULL,
    user_no BIGINT NOT NULL,
    book_writing CHAR,
    book_cover CHAR,
    book_discoloration CHAR,
    book_damage CHAR,
    book_company VARCHAR(255),
    PRIMARY KEY(trade_num, user_no),
    FOREIGN KEY(trade_num) REFERENCES trade_board(trade_num)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	FOREIGN KEY(user_no) REFERENCES users(user_no)
		ON DELETE CASCADE
        ON UPDATE CASCADE
) ENGINE=INNODB;

create table others(
	trade_num BIGINT NOT NULL,
    user_no BIGINT NOT NULL,
    other_damage CHAR,
    other_condition CHAR,
    PRIMARY KEY(trade_num, user_no),
	FOREIGN KEY(trade_num) REFERENCES trade_board(trade_num)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	FOREIGN KEY(user_no) REFERENCES users(user_no)
		ON DELETE CASCADE
        ON UPDATE CASCADE
) ENGINE=INNODB;

create table lost_item(
	lost_num BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    lost_title VARCHAR(255) NOT NULL,
    lost_position VARCHAR(255) NOT NULL,
    lost_text VARCHAR(255) NOT NULL,
    lost_photo LONGBLOB,
    user_no BIGINT,
    FOREIGN KEY(user_no) REFERENCES users(user_no)
		ON DELETE CASCADE
        ON UPDATE CASCADE
) ENGINE=INNODB;

create table chat_room(
	chat_num BIGINT AUTO_INCREMENT NOT NULL,
    user_no BIGINT NOT NULL,
    PRIMARY KEY(chat_num, user_no),
    FOREIGN KEY(user_no) REFERENCES users(user_no)
		ON DELETE CASCADE
        ON UPDATE CASCADE
) ENGINE=INNODB;

create table chat_log(
	mes_num BIGINT AUTO_INCREMENT NOT NULL,
    chat_num BIGINT NOT NULL,
    user_no BIGINT NOT NULL,
    mes_text VARCHAR(255),
    mes_time DATETIME,
    PRIMARY KEY(mes_num, chat_num, user_no),
    FOREIGN KEY(chat_num) REFERENCES chat_room(chat_num)
		ON DELETE CASCADE
        ON UPDATE CASCADE,
	FOREIGN KEY(user_no) REFERENCES users(user_no)
		ON DELETE CASCADE
        ON UPDATE CASCADE
) ENGINE=INNODB;

INSERT INTO users
	(user_no, user_name, user_phone, user_email, user_major,
    user_school_num, user_pwd, user_rights, user_point, user_made_time, user_img
    )
VALUES (null, '이재혁','010-5572-2859','ljh5572@syuin.ac.kr','컴퓨터공학부',2018100984,'1111','Y',0,CURRENT_TIME(),null),
	(null, '오송은','010-7777-7777','ohs1234@syuin.ac.kr','컴퓨터공학부',2020100984,'7777','N',30,CURRENT_TIME(),null),
    (null, '이은서','010-8888-8888','les1234@syuin.ac.kr','인공지융합부',2021100984,'8888','N',40,CURRENT_TIME(),null),
    (null, '심선우','010-8888-8888','ssw@syuin.ac.kr','인공지융합부',202419876,'8888','N',0,CURRENT_TIME(),null),
    (null, '김현수','010-8888-8888','khs@syuin.ac.kr','간호',201711234,'8888','N',10,CURRENT_TIME(),null);
select * from users;


commit;
