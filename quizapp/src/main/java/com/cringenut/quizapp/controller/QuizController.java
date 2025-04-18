package com.cringenut.quizapp.controller;

import com.cringenut.quizapp.model.Question;
import com.cringenut.quizapp.model.Response;
import com.cringenut.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category,
                                             @RequestParam int numQuestions,
                                             @RequestParam String title) {
        return quizService.createQuiz(category, numQuestions, title);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<Question>> getQuizQuestions(@PathVariable Integer id) {
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuestion(@PathVariable Integer id,
                                                  @RequestBody List<Response> responses) {
        return quizService.calculateResult(id, responses);
    }

}
