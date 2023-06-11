package com.example.doanjava.repository;

import com.example.doanjava.entity.ItemInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.doanjava.entity.Role;

import java.util.List;

@Repository
public interface IRoleRepository extends JpaRepository<Role,Long> {
    @Query("SELECT r.id FROM Role r WHERE r.name = ?1")
    Long getRoleIdByName(String roleName);
    Role findRoleById(Long id);
}
