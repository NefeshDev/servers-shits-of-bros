!#bin/bash
sudo apt-get update -y
sudo apt-get upgrade -y
sudo apt install nginx -y
sudo apt install openjdk-25-jdk -y 
sudo apt install ca-certificates curl -y
sudo cp devops/meu-server /etc/nginx/sites-available/meu-server
sudo ln -s /etc/nginx/sites-available/meu-server /etc/nginx/sites-enabled/
sudo rm -rf /etc/nginx/sites-enabled/default
sudo nginx -t
javac src/App.java
java src/App