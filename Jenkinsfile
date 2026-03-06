pipeline {
    agent any

    tools {
        maven 'Maven-3.9'   // nombre tal como está en Jenkins > Manage Jenkins > Tools
        jdk   'JDK-17'
    }

    environment {
        KARATE_ENV = "${params.ENVIRONMENT ?: 'local'}"
    }

    parameters {
        choice(
            name: 'ENVIRONMENT',
            choices: ['local', 'staging', 'prod'],
            description: 'Ambiente donde se ejecutarán los tests de Karate'
        )
        booleanParam(
            name: 'SOLO_SMOKE',
            defaultValue: false,
            description: 'Ejecutar solo los tests @smoke (más rápido)'
        )
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
                echo "🚀 Build #${BUILD_NUMBER} | Branch: ${GIT_BRANCH} | Env: ${KARATE_ENV}"
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests -q'
            }
        }

        stage('Unit Tests') {
            steps {
                // Excluye KarateRunner para que no corra aquí
                sh 'mvn test -Dtest="!KarateRunner" -q'
            }
            post {
                always {
                    junit allowEmptyResults: true,
                          testResults: 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Karate API Tests') {
            steps {
                script {
                    def karateOptions = params.SOLO_SMOKE ? '--tags @smoke' : '--tags ~@ignore'
                    sh """
                        mvn test \\
                          -Dtest=KarateRunner#testAll \\
                          -Dkarate.env=${KARATE_ENV} \\
                          -Dkarate.options="${karateOptions}" \\
                          -q
                    """
                }
            }
            post {
                always {
                    // Reporte HTML de Karate (generado automáticamente)
                    publishHTML(target: [
                        allowMissing:       false,
                        alwaysLinkToLastBuild: true,
                        keepAll:            true,
                        reportDir:          'target/karate-reports',
                        reportFiles:        'karate-summary.html',
                        reportName:         '📋 Karate API Report'
                    ])

                    // JUnit XML para que Jenkins marque el build como fallido si hay tests rotos
                    junit allowEmptyResults: true,
                          testResults: 'target/karate-reports/*.xml'
                }
            }
        }
    }

    post {
        success {
            echo "✅ Pipeline completado — todos los tests pasaron"
        }
        failure {
            echo "❌ Pipeline falló — revisa el Karate Report en la pestaña de arriba"
        }
        always {
            // Limpiar workspace para no acumular artefactos
            cleanWs()
        }
    }
}