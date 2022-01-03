package ru.job4j.cars.dao;

import ru.job4j.cars.model.User;

public class UserRepository extends Repository {

    private UserRepository() {
    }

    public static UserRepository getInstance() {
        return UserRepository.SingleUserRepository.INSTANCE;
    }

    private static class SingleUserRepository {
        private static final UserRepository INSTANCE = new UserRepository();
    }

    public int save(User user) {
        return (int) transactionWrapper(session -> session.save(user));
    }

    public User getUserById(int userId) {
        return transactionWrapper(session -> session.get(User.class, userId));
    }

    public User getUserByEmail(String userEmail) {
        return transactionWrapper(session -> session.createQuery(
                "FROM User u WHERE u.email = :email", User.class)
                .setParameter("email", userEmail)
                .getSingleResult());
    }

    public void truncateTable() {
        transactionWrapper(session -> session.
                createSQLQuery("truncate table users RESTART IDENTITY").executeUpdate());
    }
}
