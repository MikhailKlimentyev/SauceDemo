pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M3"
    }

    stages {
        stage('Running tests') {
            steps {

                // Get some code from a GitHub repository
                git 'https://github.com/MikhailKlimentyev/SauceDemo.git'

                // Run Maven on a Unix agent.
                //sh "mvn -Dmaven.test.failure.ignore=true clean package"

                // To run Maven on a Windows agent, use
                bat "mvn clean -DSauceDemoUser=${SauceDemoUser} -DSauceDemoLockedUser=${SauceDemoLockedUser} -DSauceDemoPass=${SauceDemoPass} install"
            }
            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                }
            }
        }
        stage('Generating Allure Report') {
                steps {
                script {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: 'target/allure-results']]
                    ])
                }
            }
        }
    }
}