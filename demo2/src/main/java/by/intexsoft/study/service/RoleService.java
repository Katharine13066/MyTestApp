package by.intexsoft.study.service;

import by.intexsoft.study.model.RoleDto;

public interface RoleService {

    RoleDto findByRoleName(String roleName);
}
