#!/bin/bash;
exec < $1 
read header

while IFS="," read -r first_name last_name email user_name account_number
do
  echo "First name: $first_name";
  echo "Last name: $last_name";
  echo "Email address: $email";
  echo "Username: $user_name";
  echo "Account Number: $account_number";
  echo "+------------------------------------+";
done
