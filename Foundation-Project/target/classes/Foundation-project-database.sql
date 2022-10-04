CREATE TABLE Employee(
	ID serial PRIMARY KEY,
	"first name" varchar(40),
	"last name" varchar(40),
	email varchar(40),
	user_name varchar(25) NOT NULL UNIQUE,
	"password" varchar(30) NOT NULL
);

CREATE TABLE Manager(
	ID serial,
	"name" varchar(40),
	email varchar(40),
	user_name varchar(25) NOT NULL UNIQUE,
	"password" varchar(30) NOT NULL,
	ADMIN bool,
	ticket_status varchar(25) PRIMARY KEY
);

CREATE TABLE Reimbursement(
	ID serial REFERENCES Employee,
	amount money NOT NULL,
	status varchar(25) REFERENCES Manager (ticket_status),
	description TEXT
);

	
INSERT INTO Employee ("first name", "last name", email, user_name, "password") VALUES ('Jule', 'Ropartz', 'jropartz0@indiatimes.com', 'jropartz0', 'iZcoUSbSE');
INSERT INTO Employee ("first name", "last name", email, user_name, "password") VALUES ('Teresita', 'Putland', 'tputland1@nifty.com', 'tputland1', 'tVwR6Kqa');
INSERT INTO Employee ("first name", "last name", email, user_name, "password") VALUES ('Leora', 'Berk', 'lberk2@canalblog.com', 'lberk2', 'OY21oB0');
INSERT INTO Employee ("first name", "last name", email, user_name, "password") VALUES ('Verene', 'Zoren', 'vzoren3@tuttocitta.it', 'vzoren3', 'Hw865cTi');
INSERT INTO Employee ("first name", "last name", email, user_name, "password") VALUES ('Roma', 'Saunter', 'rsaunter4@list-manage.com', 'rsaunter4', 'SBg0FjPnP3P3');

INSERT INTO Manager ("name", email, user_name, "password", admin, ticket_status) VALUES ('Veradis', 'vlewsey0@army.mil', 'vguerre0', 'FbmbqhHd1H', 'true', 'Pending');
INSERT INTO Manager ("name", email, user_name, "password", admin, ticket_status) VALUES ('Desdemona', 'dhorlock1@sphinn.com', 'dlowndsborough1', '7qlqT52iQz', 'true', 'Approved');
INSERT INTO Manager ("name", email, user_name, "password", admin, ticket_status) VALUES ('Nanon', 'nglanders2@cnn.com', 'nkilvington2', 'KfCPsjxBP4Q', 'true', 'Denied');


