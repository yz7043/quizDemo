insert into Users (user_id, email, password, firstname, lastname, is_active, is_admin) VALUES
    (1, 'admin@gmail.com', 'admin', 'admin_first', 'admin_last', b'1', b'1');
insert into Users (user_id, email, password, firstname, lastname, is_active, is_admin) VALUES
    (2, 'zyx', 'zyx', 'yx', 'z', b'1', b'0');
insert into Users (user_id, email, password, firstname, lastname, is_active, is_admin) VALUES
    (3, 'zyx1', 'zyx1', 'yx1', 'z1', b'1', b'1');

insert into Users (user_id, email, password, firstname, lastname, is_active, is_admin) VALUES
    (4, 'zyx2', 'zyx2', 'yx2', 'z2', b'0', b'0');

insert into category (category_id, name) VALUES (1, 'Dota2');
insert into category (category_id, name) VALUES (2, 'Java');
insert into category (category_id, name) VALUES (3, 'C++');
# Dota question
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
# JAVA question
insert into Question (question_id, category_id, description, is_active) VALUES
    (6, 2, 'Is overriding dynamic binding?', b'1');
insert into Question (question_id, category_id, description, is_active) VALUES
    (7, 2, 'Is Integer a primary type?', b'1');
insert into Question (question_id, category_id, description, is_active) VALUES
    (8, 2, 'Can I cast Double to Integer without losing precision?', b'1');
insert into Question (question_id, category_id, description, is_active) VALUES
    (9, 2, 'Which one is not a primitive type?', b'1');
insert into Question (question_id, category_id, description, is_active) VALUES
    (10, 2, 'Can I extend a class marked as final?', b'1');
# C++ question
insert into Question (question_id, category_id, description, is_active) VALUES
    (11, 3, 'Is overriding dynamic binding?', b'1');
insert into Question (question_id, category_id, description, is_active) VALUES
    (12, 3, 'Can A function without virtual do dynamic binding in C++?', b'1');
insert into Question (question_id, category_id, description, is_active) VALUES
    (13, 3, 'Which one is pointer symbol?', b'1');
insert into Question (question_id, category_id, description, is_active) VALUES
    (14, 3, 'Which one is reference symbol?', b'1');
insert into Question (question_id, category_id, description, is_active) VALUES
    (15, 3, 'Which one is bit operator?', b'1');
insert into Question (question_id, category_id, description, is_active) VALUES
    (16, 3, 'Can I override any operator?', b'1');

# Dota Choice
insert into Choice (choice_id, question_id, description, is_correct) VALUES (1, 1, 'Yes', b'0');
insert into Choice (choice_id, question_id, description, is_correct) VALUES (2, 1, 'No', b'1');

insert into Choice (choice_id, question_id, description, is_correct) VALUES (3, 2, 'Yes', b'0');
insert into Choice (choice_id, question_id, description, is_correct) VALUES (4, 2, 'No', b'1');

insert into Choice (choice_id, question_id, description, is_correct) VALUES (5, 3, 'Yes', b'1');
insert into Choice (choice_id, question_id, description, is_correct) VALUES (6, 3, 'No', b'0');

insert into Choice (choice_id, question_id, description, is_correct) VALUES (7, 4, 'Agile', b'0');
insert into Choice (choice_id, question_id, description, is_correct) VALUES (8, 4, 'Strength', b'0');
insert into Choice (choice_id, question_id, description, is_correct) VALUES (9, 4, 'Intelligence', b'0');
insert into Choice (choice_id, question_id, description, is_correct) VALUES (10, 4, 'Universal', b'1');

insert into Choice (choice_id, question_id, description, is_correct) VALUES (11, 5, 'Yes', b'1');
insert into Choice (choice_id, question_id, description, is_correct) VALUES (12, 5, 'No', b'0');
# Java choice
insert into Choice (choice_id, question_id, description, is_correct) VALUES (13, 6, 'Yes', b'1');
insert into Choice (choice_id, question_id, description, is_correct) VALUES (14, 6, 'No', b'0');

insert into Choice (choice_id, question_id, description, is_correct) VALUES (15, 7, 'Yes', b'0');
insert into Choice (choice_id, question_id, description, is_correct) VALUES (16, 7, 'No', b'1');

