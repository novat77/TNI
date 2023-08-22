package com.sincera.intern.repository;


import com.sincera.intern.model.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends CrudRepository<Role, Integer> {

//    @Query("SELECT a.id from Site a Where a.roles = :roles")
//    Long getrole(@Param("roles") Long roles);
}
