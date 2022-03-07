package com.restoreempire.mvc.repository.postgres;

import com.restoreempire.mvc.model.Bank;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface PostgresBankRepository extends JpaRepository<Bank, Long> {


}
