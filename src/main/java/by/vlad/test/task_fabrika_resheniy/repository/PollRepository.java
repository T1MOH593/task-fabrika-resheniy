package by.vlad.test.task_fabrika_resheniy.repository;

import by.vlad.test.task_fabrika_resheniy.entity.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollRepository extends JpaRepository<Poll, Integer> {
}
