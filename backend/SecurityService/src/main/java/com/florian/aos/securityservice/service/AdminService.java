package com.florian.aos.securityservice.service;

import com.florian.aos.securityservice.dto.role.RoleDtoGet;
import com.florian.aos.securityservice.dto.role.RoleDtoPost;
import com.florian.aos.securityservice.entity.Role;
import com.florian.aos.securityservice.entity.User;
import com.florian.aos.securityservice.exception.NotFoundException;
import com.florian.aos.securityservice.repository.RoleRepository;
import com.florian.aos.securityservice.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public AdminService(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }


    public RoleDtoGet createRole(RoleDtoPost roleDtoPost) {
        if (!roleRepository.existsByRoleIgnoreCase(roleDtoPost.getRole())){
            Role role = Role.builder()
                    .role( "ROLE_" + roleDtoPost.getRole().toUpperCase())
                    .build();
            role = roleRepository.save(role);
            return new RoleDtoGet(role);
        } else{
            throw new IllegalArgumentException("Same role already exist");
        }
    }

    private Role findRole(String role) {
        return roleRepository.findByRole("ROLE_" + role.toUpperCase())
                .orElseThrow(() -> new NotFoundException("Role are not found"));
    }

    public boolean deleteRole(RoleDtoPost roleDtoPost) {
        roleRepository.delete(findRole(roleDtoPost.getRole()));
        return true;
    }


    public boolean addRoleToUser(RoleDtoPost roleDtoPost, long idUser){
        Role role = findRole(roleDtoPost.getRole());
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new NotFoundException("User " + idUser +  " are not found"));
        if (!user.getRoles().contains(role)){
            user.getRoles().add(role);
            userRepository.save(user);
            return true;
        }else {
            throw new IllegalArgumentException("User " + idUser + " already has role: " + roleDtoPost.getRole());
        }

    }

}
