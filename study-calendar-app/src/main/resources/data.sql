-- Students
insert into student (first_name, last_name, username, password, free_day_before_obligation, concentrated_study_hours, category, activity) values ('John', 'Doe', 'johndoe', 'john123', false, false, 0, 0);

-- Subjects
insert into subject (name, passed, grade, finished, student_id) values ('Machine learning', false, 0, false, 1);

-- Obligations
insert into obligation (name, date_and_time, study_hours, priority, obligation_type, max_points, earned_points, passed, corrigible, finished, skipped, subject_id) values ('Project', '2022-05-20 10:00', 50, 2, 0, 50.0, 0.0, false, false, false, false, 1);
insert into obligation (name, date_and_time, study_hours, priority, obligation_type, max_points, earned_points, passed, corrigible, finished, skipped, subject_id) values ('Exam', '2022-05-28 14:00', 30, 1, 3, 50.0, 0.0, false, false, false, false, 1);
