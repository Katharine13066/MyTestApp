package by.intexsoft.study.repository;

import by.intexsoft.study.model.Feedback;
import java.util.List;

public interface FeedbackDao extends Dao<Feedback> {
    List<Feedback> findFeedbacksByBookId(Long bookId);
}
