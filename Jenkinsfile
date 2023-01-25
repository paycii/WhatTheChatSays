pipeline {
    agent any
    stages {
        stage('Build') {
            env.token=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
            steps {
                git 'https://github.com/paycii/WhatTheChatSays'
                sh "mvn install -Dchat_api_token=$env.token"
            }
        }
        stage('Docker Build') {
            steps {
                // script {
                //     def customImage = docker.build("my-app:${env.BUILD_NUMBER}")
                // }
                 sh "cp target/*.jar $WORKSPACE"
                
                sh "docker build -e token=$env.token -t WhatTheChatSays ."
            }
        }
    }
}
