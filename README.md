# WhatTheChatSays
This is a simple tool that uses the OpenAI API to generate responses to questions and saving them based on a provided string.
# Requirements
Java 17

Maven3
Docker(optional)
# Usage #
1. Starting by cloning the repo : https://github.com/paycii/WhatTheChatSays
2. Using Maven to build the project : <code>mvnw clean package -Dchat_token_api=token</code> 
The D flag is for token arg to be passed 
3. We can now start our app with <code>java -Dchat_api_token=API_TOKEN -jar target/*.jar</code>
4. Or if using Docker we ll need to build it with the Dockerfile : <code> docker build -t what-the-chat-says . </code>
5. Run the container (don't forget to bind a folder where the output will be saved) : <code> docker run -v /path/to/folder:/app/localstorage -e token=YOUR_API_TOKEN -p 7777:7777 what-the-chat-says </code>
6. Else, if using jenkins in a linux machine and assuming docker and maven are installed use the Jenkinsfile to build the jar and the image.
7. You can now use POST method to test the API eg POST to http://url-server:7777/WhatTheChatSays/answer, please make sure that the header content is set to application/json and that the body contains a String
# Description #
The code contains a single controller endpoint ready for POST methods (/WhatTheChatSays/answer), it return a method called OpenAISErvice.getAnswer() that treats the input and contact OpenAI API, you'll notice a package mappers where I defined the necessary POJOs i need for the API. Also there are 2 additional classes for writing the answers to files
