Preconditions:
Set up SauceDemoUser, SauceDemoLockedUser, SauceDemoPass environment variables

Run tests into windows:
1) With properties and environment variables:
mvn clean -Dbrowser=chrome -Dheadless=false -DSauceDemoUser=%SauceDemoUser% -DSauceDemoLockedUser=%SauceDemoLockedUser% -DSauceDemoPass=%SauceDemoPass% install

2) Without properties (default values) and with environment variables:
mvn clean -DSauceDemoUser=%SauceDemoUser% -DSauceDemoLockedUser=%SauceDemoLockedUser% -DSauceDemoPass=%SauceDemoPass% install

3) Without properties (default values) and without environment variables (values from config.properties file):
mvn clean install

