  pipeline {
    agent any
    environment {
        token = "YOUR-TOKEN-API"
    }
    stages {
        stage('Build') {
            
            steps {
               git branch :'main',
               url: 'https://github.com/paycii/WhatTheChatSays'
               sh "chmod u+x ./mvnw"
               sh "./mvnw clean package -Dchat_api_token=$token"
            }
        }
        stage('Docker Build') {
            steps {
                
                //sh "cp target/*.jar $WORKSPACE"
                
                sh "docker build --build-arg token=$token -t what-the-chat-says ."
            }
        }
    }
}
