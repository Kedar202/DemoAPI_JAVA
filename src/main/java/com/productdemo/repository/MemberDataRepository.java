package com.productdemo.repository;

import com.productdemo.entity.MemberData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberDataRepository extends JpaRepository<MemberData, Long> {

    @Query("select md from MemberData md where md.userId=:username")
    Optional<MemberData> findOneByEmailIgnoreCase(String username);

    @Query("SELECT c from MemberData c where c.userId= :userId")
    Optional<MemberData> findByUserId(String userId);
}
