#!/bin/bash;

echo "What would you like to do?";

echo "Sign up?"
echo "Delete account?"
echo "Search?"
#prompt for user to sign up, delete or search for existing account
read answer;

case $answer in
    'Sign up')
        sh add-user.sh
        ;;
    'Delete account')
        sh delete-user.sh
         ;;
    'Search')
        sh search-user.sh
        ;;
    *)
esac