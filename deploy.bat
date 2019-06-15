@echo off
mvn clean install
del deployed
md deployed
echo "Перенос файла start.bat..."
copy start.bat deployed\start.bat
echo "Перенос файла questions.xlsx..."
copy questions.xlsx deployed\questions.xlsx
echo "Перенос файла readme.txt..."
copy readme.txt deployed\readme.txt
echo "Перенос файла testing-1.0.4.jar..."
copy target\testing-1.0.4.jar deployed\testing-1.0.4.jar
echo "Билд успешно завершён, готовый проект в папке deployed"
pause