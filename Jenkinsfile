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
        stage("push docker image to ECR") {
            steps {
                withAWS(region: 'ap-south-1', credentials: 'aws-creds'){
                   sh 'aws ecr get-login-password --region ap-south-1 | docker login --username AWS --password-stdin 307946649652.dkr.ecr.ap-south-1.amazonaws.com'
                   sh 'docker tag chatapp:latest 307946649652.dkr.ecr.ap-south-1.amazonaws.com/chatapp:latest'
                   sh 'docker push 307946649652.dkr.ecr.ap-south-1.amazonaws.com/chatapp:latest'
                }
            }
        }
        stage("Docker Run") {
            steps {
                sh 'docker run -d -p 8081:8081 chatapp:latest'
            }
        }
        
    }
}
