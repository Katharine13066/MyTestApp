package by.intexsoft.study.service.impl;

import by.intexsoft.study.mapper.FeedbackMapper;
import by.intexsoft.study.model.Feedback;
import by.intexsoft.study.model.FeedbackDto;
import by.intexsoft.study.repository.FeedbackDao;
import by.intexsoft.study.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("feedbackService")
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackDao feedbackDao;

    @Autowired
    private FeedbackMapper feedbackMapper;

    public FeedbackServiceImpl(FeedbackDao feedbackDao, FeedbackMapper feedbackMapper) {
        this.feedbackDao = feedbackDao;
        this.feedbackMapper = feedbackMapper;
    }

    @Override
    public List<FeedbackDto> getFeedbacksByBookId(Long bookId) {
        return feedbackMapper.toDtos(feedbackDao.findFeedbacksByBookId(bookId));
    }

    @Override
    public FeedbackDto findById(Long id) {
        return feedbackMapper.toDto(feedbackDao.findById(id));
    }

    @Override
    public List<FeedbackDto> findAll() {
        List<Feedback> feedbacks = feedbackDao.findAll();
        return feedbackMapper.toDtos(feedbacks);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        feedbackDao.deleteById(id);
    }

    @Override
    @Transactional
    public FeedbackDto create(FeedbackDto feedbackDto) {
        return feedbackMapper.toDto(feedbackDao.createEntity(feedbackMapper.fromDto(feedbackDto)));
    }

    @Override
    @Transactional
    public FeedbackDto update(FeedbackDto feedbackDto) {
        return feedbackMapper.toDto(feedbackDao.updateEntity(feedbackMapper.fromDto(feedbackDto)));
    }

    @Override
    @Transactional
    public void patch(FeedbackDto feedbackDto) {
        feedbackMapper.updateFeedbackFromDto(feedbackDto, feedbackDao.findById(feedbackDto.getId()));
    }
}