insert into Choice (choice_id, question_id, description, is_correct) VALUES (17, 8, 'Yes', b'0');
insert into Choice (choice_id, question_id, description, is_correct) VALUES (18, 8, 'No', b'1');

insert into Choice (choice_id, question_id, description, is_correct) VALUES (19, 9, 'int', b'0');
insert into Choice (choice_id, question_id, description, is_correct) VALUES (20, 9, 'long', b'0');
insert into Choice (choice_id, question_id, description, is_correct) VALUES (21, 9, 'Boolean', b'1');

insert into Choice (choice_id, question_id, description, is_correct) VALUES (22, 10, 'Yes', b'0');
insert into Choice (choice_id, question_id, description, is_correct) VALUES (23, 10, 'No', b'1');
# C++ choice
insert into Choice (choice_id, question_id, description, is_correct) VALUES (24, 11, 'Yes', b'1');
insert into Choice (choice_id, question_id, description, is_correct) VALUES (25, 11, 'No', b'0');

insert into Choice (choice_id, question_id, description, is_correct) VALUES (26, 12, 'Yes', b'0');
insert into Choice (choice_id, question_id, description, is_correct) VALUES (27, 12, 'No', b'1');

insert into Choice (choice_id, question_id, description, is_correct) VALUES (28, 13, '*', b'1');
insert into Choice (choice_id, question_id, description, is_correct) VALUES (29, 13, '/', b'0');
insert into Choice (choice_id, question_id, description, is_correct) VALUES (30, 13, '+', b'0');

insert into Choice (choice_id, question_id, description, is_correct) VALUES (31, 14, '*', b'0');
insert into Choice (choice_id, question_id, description, is_correct) VALUES (32, 14, '&', b'1');
insert into Choice (choice_id, question_id, description, is_correct) VALUES (33, 14, '+', b'0');

insert into Choice (choice_id, question_id, description, is_correct) VALUES (34, 15, '^', b'1');
insert into Choice (choice_id, question_id, description, is_correct) VALUES (35, 15, '&', b'0');
insert into Choice (choice_id, question_id, description, is_correct) VALUES (36, 15, '+', b'0');

insert into Choice (choice_id, question_id, description, is_correct) VALUES (37, 16, 'Yes', b'0');
insert into Choice (choice_id, question_id, description, is_correct) VALUES (38, 16, 'No', b'1');

# Quiz
insert into quiz (quiz_id, user_id, category_id, name, time_start, time_end) VALUES
    (1, 2, 1, 'Dota test 1', CURRENT_TIMESTAMP()-INTERVAL 1 HOUR, CURRENT_TIMESTAMP()-INTERVAL 30 MINUTE );
insert into quiz (quiz_id, user_id, category_id, name, time_start, time_end) VALUES
    (2, 2, 3, 'C++ test 1', CURRENT_TIMESTAMP()-INTERVAL 20 MINUTE , CURRENT_TIMESTAMP()-INTERVAL 10 MINUTE );
# Quiz Question
    # Dota
insert into quizquestion (qq_id, quiz_id, question_id, user_choice_id) VALUES (1, 1, 1, 1);
insert into quizquestion (qq_id, quiz_id, question_id, user_choice_id) VALUES (2, 1, 2, 3);
insert into quizquestion (qq_id, quiz_id, question_id, user_choice_id) VALUES (3, 1, 3, 5);
insert into quizquestion (qq_id, quiz_id, question_id, user_choice_id) VALUES (4, 1, 4, 10);
insert into quizquestion (qq_id, quiz_id, question_id, user_choice_id) VALUES (5, 1, 5, 11);
    # Java
insert into quizquestion (qq_id, quiz_id, question_id, user_choice_id) VALUES (6, 2, 11, 24);
insert into quizquestion (qq_id, quiz_id, question_id, user_choice_id) VALUES (7, 2, 12, 26);
insert into quizquestion (qq_id, quiz_id, question_id, user_choice_id) VALUES (8, 2, 13, 29);
insert into quizquestion (qq_id, quiz_id, question_id, user_choice_id) VALUES (9, 2, 14, 32);
insert into quizquestion (qq_id, quiz_id, question_id, user_choice_id) VALUES (10, 2, 15, 36);