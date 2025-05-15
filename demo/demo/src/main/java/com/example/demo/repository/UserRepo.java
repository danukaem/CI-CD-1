package com.example.demo.repository;

import com.example.demo.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
//public interface UserRepo extends CrudRepository<User,String> {

//    @Query(nativeQuery = true, value = "select * from user where name = ?1")
//    List<User> findUserByName(String name);

    @Query(nativeQuery = true, value = "select * from user where name = ?1")
    Optional<User> findUserByName(String name);


// 1st  level , 2nd level caching

//    @Modifying @Transactional
//    @Query(nativeQuery = true, value = "delete from user where id = :id")
//    int deleteUserById(@Param("id") int id);


    @Modifying @Transactional
    @Query(nativeQuery = true, value = "delete from user where id = ?1")
    int deleteUserById( int id);
}

