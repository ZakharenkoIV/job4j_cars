package ru.job4j.cars.dao;

import ru.job4j.cars.model.Role;

public class RoleRepository extends Repository {

    private RoleRepository() {
    }

    public static RoleRepository getInstance() {
        return RoleRepository.SingleRoleRepository.INSTANCE;
    }

    public int save(Role role) {
        return (int) transactionWrapper(session -> session.save(role));
    }

    public Role getRoleById(int roleId) {
        return transactionWrapper(session -> session.get(Role.class, roleId));
    }

    public void truncateTable() {
        transactionWrapper(session -> session.
                createSQLQuery("truncate table roles RESTART IDENTITY").executeUpdate());
    }

    private static class SingleRoleRepository {
        private static final RoleRepository INSTANCE = new RoleRepository();
    }
}