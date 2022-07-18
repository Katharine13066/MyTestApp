package by.intexsoft.study.repository;

import by.intexsoft.study.model.User;

public interface UserDao extends Dao<User> {
    User findByEmail(String email);
    User findByUserName(String userName);

}
