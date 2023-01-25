pipeline {
    agent {
        docker {
            image 'openjdk:17-alpine'
        }
    }
    stages {
        stage('Build') {
            env.token=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
            steps {
                git 'https://github.com/paycii/WhatTheChatSays'
                sh "mvn install -Dchat_api_token=${env.token}"
            }
        }
        stage('Docker Build') {
            steps {
                // script {
                //     def customImage = docker.build("my-app:${env.BUILD_NUMBER}")
                // }
                sh "docker build -e token=${env.token} -t WhatTheChatSays ."
            }
        }
    }
}
