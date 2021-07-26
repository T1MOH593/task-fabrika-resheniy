package by.vlad.test.task_fabrika_resheniy.repository;

import by.vlad.test.task_fabrika_resheniy.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
}
