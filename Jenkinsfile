pipeline {
    /* insert Declarative Pipeline here */
    agent any

    stages {
        stage('Build Jar') {
            steps {
                    bat "mvn clean package -DskipTests"
                }
            }

        stage('Build Image') {
            steps {
                bat "docker build -t=atharvahiwase7/dockerselenium:latest ."
            }
        }

        stage('Push Image') {
            environment {
                DOCKER_HUB = credentials('dockerhub-credentials')
            }
            steps {
                bat 'docker login -u %DOCKER_HUB_USR% -p %DOCKER_HUB_PSW%'
                bat "docker push atharvahiwase7/dockerselenium:latest"
            }
        }
    }

    post {
        always {
            bat "docker logout"
        }
    }
}