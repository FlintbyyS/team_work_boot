DELETE FROM employees;
DELETE FROM student_group;
DELETE FROM students;
DELETE FROM subjects;
DELETE FROM grade;
DELETE FROM group_subject;
DELETE FROM lecturer_groups;
DELETE FROM lecturer_subject;
DELETE FROM departments;
DELETE FROM users;
DELETE FROM user_role;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (id,email, password, enabled)
VALUES
    (1,'sgt@tpu.ru', '{noop}sgt', true),
    (2,'mam59@tpu.ru', '{noop}mam', true),
    (3,'ivan@tpu.ru', '{noop}ivan', true);

INSERT INTO user_role (user_id, role)
VALUES
    (2, 'STUDENT'),
    (1, 'LECTURER'),
    (3, 'LECTURER'),
    (3, 'ADMIN');

INSERT INTO employees (name, surname, school, department, post, workload, salary, email)
VALUES
    ('Сергей','Цапко',	'ИШИТР','ОИТ','Доцент',150,36500,'sgt@tpu.ru'),
    ('Михаил','Мужикбаев',	'ИШИТР','ОИТ','Ассистент',120,19700,'mam59@tpu.ru'),
    ('Владимир','Попов','ИШИТР','ОИТ','Преподаватель',130,28100,'vnp@tpu.ru');
INSERT INTO student_group(name,students)
VALUEs
    ('8K03',22),
    ('8K04',24);
INSERT INTO students (name, surname,school,direction,specialization,course, group_name, omissions,scores)
VALUES
    ('Матвей', 'Науменко','ИШИТР','Программная инженерия','кто знает',3 ,'8K03',0,0),
    ('Даниил', 'Климов','ИШИТР','Программная инженерия','кто знает',3 , '8K03',0,0),
    ('Екатерина', 'Леонова','ИШИТР','Программная инженерия','кто знает',3 , '8K03',0,0),
    ('Денис', 'Финенко','ИШИТР','Программная инженерия','кто знает',3 , '8K03',0,0),
    ('Иван', 'Вебер','ИШИТР','Программная инженерия','кто знает',3 , '8K03',0,0),
    ('Михаил', 'Семенов','ИШИТР','Программная инженерия','кто знает',3 , '8K04',0,0),
    ('Александра', 'Смирнова','ИШИТР','Программная инженерия','кто знает',3 , '8K04',0,0),
    ('Михаил', 'Мужикбаев','ИШИТР','Программная инженерия','кто знает',3 , '8K04',0,0),
    ('Полина', 'Астахова','ИШИТР','Программная инженерия','кто знает',3 , '8K04',0,0),
    ('Елизавета', 'Колотова','ИШИТР','Программная инженерия','кто знает',3 , '8K04',0,0);

INSERT INTO subjects(id,name)
VALUEs
    (1,'WEB'),
    (2,'WEB - course paper');

INSERT INTO grade(subject, group_name,student_id,omissions,scores)
VALUEs
    ('WEB','8K04',100008,0,5),
    ('WEB','8K04',100009,1,6),
    ('WEB','8K04',100010,2,7),
    ('WEB','8K04',100011,3,8),
    ('WEB','8K04',100012,4,9),
    ('WEB','8K03',100003,5,10),
    ('WEB','8K03',100004,6,11),
    ('WEB','8K03',100005,7,12),
    ('WEB','8K03',100006,8,13),
    ('WEB','8K03',100007,9,14),
    ('WEB - course paper','8K04',100008,21,26),
    ('WEB - course paper','8K04',100009,22,27),
    ('WEB - course paper','8K04',100010,23,28),
    ('WEB - course paper','8K04',100011,24,29),
    ('WEB - course paper','8K04',100012,25,30),
    ('WEB - course paper','8K03',100003,10,15),
    ('WEB - course paper','8K03',100004,11,16),
    ('WEB - course paper','8K03',100005,12,17),
    ('WEB - course paper','8K03',100006,13,18),
    ('WEB - course paper','8K03',100007,14,19);

INSERT INTO group_subject(group_name,subject_id)
VALUEs
    ('8K04',1),
    ('8K04',2),
    ('8K03',1),
    ('8K03',2);
INSERT INTO lecturer_groups(LECTURER_ID,GROUP_NAME)
VALUEs
    (100002,'8K04'),
    (100002,'8K03'),
    (100000,'8K04');
INSERT INTO lecturer_subject(LECTURER_ID,subject_id)
VALUEs
    (100002,1),
    (100002,2),
    (100000,2);

