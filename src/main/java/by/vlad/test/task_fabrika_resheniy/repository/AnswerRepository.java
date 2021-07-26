package by.vlad.test.task_fabrika_resheniy.repository;

import by.vlad.test.task_fabrika_resheniy.entity.Answer;
import by.vlad.test.task_fabrika_resheniy.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {

     List<Answer> findAllByUsernameAndQuestion(String username, Question question);
     List<Answer> findAllByUsername(String username);

}
