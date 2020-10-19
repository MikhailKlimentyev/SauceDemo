TMS Lesson_7 Find locators for https://www.saucedemo.com/
=======================================================================

1. Обновить версии всех библиотек в проекте и ее вывод:
mvn versions:display-dependency-updates
[INFO] The following dependencies in Dependencies have newer versions:
[INFO]   org.seleniumhq.selenium:selenium-java ...... 3.141.59 -> 4.0.0-alpha-6
[INFO]   org.testng:testng ..................................... 7.1.0 -> 7.3.0

mvn versions:use-latest-versions
[INFO] Major version changes allowed
[INFO] Updated org.testng:testng:jar:7.1.0 to version 7.3.0
[INFO] Updated org.seleniumhq.selenium:selenium-java:jar:3.141.59 to version 4.0.0-alpha-6

2. Запустить тесты используя mvn clean test команду и ее вывод
Tests run: 19, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 112.814 sec

3. Использовать параметры для запуска конкретных тестов и методов:
mvn -Dtest=LoginTest#productsPageShouldBeOpenedAfterSuccessfulLogin+logoutShouldLeadToLoginPage test

[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 12.666 s - in by.teachmeskills.tests.LoginTest

4. Пробросить параметр из mvn command line внутрь теста
mvn clean test -Dbrowser=chrome
