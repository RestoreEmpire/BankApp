package com.restoreempire.mvc.repository;

import com.restoreempire.mvc.model.Bank;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {


}
