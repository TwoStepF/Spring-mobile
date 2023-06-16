package com.example.opentalk.repository;

import com.example.opentalk.entity.OTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpRepository extends JpaRepository<OTP, String> {
    @Query(value = "select * from tokentoconfirmemail as t join employee as e on t.employee_id = e.id where e.name = :name", nativeQuery = true)
    OTP findByEmployeeName(@Param("name") String name);

    @Query(value = "select * from token_to_confirm_email where token_id = :id", nativeQuery = true)
    OTP findTokenToConfirmEmailByToken_id(@Param("id") String id);

    @Query(value = "select * from token_to_confirm_email where token_id = :id", nativeQuery = true)
    OTP findOTPBy(@Param("id") String id);
}
