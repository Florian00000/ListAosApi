package com.florian.aos.securityservice.dto.role;

import com.florian.aos.securityservice.entity.Role;
import lombok.Data;

@Data
public class RoleDtoGet {
    private int id;
    private String role;

    public RoleDtoGet(Role roleEntity) {
        this.id = roleEntity.getId();
        this.role = roleEntity.getRole();
    }
}
