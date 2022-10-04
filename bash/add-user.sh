#!/bin/bash;
read -p "First name: " first_name;
read -p "Last name: " last_name;
read -p "Email address: " email;
read -p "Username: " user_name;
read -p "Account number: " account_number;
string="$first_name,$last_name,$email,$user_name,$account_number";
echo $string >> MOCK_DATA.csv;