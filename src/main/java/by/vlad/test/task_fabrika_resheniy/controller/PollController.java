package by.vlad.test.task_fabrika_resheniy.controller;

import by.vlad.test.task_fabrika_resheniy.dto.PollDto;
import by.vlad.test.task_fabrika_resheniy.entity.Poll;
import by.vlad.test.task_fabrika_resheniy.service.PollService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PollController {

    @Autowired
    private PollService pollService;
    @Autowired
    private ModelMapper modelMapper;

    /**
     * @return all current polls
     */
    @GetMapping("/polls")
    public List<Poll> showAllPolls() {
        var now = LocalDate.now();
        var allPolls = pollService.getAllPolls().stream()
                .filter(poll -> (poll.getStartDate().isAfter(now) || poll.getStartDate().isEqual(now))
                        && (poll.getFinishDate().isBefore(now) || poll.getFinishDate().isEqual(now)))
                .collect(Collectors.toList());
        return allPolls;
    }

    /**
     * @param poll is saving poll
     * @return saved poll
     */
    @PostMapping("/polls")
    public PollDto savePoll(@RequestBody Poll poll) {
        if (poll.getStartDate().isBefore(LocalDate.now())) {
            throw new RuntimeException("Start date should be after yesterday");
        }
        return modelMapper.map(pollService.savePoll(poll), PollDto.class);
    }

    /**
     * @param poll is updating poll with id != 0
     * @return updated poll
     */
    @PutMapping("/polls")
    public PollDto updatePoll(@RequestBody Poll poll) {
        poll.setStartDate(pollService.getPoll(poll.getId()).get().getStartDate());
        return modelMapper.map(pollService.savePoll(poll), PollDto.class);
    }

    /**
     * @param pollId is id of deleting poll
     * @return result of deleting
     */
    @DeleteMapping("/polls/{pollId}")
    public String deletePoll(@PathVariable Integer pollId) {
        pollService.deletePoll(pollId);
        return "poll with ID = " + pollId + " was deleted";
    }

    /**
     * @param pollId is id of wanted poll
     * @return poll with pollId
     */
    @GetMapping("/polls/{pollId}")
    public PollDto getPoll(@PathVariable Integer pollId) {
        var maybePoll = pollService.getPoll(pollId);
        if (maybePoll.isEmpty()) {
            throw new RuntimeException("There is no poll with this ID");
        }
        return modelMapper.map(maybePoll.get(), PollDto.class);
    }
}
