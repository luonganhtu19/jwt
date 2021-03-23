package com.example.jwt.service.role;

import com.example.jwt.model.Role;
import com.example.jwt.repository.IRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    IRolesRepository iRolesRepository;
    @Override
    public Iterable<Role> findAll() {
        return iRolesRepository.findAll();
    }

    @Override
    public Role save(Role role) {
        return iRolesRepository.save(role);
    }

    @Override
    public Optional<Role> findById(Long id) {
        return iRolesRepository.findById(id);
    }

    @Override
    public void remove(Role role) {
         iRolesRepository.delete(role);
    }
}
