package by.vlad.test.task_fabrika_resheniy.controller;

import by.vlad.test.task_fabrika_resheniy.dto.QuestionDto;
import by.vlad.test.task_fabrika_resheniy.entity.Question;
import by.vlad.test.task_fabrika_resheniy.service.PollService;
import by.vlad.test.task_fabrika_resheniy.service.QuestionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/polls/{pollId}")
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private PollService pollService;
    @Autowired
    private ModelMapper modelMapper;

    /**
     * @return all questions of a poll
     */
    @GetMapping("/questions")
    public List<QuestionDto> getAllQuestions(@PathVariable Integer pollId) {
        var poll = pollService.getPoll(pollId).get();
        return poll.getQuestions().stream()
                .map(question -> modelMapper.map(question, QuestionDto.class))
                .collect(Collectors.toList());
    }

    /**
     * @param question is a saving question
     * @return saved question
     */
    @PostMapping("/questions")
    public QuestionDto addQuestion(@PathVariable Integer pollId, @RequestBody Question question) {
        var poll = pollService.getPoll(pollId).get();
        question.setPoll(poll);
        return modelMapper.map(questionService.saveQuestion(question), QuestionDto.class);
    }

    /**
     * @param question is updating question
     * @return updated question
     */
    @PutMapping("/questions")
    public QuestionDto updateQuestion(@PathVariable Integer pollId, @RequestBody Question question) {
        if (questionService.getQuestion(question.getId()).isEmpty()) {
            throw new RuntimeException("There is no question with ID = " + question.getId());
        }
        return modelMapper.map(questionService.saveQuestion(question), QuestionDto.class);
    }

    /**
     * @param questionId is id of deleting question
     * @return result of deleting
     */
    @DeleteMapping("/questions/{questionId}")
    public String deleteQuestion(@PathVariable(name = "questionId") Integer questionId,
                                 @PathVariable(name = "pollId") Integer pollId) {
        if (questionService.getQuestion(questionId).isEmpty()) {
            throw new RuntimeException("there is no question with ID = " + questionId);
        }
        questionService.deleteQuestion(questionId);
        return "question was deleted";
    }
}
