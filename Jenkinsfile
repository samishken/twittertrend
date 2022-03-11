def imageName = 'stalin.jfrog.io/default-docker-local/twittertrend'
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
                echo '<--------------- Building --------------->'
                sh 'printenv'
                sh 'mvn clean deploy -Dmaven.test.skip=true'
                echo '<------------- Build completed --------------->'
            }
        }
        stage('Jar Publish') {
           steps {
             scripts {
                 echo '<--------------- Jar publishing Started --------------->'
                 server = Artifactory.newServer url:registry+"/artifactory" ,  credentialsId:"artifactorycredentialid"
                 def properties = env.BUILD_ID;
                 def uploadSpec = """{
                      "files": [
                        {
                          "pattern": "jarstaging/(*)",
                          "target": "default-maven-local/{1}",
                          "flat": "false",
                          "props" : "${properties}",
                          "excludePatterns": [ "*.sha1", "*.md5"]
                        }
                     ]
                 }"""
                 def buildInfo = server.upload(uploadSpec)
                 buildInfo.env.collect()
                 server.publishBuildInfo(buildInfo)
                 echo '<--------------- Jar Publishing finished --------------->'
             }
           }
        }
        stage('Docker Build') {
            steps {
              script{
                echo '<---------------  Building Docker Image --------------->'
                //app = docker.build(imageName)
                echo '<---------------  Building Docker Image Finished --------------->'
              }
            }
        }
        stage('Docker Publish') {
            steps {
              script{
                echo '<---------------  Publishing Docker Image --------------->'
                docker.withRegistry(registry, 'artifactorycredentialid') {
                   //app.push("latest")
                   //docker.image(imageName).push(env.BUILD_ID)
                echo '<---------------  Publishing Docker Image finished --------------->'
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
