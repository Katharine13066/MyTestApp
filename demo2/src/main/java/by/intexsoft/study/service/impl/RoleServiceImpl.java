package by.intexsoft.study.service.impl;

import by.intexsoft.study.mapper.RoleMapper;
import by.intexsoft.study.model.RoleDto;
import by.intexsoft.study.repository.RoleDao;
import by.intexsoft.study.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private RoleMapper roleMapper;

    public RoleServiceImpl(RoleDao roleDao, RoleMapper roleMapper) {
        this.roleDao = roleDao;
        this.roleMapper = roleMapper;
    }

    @Override
    public RoleDto findByRoleName(String roleName) {
        return roleMapper.toDto(roleDao.findByRoleName(roleName));
    }
}
