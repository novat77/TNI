package com.sincera.intern.repository;

import com.sincera.intern.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

	@Query("SELECT u from User u Where u.username = :username")
	public User getUserByUsername(@Param("username") String username);


	@Query("SELECT u from User u Where u.username = :username")
	List<User>  getUsersByUsername(@Param("username") String username);
	@Query("SELECT u FROM User u WHERE " +
			"(:id IS NULL OR u.id = :id) " +
//			"AND (:role IS NULL OR :role = '' OR u.role = :role) " +
			"AND (:createdDate IS NULL OR u.createdDate = :createdDate) " +
			"AND (:username IS NULL OR :username = '' OR u.username LIKE %:username%) ")
	List<User> getUsersByAttributes(
			@Param("id") Long id,
//			@Param("role") String role,
			@Param("createdDate") Date createdDate,
			@Param("username") String username);
	
}
