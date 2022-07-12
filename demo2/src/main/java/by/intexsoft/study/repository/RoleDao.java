package by.intexsoft.study.repository;

import by.intexsoft.study.model.Role;

public interface RoleDao extends Dao<Role> {

    Role findByRoleName(String roleName);
}
