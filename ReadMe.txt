1)Make sure u have java 17

2)i use postgresql locally so please make sure you install postgres and adjust the application.properties file eith you configuration

3)my app run on port 8080 , so please run these command in cmd to make sure port will listen
"netstat -ano | findstr :8080"
"taskkill /PID <PID> /F"

4)if you faced this "Error: Could not find or load main class in intelliJ IDE" please try running this command "rm -rf .idea *.iml"

5)run the app and you can find attached postman project for the app

