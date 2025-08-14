pipeline{
    agent any
    stages{
        stage("Git checkout")
        {
            steps{
                git branch: 'main', url: 'https://github.com/manjushabhopale/chatapp.git'
            }
        }
        stage("Build")
        {
            steps{
               sh 'mvn clean install' 
               sh 'whoami'
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
