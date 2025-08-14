
pipeline{
    agent any
    	tools{
	maven 'Maven-3.9.6'
		}
	environment {
        SONARQUBE = 'SonarQubeServer' // Name from Jenkins System config
	    }

	stages{
        stage("Git checkout")
        {
            steps{
                git branch: 'main', url: 'https://github.com/manjushabhopale/chatapp.git'
            }
        }

	stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv(credentialsId: 'sonar-token') {
                    sh 'mvn sonar:sonar'
                }
            }
        }
        stage("Build")
        {
            steps{
               sh 'mvn clean install' 
               sh 'whoami'
            }
        }
	stage('Check Maven') {
            steps {
                sh 'which mvn'
                sh 'mvn -version'
            }
        }
        stage("Docker build")
        {
            steps{
                sh 'docker build -t chatapp .'
            }
        }
        stage("Docker Run")
        {
            steps{
                sh 'docker run -d -p 8081:8081 chatapp:latest'
            }
        }
        
    }
    
}
