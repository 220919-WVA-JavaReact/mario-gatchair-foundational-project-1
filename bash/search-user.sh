#!/bin/bash:

read -p	"Enter your Username: " user_name;

sh parser.sh MOCK_DATA.csv | grep -A1 -B4  "Username: $user_name$";

