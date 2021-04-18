pipeline {
    agent any

    stages{
        stage('Checkout') {
            steps {
                git url: 'https://github.com/rxbao/jgsu-spring-petclinic.git', branch: 'main'
            }
        }
        stage ('Build') {
            steps {
                sh './mvnw clean compile'
            }

            post {
                always {
                    junit '**/target/surefile-reports/TEST-*.xml'
                    arciveArtifacts 'target/*.jar'
                }
            }
        }
    }
}