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
                bat "docker build -t=atharvahiwase7/dockerselenium ."
            }
        }

        stage('Push Image') {
            steps {
                bat "docker push atharvahiwase7/dockerselenium"
            }
        }
    }
}