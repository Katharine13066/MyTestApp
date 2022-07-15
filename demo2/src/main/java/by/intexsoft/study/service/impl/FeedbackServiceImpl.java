package by.intexsoft.study.service.impl;

import by.intexsoft.study.LibraryApplication;
import by.intexsoft.study.mapper.FeedbackMapper;
import by.intexsoft.study.model.Feedback;
import by.intexsoft.study.model.FeedbackDto;
import by.intexsoft.study.repository.FeedbackDao;
import by.intexsoft.study.service.FeedbackService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("feedbackService")
public class FeedbackServiceImpl implements FeedbackService {

    private static final Logger logger = LogManager.getLogger(LibraryApplication.class);

    private FeedbackDao feedbackDao;

    private FeedbackMapper feedbackMapper;

    @Autowired
    public FeedbackServiceImpl(FeedbackDao feedbackDao, FeedbackMapper feedbackMapper) {
        this.feedbackDao = feedbackDao;
        this.feedbackMapper = feedbackMapper;
    }

    @Override
    public List<FeedbackDto> getFeedbacksByBookId(Long bookId) {
        List<FeedbackDto> result = feedbackMapper.toDtos(feedbackDao.findFeedbacksByBookId(bookId));
        logger.info("Get feedbacks by book id");
        return result;
    }

    @Override
    public FeedbackDto findById(Long id) {
        FeedbackDto result = feedbackMapper.toDto(feedbackDao.findById(id));

        if(result == null){
            logger.warn("No feedback find by id");
            return null;
        }
        logger.info("Find feedback by id");
        return result;
    }

    @Override
    public List<FeedbackDto> findAll() {
        List<FeedbackDto> result = feedbackMapper.toDtos(feedbackDao.findAll());
        logger.info("Get all feedbacks");
        return result;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        feedbackDao.deleteById(id);
        logger.info("Delete feedback by id");
    }

    @Override
    @Transactional
    public FeedbackDto create(FeedbackDto feedbackDto) {
        FeedbackDto result = feedbackMapper.toDto(feedbackDao.createEntity(feedbackMapper.fromDto(feedbackDto)));
        logger.info("Create new feedback");
        return result;
    }

    @Override
    @Transactional
    public FeedbackDto update(FeedbackDto feedbackDto) {
        FeedbackDto result = feedbackMapper.toDto(feedbackDao.updateEntity(feedbackMapper.fromDto(feedbackDto)));
        logger.info("Update feedback");
        return result;
    }

    @Override
    @Transactional
    public void patch(FeedbackDto feedbackDto) {
        feedbackMapper.updateFeedbackFromDto(feedbackDto, feedbackDao.findById(feedbackDto.getId()));
        logger.info("Patch feedback");
    }
}
