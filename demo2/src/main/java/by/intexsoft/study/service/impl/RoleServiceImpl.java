package by.intexsoft.study.service.impl;

import by.intexsoft.study.LibraryApplication;
import by.intexsoft.study.mapper.RoleMapper;
import by.intexsoft.study.model.RoleDto;
import by.intexsoft.study.repository.RoleDao;
import by.intexsoft.study.service.RoleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

    private static final Logger logger = LogManager.getLogger(LibraryApplication.class);

    private RoleDao roleDao;

    private RoleMapper roleMapper;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao, RoleMapper roleMapper) {
        this.roleDao = roleDao;
        this.roleMapper = roleMapper;
    }

    @Override
    public RoleDto findByRoleName(String roleName) {
        RoleDto result = roleMapper.toDto(roleDao.findByRoleName(roleName));
        logger.info("Find role by roleName");
        return result;
    }
}
