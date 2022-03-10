def imageName = 'stalin.jfrog.io/twittertrend'
def registry  = 'https://stalin.jfrog.io'
def app
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
              script{
                echo 'Building Docker Image..'
                app = docker.build(imageName)
              }
            }
        }
        stage('Docker Publish') {
            steps {
              script{
                echo 'Publishing Docker Image..'
                docker.withRegistry(registry, 'artifactorycredentialid') {
                       docker.image(imageName).push(env.BUILD_ID)
                }
              }
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
                cleanWs()
                sh """
                echo "Cleaned Up Workspace For Project"
                """
            }
        }
    }
}
