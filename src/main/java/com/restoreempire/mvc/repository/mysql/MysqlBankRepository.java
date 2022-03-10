package com.restoreempire.mvc.repository.mysql;

import com.restoreempire.mvc.model.Bank;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(transactionManager = "mysqlTransactionManager")
public interface MysqlBankRepository extends JpaRepository<Bank, Long> {


}
