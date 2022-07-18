package by.intexsoft.study.controller;

import by.intexsoft.study.api.FeedbackApi;
import by.intexsoft.study.model.FeedbackDto;
import by.intexsoft.study.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FeedbackController implements FeedbackApi {
    private FeedbackService feedbackService;

    @Autowired
    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }


    @Override
    public ResponseEntity<Void> createFeedback(FeedbackDto feedbackDto) {
        feedbackService.create(feedbackDto);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteFeedbackById(Long id) {
        feedbackService.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<FeedbackDto>> findAllFeedbacks() {
        return ResponseEntity.ok(feedbackService.findAll());
    }

    @Override
    public ResponseEntity<FeedbackDto> findByIdFeedback(Long id) {
        return new ResponseEntity<>(feedbackService.findById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<FeedbackDto>> getFeedbacksByBookId(Long bookId) {
        return ResponseEntity.ok(feedbackService.getFeedbacksByBookId(bookId));
    }

    @Override
    public ResponseEntity<Void> patchFeedback(FeedbackDto feedbackDto) {
        feedbackService.patch(feedbackDto);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> updateFeedback(FeedbackDto feedbackDto) {
        feedbackService.update(feedbackDto);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
