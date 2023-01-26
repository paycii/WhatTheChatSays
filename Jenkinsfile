pipeline {
    agent any
    stages {
        stage('Build') {
            env.token=YOUR-TOKEN-API
            steps {
                sh "git clone https://github.com/paycii/WhatTheChatSays"
                sh "cd WhatTheChatSays"
                sh "chmod u+x ./mvnw"
                sh "./mvnw install -Dchat_api_token=$env.token"
            }
        }
        stage('Docker Build') {
            steps {
                
                 sh "cp target/*.jar $WORKSPACE"
                
                sh "docker build --build-arg token=$env.token -t WhatTheChatSays ."
            }
        }
    }
}
