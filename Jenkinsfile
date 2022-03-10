pipeline {
    agent {
       node {
         label "devops"
      }
    }
    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                sh 'mvn clean deploy -Dmaven.test.skip=true'
            }
        }
        stage('Docker Build') {
            steps {
                echo 'Building Docker Image..'
                app = docker.build("stalin.jfrog.io/twittertrend")
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
        stage('Cleanup Workspace') {
            steps {
                //cleanWs()
                sh """
                echo "Cleaned Up Workspace For Project"
                """
            }
        }
    }
}
