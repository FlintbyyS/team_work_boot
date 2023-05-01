DROP TABLE IF EXISTS lecturer_groups;
DROP TABLE IF EXISTS lecturer_subject;
DROP TABLE IF EXISTS group_subject;
DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS grade;
DROP TABLE IF EXISTS students;
DROP TABLE IF EXISTS student_group;
DROP TABLE IF EXISTS subjects;
DROP TABLE IF EXISTS departments;
DROP TABLE IF EXISTS user_role;
DROP TABLE IF EXISTS users;

DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE users
(
    id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    email       VARCHAR     UNIQUE                NOT NULL,
    password    VARCHAR                           NOT NULL,
    enabled     BOOLEAN                           NOT NULL
) ;

CREATE TABLE user_role (
                             user_id BIGINT,
                             role varchar(25),
                             FOREIGN KEY (user_id) references users(id)
) ;

CREATE TABLE employees
(
    id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name        VARCHAR                           NOT NULL,
    surname     VARCHAR                           NOT NULL,
    school      VARCHAR                           NOT NULL,
    department  VARCHAR                           NOT NULL,
    post        VARCHAR                           NOT NULL,
    workload    INTEGER                           NOT NULL,
    salary      INTEGER                           NOT NULL,
    email       VARCHAR                           NOT NULL,
    user_id     INTEGER                           ,
    FOREIGN KEY (user_id) references users(id)
) ;
CREATE TABLE student_group (
                               name VARCHAR                           NOT NULL,
                               students INTEGER                           NOT NULL,
                               PRIMARY KEY (name)
) ;
CREATE TABLE students (
                          id  INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
                          name VARCHAR                           NOT NULL,
                          surname VARCHAR                           NOT NULL,
                          school VARCHAR                           NOT NULL,
                          direction VARCHAR                           NOT NULL,
                          specialization VARCHAR                           NOT NULL,
                          course INTEGER                           NOT NULL,
                          group_name VARCHAR                           NOT NULL,
                          omissions INTEGER                           NOT NULL,
                          scores INTEGER                           NOT NULL,
                          user_id     INTEGER                       ,
                          FOREIGN KEY (group_name)  REFERENCES student_group (name),
                          FOREIGN KEY (user_id) references users(id)
) ;
CREATE TABLE subjects (
                                id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
                                name VARCHAR                           NOT NULL
);

CREATE TABLE grade (
                             id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
                             subject VARCHAR                           NOT NULL,
                             group_name VARCHAR                           NOT NULL,
                             student_id INTEGER                           NOT NULL,
                             omissions INTEGER     default (0)                     NOT NULL,
                             scores INTEGER        default (0)                  NOT NULL,
                             UNIQUE  (subject,group_name,student_id),
                             FOREIGN KEY (student_id) REFERENCES students(id) on DELETE CASCADE );

CREATE TABLE lecturer_groups (
                                       LECTURER_ID INTEGER                           NOT NULL,
                                       group_name VARCHAR                           NOT NULL,
                                       PRIMARY KEY (LECTURER_ID, group_name),
                                       FOREIGN KEY (LECTURER_ID) REFERENCES employees(id) on DELETE CASCADE ,
                                       FOREIGN KEY (group_name) REFERENCES student_group(name)on DELETE CASCADE );

CREATE TABLE lecturer_subject (
                                        LECTURER_ID INTEGER                           NOT NULL,
                                        SUBJECT_ID INTEGER                           NOT NULL,
                                        PRIMARY KEY (LECTURER_ID, SUBJECT_ID),
                                        FOREIGN KEY (LECTURER_ID) REFERENCES employees(id) on DELETE CASCADE ,
                                        FOREIGN KEY (SUBJECT_ID) REFERENCES subjects(ID) on DELETE CASCADE );

CREATE TABLE group_subject (
                                     group_name VARCHAR                           NOT NULL,
                                     SUBJECT_ID INTEGER                           NOT NULL,
                                     PRIMARY KEY (group_name, SUBJECT_ID),
                                     FOREIGN KEY (group_name) REFERENCES student_group(name) on DELETE CASCADE ,
                                     FOREIGN KEY (SUBJECT_ID) REFERENCES subjects(id) on DELETE CASCADE );
CREATE TABLE departments (
                             department VARCHAR                           NOT NULL,
                             school VARCHAR                           NOT NULL,
                             short_department VARCHAR                           NOT NULL,
                             PRIMARY KEY (department)
) ;
