package by.vlad.test.task_fabrika_resheniy.controller;

import by.vlad.test.task_fabrika_resheniy.dto.AnswerDto;
import by.vlad.test.task_fabrika_resheniy.dto.PollDto;
import by.vlad.test.task_fabrika_resheniy.dto.QuestionDto;
import by.vlad.test.task_fabrika_resheniy.entity.Answer;
import by.vlad.test.task_fabrika_resheniy.service.AnswerService;
import by.vlad.test.task_fabrika_resheniy.service.PollService;
import by.vlad.test.task_fabrika_resheniy.service.QuestionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/polls")
public class AnswerController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private PollService pollService;

    @Autowired
    private QuestionService questionService;

    /**
     * @param pollId is id of the poll on which user answers
     * @param questionId is id of question on which user answers
     * @param username is user who puts the answer
     * @param answer
     * @return saved answer
     */
    @PostMapping("/{pollId}/questions/{questionId}/{username}")
    public AnswerDto saveAnswer(@PathVariable(name = "pollId") Integer pollId,
                             @PathVariable(name = "questionId") Integer questionId,
                             @PathVariable(name = "username") String username,
                             @RequestBody Answer answer) {
        var answer1 = pollService.getPoll(pollId).map(poll -> {
            var question = questionService.getQuestion(questionId).get();
            question.setPoll(poll);
            answer.setUsername(username);
            answer.setQuestion(question);
            return answerService.saveAnswer(answer);
        }).get();
        return modelMapper.map(answer1, AnswerDto.class);
    }

    /**
     * @param username
     * @return all polls in which the user participated,
     * all questions on which the user answered,
     * and all user's answers
     */
    @GetMapping("/{username}/mypolls")
    public ResponseEntity<Map<String, Object>> getUsersPolls(@PathVariable String username) {
        var answers = answerService.getAnswersByUsername(username);
        var pollList = answers.stream()
                .map(answer -> answer.getQuestion().getPoll())
                .distinct()
                .collect(Collectors.toList());
        var questionList = answers.stream()
                .map(Answer::getQuestion)
                .distinct()
                .collect(Collectors.toList());
        Map<String, Object> response = new HashMap<>();
        response.put("polls", pollList.stream().map(poll -> modelMapper.map(poll, PollDto.class)));
        response.put("questions", questionList.stream().map(question -> modelMapper.map(question, QuestionDto.class)));
        response.put("answers", answers.stream().map(answer -> modelMapper.map(answer, AnswerDto.class)));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
