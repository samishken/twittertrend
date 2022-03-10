def imageName = 'stalin.lenin@gmail.com/twittertrend'
def registry  = 'https://registry.slowcoder.com'
def app
pipeline {
    agent {
       node {
         label "devops"
      }
    }
    stages {
        def app
        stage('Build') {
            steps {
                echo 'Building..'
                sh 'mvn clean deploy -Dmaven.test.skip=true'
            }
        }
        stage('Docker Build') {
            steps {
                echo 'Building Docker Image..'
                app = docker.build(imageName)
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
