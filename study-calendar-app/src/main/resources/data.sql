-- Admins
insert into admin (first_name, last_name, username, password) values ('Jane', 'Doe', 'admin', 'admin');

-- Students
insert into student (first_name, last_name, username, password, concentrated_study_hours, category, activity_points, activity, status) values ('John', 'Doe', 'john', 'doe', false, 0, 0, 0, '');

--Calendar
insert into study_calendar (student_id, obligations_injected, priorities_calculated, sessions_created) values (1, false, false, false);

-- Subjects
insert into subject (name, earned_points, passed, grade, finished, student_id) values ('Machine learning', 0.0, false, 0, false, 1);

-- Obligations
insert into obligation (name, date_and_time, study_hours, priority, obligation_type, max_points, earned_points, passed, corrigible, finished, skipped, subject_id, study_start_date, study_end_date, study_calendar_id) values ('Project', '2022-05-20 10:00', 50, 2, 0, 50.0, 0.0, false, true, false, false, 1, '2022-07-01', '2022-07-05', 1);
insert into obligation (name, date_and_time, study_hours, priority, obligation_type, max_points, earned_points, passed, corrigible, finished, skipped, subject_id, study_start_date, study_end_date, study_calendar_id) values ('Exam', '2022-05-28 14:00', 30, 1, 3, 50.0, 0.0, false, false, false, false, 1, '2022-07-06', '2022-07-10', 1);
insert into obligation (name, date_and_time, study_hours, priority, obligation_type, max_points, earned_points, passed, corrigible, finished, skipped, subject_id, study_start_date, study_end_date, study_calendar_id) values ('Homework', '2022-08-28 14:00', 30, 1, 3, 10.0, 0.0, false, false, false, false, 1, '2022-07-06', '2022-07-10', 1);