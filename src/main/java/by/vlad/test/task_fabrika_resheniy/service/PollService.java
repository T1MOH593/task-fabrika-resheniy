package by.vlad.test.task_fabrika_resheniy.service;

import by.vlad.test.task_fabrika_resheniy.entity.Poll;
import by.vlad.test.task_fabrika_resheniy.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PollService {

    @Autowired
    private PollRepository pollRepository;

    public List<Poll> getAllPolls() {
        return pollRepository.findAll();
    }

    public Optional<Poll> getPoll(Integer id) {
        return pollRepository.findById(id);
    }

    public Poll savePoll(Poll poll) {
        return pollRepository.save(poll);
    }

    public void deletePoll(Integer id) {
        pollRepository.deleteById(id);
    }
}
