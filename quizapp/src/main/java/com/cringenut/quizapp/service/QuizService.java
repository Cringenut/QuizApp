package com.cringenut.quizapp.service;

import com.cringenut.quizapp.Dao.QuestionDao;
import com.cringenut.quizapp.Dao.QuizDao;
import com.cringenut.quizapp.model.Question;
import com.cringenut.quizapp.model.QuestionWrapper;
import com.cringenut.quizapp.model.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQuestions, String title) {
        List<Question> questions = questionDao.findRandomByCategory(category, numQuestions);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return ResponseEntity.ok("Quiz created");
    }

    public ResponseEntity<List<Question>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questions = quiz.get().getQuestions();
        List<QuestionWrapper> forUserQuestions = new ArrayList<>();

        questions.forEach(question -> {
            QuestionWrapper questionWrapper = new QuestionWrapper(
                    question.getQuestionTitle(),
                    question.getQuestion(),
                    question.getOption1(),
                    question.getOption2(),
                    question.getOption3(),
                    question.getOption4()
            );

            forUserQuestions.add(questionWrapper);
        });

        return forUserQuestions.isEmpty() ? ResponseEntity.ok(questions) : ResponseEntity.ok(questions);
    }
}
