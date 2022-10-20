CREATE TABLE Employee(
	ID serial PRIMARY KEY,
	"first" varchar(40),
	"last" varchar(40),
	email varchar(40),
	username varchar(25) NOT NULL UNIQUE,
	"password" varchar(30) NOT NULL
);

CREATE TABLE Manager(
	ID serial PRIMARY KEY,
	"first" varchar(40),
	"last" varchar(40),
	email varchar(40),
	username varchar(25) NOT NULL UNIQUE,
	"password" varchar(30) NOT NULL
);

CREATE TABLE Reimbursement(
	ID serial PRIMARY KEY,
	employee_id int REFERENCES Employee,
	amount numeric NOT NULL,
	status varchar(25),
	description TEXT,
	"type" varchar(25),
	handled_by int REFERENCES Manager
);

	
INSERT INTO Employee ("first", "last", email, username, "password") VALUES ('Jule', 'Ropartz', 'jropartz0@indiatimes.com', 'jropartz0', 'iZcoUSbSE');
INSERT INTO Employee ("first", "last", email, username, "password") VALUES ('Teresita', 'Putland', 'tputland1@nifty.com', 'tputland1', 'tVwR6Kqa');
INSERT INTO Employee ("first", "last", email, username, "password") VALUES ('Leora', 'Berk', 'lberk2@canalblog.com', 'lberk2', 'OY21oB0');
INSERT INTO Employee ("first", "last", email, username, "password") VALUES ('Verene', 'Zoren', 'vzoren3@tuttocitta.it', 'vzoren3', 'Hw865cTi');
INSERT INTO Employee ("first", "last", email, username, "password") VALUES ('Roma', 'Saunter', 'rsaunter4@list-manage.com', 'rsaunter4', 'SBg0FjPnP3P3');

INSERT INTO Manager ("first", "last", email, username, "password") VALUES ('Veradis', 'Guerrea', 'vlewsey0@army.mil', 'vguerre0', 'FbmbqhHd1H');
INSERT INTO Manager ("first", "last", email, username, "password") VALUES ('Desdemona', 'Horlocke', 'dhorlock1@sphinn.com', 'dlowndsborough1', '7qlqT52iQz');
INSERT INTO Manager ("first", "last", email, username, "password") VALUES ('Nanon', 'Livingston', 'nglanders2@cnn.com', 'nkilvington2', 'KfCPsjxBP4Q');


