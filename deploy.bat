@echo off
call "apache-maven-3.6.1/bin/mvn" clean install
del /q deployed
md deployed
echo "Move questions.xlsx..."
copy questions.xlsx deployed\questions.xlsx
echo "Move readme.txt..."
copy readme.txt deployed\readme.txt
echo "Move testing-1.0.4.jar..."
copy target\testing-1.0.4.jar deployed\testing-1.0.4.jar
echo "Build success, look at folder 'deployed' deployed"
pause