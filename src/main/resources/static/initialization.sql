DROP DATABASE IF EXISTS QuizProject;
CREATE DATABASE QuizProject;
USE QuizProject;
DROP TABLE if exists Contact;
DROP TABLE if exists  QuizQuestion;
DROP TABLE if exists Quiz;
DROP TABLE if exists Choice;
DROP TABLE if exists Question;
DROP TABLE if exists Category;
DROP TABLE if exists Users;

create table Contact(
    contact_id int primary key auto_increment,
    subject varchar(200),
    message varchar(2000),
    email varchar(50),
    time timestamp
);

create table Users(
    user_id int primary key auto_increment,
    email varchar(50) unique,
    password varchar(50) not null,
    firstname varchar(50),
    lastname varchar(50),
    is_active bit(1) default b'1',
    is_admin bit(1) default b'0'
);

create table Category(
    category_id int primary key auto_increment,
    name varchar(50)
);

create table Quiz(
    quiz_id int primary key auto_increment,
    user_id int,
    category_id int,
    name varchar(100),
    time_start timestamp,
    time_end timestamp,

    foreign key (user_id) references Users(user_id),
    foreign key (category_id) references Category(category_id)
);

create table Question(
    question_id int primary key auto_increment,
    category_id int,
    description varchar(1000),
    is_active bit(1) default b'1',

    foreign key (category_id) references Category(category_id)
);

create table Choice(
    choice_id int primary key auto_increment,
    question_id int,
    description varchar(300),
    is_correct bit(1) not null,

    foreign key (question_id) references Question(question_id)
);

create table QuizQuestion(
    qq_id int primary key auto_increment,
    quiz_id int,
    question_id int,
    user_choice_id int,

    foreign key (quiz_id) references Quiz(quiz_id),
    foreign key (question_id) references Question(question_id),
    foreign key (user_choice_id) references Choice(choice_id)
);


insert into Users (user_id, email, password, firstname, lastname, is_active, is_admin) VALUES
    (1, 'admin@gmail.com', 'admin', 'admin_first', 'admin_last', b'1', b'1');

insert into category (category_id, name) VALUES (1, 'Dota2');

insert into Question (question_id, category_id, description, is_active) VALUES
    (1, 1, 'Can basic dispel dispel stun?', b'1');
insert into Question (question_id, category_id, description, is_active) VALUES
    (2, 1, 'If a skill cannot pierce spell immunity, can it deal pure damage to a hero under spell immunity', b'1');
insert into Question (question_id, category_id, description, is_active) VALUES
    (3, 1, 'If a skill cannot pierce spell immunity, can it deal magic damage to a hero under spell immunity with magic resistence < 100?', b'1');
insert into Question (question_id, category_id, description, is_active) VALUES
    (4, 1, 'What is the primary attribute of Timbersaw?', b'1');
insert into Question (question_id, category_id, description, is_active) VALUES
    (5, 1, 'Can nullifier be used on hero under spell immunity?', b'1');

insert into Choice (question_id, description, is_correct) VALUES (1, 'Yes', b'0');
insert into Choice (question_id, description, is_correct) VALUES (1, 'No', b'1');

insert into Choice (question_id, description, is_correct) VALUES (2, 'Yes', b'0');
insert into Choice (question_id, description, is_correct) VALUES (2, 'No', b'1');

insert into Choice (question_id, description, is_correct) VALUES (3, 'Yes', b'1');
insert into Choice (question_id, description, is_correct) VALUES (3, 'No', b'0');

insert into Choice (question_id, description, is_correct) VALUES (4, 'Agile', b'0');
insert into Choice (question_id, description, is_correct) VALUES (4, 'Strength', b'0');
insert into Choice (question_id, description, is_correct) VALUES (4, 'Intelligence', b'0');
insert into Choice (question_id, description, is_correct) VALUES (4, 'Universal', b'1');

insert into Choice (question_id, description, is_correct) VALUES (5, 'Yes', b'1');
insert into Choice (question_id, description, is_correct) VALUES (5, 'No', b'0');