INSERT INTO departments (department, school, short_department)
VALUES
    ('Division for Chemical Engineering', 'SESE', 'DCE'),
    ('Division for Geology', 'SESE', 'DG'),
    ('Division for Oil and Gas Engineering', 'SESE', 'DOGE'),
    ('Heriot-Watt Petroleum Engineering Approved Support Centre', 'SESE', 'HWPEASC'),
    ('Organization Department of School of Earth Sciences & Engineering', 'SESE', 'ODSESE'),
    ('Research Laboratory for Hydrogeochemistry', 'SESE', 'RLH'),
    ('Research and Education Cneter for Petroleum Chemistry and Technology', 'SESE', 'RECPCT'),
    ('Butakov Research Center', 'SEPE', 'Butakov RC'),
    ('Division for Power and Electrical Engineering', 'SEPE', 'DPEE'),
    ('Ecoenergy 4.0 Research Center', 'SEPE', 'ERC'),
    ('Heat and Mass Transfer Laboratory', 'SEPE', 'HMTL'),
    ('Organization Department of School of Energy and Power Engineering', 'SEPE', 'ODSEPE'),
    ('Division for Experimental Physics', 'SNSE', 'DEP'),
    ('Division for Nuclear-Fuel Cycle', 'SNSE', 'DNFC'),
    ('Engineering Center', 'SNSE', 'EC'),
    ('International Research Laboratory of X-Ray Optics', 'SNSE', 'IRLXO'),
    ('Laboratory of Radioactive Substances and Technology', 'SNSE', 'LRST'),
    ('Organization Department of School of Nuclear Science & Engineering', 'SNSE', 'ODSNSE'),
    ('Radiation Safety Office', 'SNSE', 'RSO'),
    ('Research Laboratory for Electronics and Automation of Physical Equipment', 'SNSE', 'RLEAPE'),
    ('Research Laboratory of Super High Frequency Technology', 'SNSE', 'RLSHFT'),
    ('Research Nuclear Reactor Center', 'SNSE', 'RNRC'),
    ('Weinberg Research Center', 'SNSE', 'WRC');
INSERT INTO departments (department, school, short_department)
VALUES
    ('Center for Certification, Testing and Diagnostics', 'SNDT', 'CCTD'),
    ('Center for Industrial Tomography', 'SNDT', 'CIT'),
    ('Division for Electronic Engineering', 'SNDT', 'DEE'),
    ('Division for Testing and Diagnostics', 'SNDT', 'DTD'),
    ('Laboratory for X-ray Inspection and Safety', 'SNDT', 'LXIS'),
    ('Organization Department of School of Non-Destructive Testing', 'SNDT', 'ODSNDT'),
    ('R&D Laboratory for Medical Engineering', 'SNDT', 'R&DLME'),
    ('R&D Laboratory for Portable Betatrons', 'SNDT', 'R&DLPB'),
    ('Test Center', 'SNDT', 'TC'),
    ('Division for Automation and Robotics', 'SCSR', 'DAR'),
    ('Division for Information Technology', 'SCSR', 'DIT'),
    ('Organization Department of School of Computer Science & Robotics', 'SCSR', 'ODSCSR'),
    ('Research Laboratory for Financial Technology', 'SCSR', 'RLFT'),
    ('Research Laboratory for Processing and Analysis of Big Data', 'SCSR', 'RLPABD'),
    ('Research Laboratory for Telecommunications, Instrument Engineering and Marine Geology', 'SCSR', 'RLTIEMG'),
    ('Division for Materials Science', 'SAMT', 'DMS'),
    ('Division for Mechanical Engineering', 'SAMT', 'DME'),
    ('Innovation Center for Nanomaterials and Nanotechnologies', 'SAMT', 'ICNN'),
    ('Kizhner Research Center', 'SAMT', 'Kizhner RC'),
    ('Organization Department of School of Advanced Manufacturing Technologies', 'SAMT', 'ODSAMT'),
    ('R&D Laboratory for Agrobiophotonics', 'SAMT', 'R&DLA'),
    ('R&D Laboratory for Clean Water', 'SAMT', 'R&DLCW'),
    ('R&D Laboratory for Pulse-Beam, Electric Discharge and Plasma Technologies', 'SAMT', 'R&DLPBEDPT');
INSERT INTO departments (department, school, short_department)
VALUES
    ('Business Incubator - Test Ground of Engineering Entrepreneurship', 'SEE', 'BI'),
    ('International Research Laboratory of Technology of Senior Citizen Well-Being Improvement', 'SEE', 'IRLTSCWBI'),
    ('Organization Department of School of Engineering Enterpreneurship', 'SEE', 'ODSEE'),
    ('Laboratory of High-Intensity Ion Implantation', 'RSHEP', 'LHIII'),
    ('Organization Department(RSHEP)', 'RSHEP', 'OD'),
    ('Research Laboratory for Electronics, Semiconductors and Dielectrics', 'RSHEP', 'RLESD'),
    ('International Research Center of Piezo and Magnetoelectric Materials', 'RSCABS', 'IRCPMM'),
    ('International Research Laboratory of Non-Bonded Interactions in Materials Chemistry', 'RSCABS', 'IRLNBIMC'),
    ('Laboratory of Chemical Engineering and Molecular Design', 'RSCABS', 'LCEMD'),
    ('Organization Department(RSCABS)', 'RSCABS', 'OD'),
    ('Research Center of Physical Materials Science and Composite Materials', 'RSCABS', 'RCPMSCM'),
    ('Shared Knowledge Center - Physical and Chemical Analysis Methods', 'RSCABS', 'SKC'),
    ('Division for Foreign Languages', 'SCEE', 'DFL'),
    ('Division for General Technical Subjects', 'SCEE', 'DGTS'),
    ('Division for Mathematics and Computer Sciences', 'SCEE', 'DMCS'),
    ('Division for Natural Sciences', 'SCEE', 'DNS'),
    ('Division for Physical Education', 'SCEE', 'DPE'),
    ('Division for Russian Language', 'SCEE', 'DRL'),
    ('Division for Social Sciences and Humanities', 'SCEE', 'DSSH'),
    ('Organization Department of School of Core Engineering Education', 'SCEE', 'ODSCEE');