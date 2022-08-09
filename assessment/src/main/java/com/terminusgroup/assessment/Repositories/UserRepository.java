package com.terminusgroup.assessment.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.terminusgroup.assessment.models.User;

public interface UserRepository extends  JpaRepository<User, Long>{

}
