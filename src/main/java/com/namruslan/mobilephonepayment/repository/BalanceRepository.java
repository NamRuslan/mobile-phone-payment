package com.namruslan.mobilephonepayment.repository;

import com.namruslan.mobilephonepayment.entity.PhoneBalanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BalanceRepository extends JpaRepository<PhoneBalanceEntity, Integer> {

    Optional<PhoneBalanceEntity> findByPhoneNumber(int number);

    List<PhoneBalanceEntity> findAllByCustomerName(String name);
}
