@echo off
echo Running TestNG Tests...
mvn clean test

echo Generating Allure Report...
allure generate allure-results --clean -o allure-report

echo Opening Allure Report in Browser...
allure open allure-report

pause
