# task-fabrika-resheniy

To set up service you should run method main in TaskFabrikaResheniyApplication.java

To have a role ADMIN put username = vlad  password = vlad
To have a role USER put username = elena  password = elena

API:
  - GET /polls get call current polls
  - POST /polls add pooll
  - PUT /polls update poll (start date can't be changed)
  - DELETE /polls/{pollId} delete poll
  - GET /polls/{pollId} get poll
  
  - GET /polls/{pollId}/questions get all questions of the poll
  - POST /polls/{pollId}/questions add question to poll
  - PUT /polls/{pollId}/questions update question
  - DELETE /polls/{pollId}/questions/{questionId} delete question
  
  - POST /polls/{pollId}/questions/{questionId}/{username} add answer to question
  - GET /polls/{username}/mypolls to get user's polls, questions and answers
