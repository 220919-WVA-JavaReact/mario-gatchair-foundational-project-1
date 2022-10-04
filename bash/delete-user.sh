#!/bin/bash;

read -p	"Enter your Username: " duser_name;

sh parser.sh MOCK_DATA.csv | grep -A5 "Username: $user_name$";

if [[ "$dusername" == "$user_name" ]];
then
 	echo "Enter your information to delete it"
    read -p "First name: " first_name;
    read -p "Last name: " last_name;
    read -p "Email address: " email;
    read -p "Username: " user_name;
    read -p "Account number: " account_number;
fi
string="$first_name,$last_name,$email,$user_name,$account_number";
grep -v $string MOCK_DATA.csv > tmpfile && mv tmpfile MOCK_DATA.csv