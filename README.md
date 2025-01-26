**Offline First Weather App**

This is an offline-first weather app. Pulls weather information from [[openweathermap API](http://api.openweathermap.org/data/2.5/)]

UI inspiration: Samsung's weather app and <a target="_blank" href="https://dribbble.com/shots/17998271-Cuacane-Weather-App?utm_source=Clipboard_Shot&utm_campaign=mochamadhakim&utm_content=Cuacane%20-%20Weather%20App&utm_medium=Social_Share&utm_source=Clipboard_Shot&utm_campaign=mochamadhakim&utm_content=Cuacane%20-%20Weather%20App&utm_medium=Social_Share">this free design</a>

How to run it:
1. Clone the repository to your android studio
2. Create a `local.properties` file on the root project folder if it doesn't already exist and add your Openweathermap API key. (Create an account at Openweathermap to get an API key). Add this line:

   `API_KEY="your-api-key-here"`
   

Tools and Concepts used:

1. Kotlin and Jetpack Compose
2. Clean architecture
3. MVI Presentational Pattern
4. Dependency Injection with Koin
5. Retrofit HTTP Client
6. Room DB - Offline storage on the device


Screenshots:


Light Theme:

<img src="https://github.com/user-attachments/assets/98448d51-75ac-4d3b-b790-4075f15b2e17" width="150">
&nbsp; &nbsp; &nbsp; 
<img src="https://github.com/user-attachments/assets/0bf3cb7f-b3c7-4a89-9f5f-3dda5044fa93" width="150">
&nbsp; &nbsp; &nbsp; 
<img src="https://github.com/user-attachments/assets/e11495eb-d7b6-44aa-b665-2539eb0e1f8f" width="150">
&nbsp; &nbsp; &nbsp; 
<img src="https://github.com/user-attachments/assets/f779dc0b-2c95-4c09-8a73-6a5a92dd9e19" width="150">


Dark Theme:

<img src="https://github.com/user-attachments/assets/70942c50-69db-42f5-af91-f7425f278ac3" width="150">
&nbsp; &nbsp; &nbsp; 
<img src="https://github.com/user-attachments/assets/298c8d02-e2cd-467a-bd78-bdfa23bfe398" width="150">
&nbsp; &nbsp; &nbsp; 
<img src="https://github.com/user-attachments/assets/160a01a8-5b05-43cf-aeac-74ec49807eb8" width="150">
&nbsp; &nbsp; &nbsp; 
<img src="https://github.com/user-attachments/assets/8fede4be-6148-44e5-871a-d4f56d96f8bd" width="150">
