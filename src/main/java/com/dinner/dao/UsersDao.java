package com.dinner.dao;

import com.dinner.models.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

/**
 * Created by Brut on 16.02.2016.
 */
@Repository
@Table(name = "users")
public interface UsersDao extends JpaRepository<UserDto, String> {

}
