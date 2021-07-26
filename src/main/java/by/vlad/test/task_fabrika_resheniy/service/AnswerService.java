package by.vlad.test.task_fabrika_resheniy.service;

import by.vlad.test.task_fabrika_resheniy.entity.Answer;
import by.vlad.test.task_fabrika_resheniy.repository.AnswerRepository;
import by.vlad.test.task_fabrika_resheniy.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QuestionRepository questionRepository;

    public List<Answer> getAllAnswers(String username, Integer questionId) {
        var question = questionRepository.findById(questionId).get();
        var answers = answerRepository.findAllByUsernameAndQuestion(username, question);
        return answers;
    }

    public Answer saveAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    public List<Answer> getAnswersByUsername(String username) {
        return answerRepository.findAllByUsername(username);
    }
}
