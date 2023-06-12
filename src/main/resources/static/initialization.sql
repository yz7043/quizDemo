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
    name varchar(50) unique not null,
    picture varchar(500) default 'https://www.slideteam.net/media/catalog/product/cache/1280x720/q/u/question_ppt_powerpoint_presentation_file_pictures_Slide01.jpg'
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


