# task-fabrika-resheniy

To set up service you should set up PostgreSQL, write in application.properties url, password, username
 and run method main in TaskFabrikaResheniyApplication.java

To have a role ADMIN put username = vlad  password = vlad
To have a role USER put username = elena  password = elena

API:
  - GET /polls get call current polls (ADMIN, USER)
  - POST /polls add pooll (ADMIN)
  - PUT /polls update poll (start date can't be changed) (ADMIN)
  - DELETE /polls/{pollId} delete poll (ADMIN)
  - GET /polls/{pollId} get poll (ADMIN, USER)
  
  - GET /polls/{pollId}/questions get all questions of the poll (ADMIN, USER)
  - POST /polls/{pollId}/questions add question to poll (ADMIN)
  - PUT /polls/{pollId}/questions update question (ADMIN)
  - DELETE /polls/{pollId}/questions/{questionId} delete question (ADMIN)
  
  - POST /polls/{pollId}/questions/{questionId}/{username} add answer to question (USER)
  - GET /polls/{username}/mypolls to get user's polls, questions and answers (USER)
