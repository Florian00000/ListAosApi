package com.florian.aos.securityservice.controller;

import com.florian.aos.securityservice.dto.role.RoleDtoGet;
import com.florian.aos.securityservice.dto.role.RoleDtoPost;
import com.florian.aos.securityservice.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/create-role")
    public ResponseEntity<RoleDtoGet> createRole(@RequestBody RoleDtoPost roleDtoPost){
        return ResponseEntity.status(201).body(adminService.createRole(roleDtoPost));
    }

    @DeleteMapping("/delete-role")
    public ResponseEntity<String> deleteRole(@RequestBody RoleDtoPost roleDtoPost){
        adminService.deleteRole(roleDtoPost);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Role are deleted");
    }

    @PatchMapping("/add-role-to-user/{id}")
    public ResponseEntity<String> addRoleToUser(@RequestBody RoleDtoPost roleDtoPost,
                                                @PathVariable long id){
        adminService.addRoleToUser(roleDtoPost, id);
        return ResponseEntity.ok("Role " + roleDtoPost.getRole() + " add to User");
    }
}
