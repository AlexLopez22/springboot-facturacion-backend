// prueba jenkins
pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                bat './mvnw clean package -DskipTests'
            }
        }
        stage('Unit Tests') {
            steps {
                bat './mvnw test'
            }
        }
        stage('API Tests') {
            steps {
                bat './mvnw test -Dtest=FacturacionTest'
            }
        }
    }
}
