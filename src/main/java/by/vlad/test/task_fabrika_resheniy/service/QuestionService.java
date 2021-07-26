package by.vlad.test.task_fabrika_resheniy.service;

import by.vlad.test.task_fabrika_resheniy.entity.Question;
import by.vlad.test.task_fabrika_resheniy.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }

    public void deleteQuestion(Integer id) {
        questionRepository.deleteById(id);
    }

    public Optional<Question> getQuestion(Integer id) {
        return questionRepository.findById(id);
    }
}
