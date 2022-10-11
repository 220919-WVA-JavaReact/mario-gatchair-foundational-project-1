CREATE TABLE Employee(
	employee_id serial PRIMARY KEY,
	"first" varchar(40),
	"last" varchar(40),
	email varchar(40),
	user_name varchar(25) NOT NULL UNIQUE,
	"password" varchar(30) NOT NULL
);

CREATE TABLE Manager(
	manager_id serial PRIMARY KEY,
	"first" varchar(40),
	"last" varchar(40),
	email varchar(40),
	user_name varchar(25) NOT NULL UNIQUE,
	"password" varchar(30) NOT NULL
);

CREATE TABLE Reimbursement(
	request_id serial PRIMARY KEY,
	employee_id int REFERENCES Employee,
	amount numeric NOT NULL,
	status varchar(25),
	description TEXT NOT NULL,
	"type" varchar(25) NOT NULL,
	handled_by int REFERENCES Manager
);

	
INSERT INTO Employee ("first", "last", email, user_name, "password") VALUES ('Jule', 'Ropartz', 'jropartz0@indiatimes.com', 'jropartz0', 'iZcoUSbSE');
INSERT INTO Employee ("first", "last", email, user_name, "password") VALUES ('Teresita', 'Putland', 'tputland1@nifty.com', 'tputland1', 'tVwR6Kqa');
INSERT INTO Employee ("first", "last", email, user_name, "password") VALUES ('Leora', 'Berk', 'lberk2@canalblog.com', 'lberk2', 'OY21oB0');
INSERT INTO Employee ("first", "last", email, user_name, "password") VALUES ('Verene', 'Zoren', 'vzoren3@tuttocitta.it', 'vzoren3', 'Hw865cTi');
INSERT INTO Employee ("first", "last", email, user_name, "password") VALUES ('Roma', 'Saunter', 'rsaunter4@list-manage.com', 'rsaunter4', 'SBg0FjPnP3P3');

insert into Manager ("first", "last", email, user_name, "password") values ('Gilly', 'Brehat', 'gbrehat0@java.com', 'gbrehat0', 'OuzQhb');
insert into Manager ("first", "last", email, user_name, "password") values ('Venita', 'Rival', 'vrival1@furl.net', 'vrival1', 'WeyGmw');
insert into Manager ("first", "last", email, user_name, "password") values ('Rip', 'Baish', 'rbaish2@walmart.com', 'rbaish2', 'VHK5Vj');
INSERT INTO manager ("first", "last", email, user_name, "password") VALUES ('Mario', 'Gatchair', 'margat22@aol.com', 'margat22', 'password');

UPDATE reimbursement SET status = 'approved', handled_by = 4 WHERE request_id = 5 RETURNING *;

INSERT INTO manager
SELECT * FROM employee 
WHERE 