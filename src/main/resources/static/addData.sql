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