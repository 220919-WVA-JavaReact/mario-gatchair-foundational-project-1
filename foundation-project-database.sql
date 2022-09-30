CREATE TABLE Employee(
	ID int NOT NULL UNIQUE,
	"first name" varchar(40),
	"last name" varchar(40),
	user_name varchar(25) NOT NULL PRIMARY KEY,
	"password" varchar(30) NOT NULL,
	ticket_number int UNIQUE
);

CREATE TABLE Manager(
	ID int NOT NULL UNIQUE,
	"first name" varchar(40),
	"last name" varchar(40),
	user_name varchar(25) NOT NULL PRIMARY KEY,
	"password" varchar(30) NOT NULL,
	ADMIN bool,
	tickets int NOT NULL UNIQUE
);

CREATE TABLE Reimbursment(
	user_name varchar(25) REFERENCES Employee,
	ticket_number int REFERENCES Employee (ticket_number),
	ID int REFERENCES Employee (ID),
	amount money NOT NULL,
	description TEXT,
	managed_by int REFERENCES Manager (tickets)
);
	
