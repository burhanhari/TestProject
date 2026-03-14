pipeline {
    agent any

    tools {
        // Match this name to your Jenkins JDK configuration
        jdk 'JDK17'
    }

    options {
        timestamps()
        ansiColor('xterm')
    }

    parameters {
        string(name: 'TAGS', defaultValue: '', description: 'Cucumber tags, e.g. @smoke and not @wip')
        choice(name: 'BROWSER', choices: ['chrome', 'firefox', 'edge'], description: 'Browser for WebDriver')
        choice(name: 'ENV', choices: ['qa', 'stage', 'prod'], description: 'Target environment')
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Verify Java') {
            steps {
                bat 'java -version'
            }
        }

        stage('Test') {
            steps {
                bat ".\\gradlew testNG -Dcucumber.filter.tags=\"%TAGS%\" -Dbrowser=%BROWSER% -Denv=%ENV%"
            }
        }
    }

    post {
        always {
            junit 'build/test-results/testNG/*.xml'
            archiveArtifacts artifacts: 'target/cucumber-testng-report.html', fingerprint: true, onlyIfSuccessful: false
            publishHTML(target: [
                allowMissing: true,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'target',
                reportFiles: 'cucumber-testng-report.html',
                reportName: 'Cucumber TestNG Report'
            ])
        }
    }
}
