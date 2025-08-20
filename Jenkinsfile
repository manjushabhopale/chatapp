pipeline {
    agent any

    tools {
        maven 'Maven-3.9.6'
    }

    environment {
        SONARQUBE = 'SonarQubeServer' // Name from Jenkins System config
    }

    stages {
        stage("Git checkout") {
            steps {
                git branch: 'main', url: 'https://github.com/manjushabhopale/chatapp.git'
            }
        }

	stage("Build")
        {
            steps{
               sh 'mvn clean install' 
            }
        }
	stage('Static Code Analysis') {
     	 environment {
        	SONAR_URL = "http://65.1.35.156:9000"
      		}	
      		steps {
        		withCredentials([string(credentialsId: 'sonar-token', variable: 'SONAR_AUTH_TOKEN')]) {
          		sh 'mvn sonar:sonar -Dsonar.login=$SONAR_AUTH_TOKEN -Dsonar.host.url=${SONAR_URL}'
        		}
      			}
    		}
       
       
        stage("Docker build") {
            steps {
                sh 'docker build -t chatapp .'
            }
        }

        stage("Docker Run") {
            steps {
                sh 'docker run -d -p 8081:8081 chatapp:latest'
            }
        }
        stage("view s3 buckets") {
            steps {
                withAWS(region: 'ap-south-a', credentials: 'aws-creds'){
                    sh 'aws s3 ls'
                }
            }
        }
        stage("push docker image to ECR") {
            steps {
                sh 'add the code here'
            }
        }
    }
}
