package com.restoreempire.mvc.service;

import java.util.List;

import com.restoreempire.mvc.model.Bank;
import com.restoreempire.mvc.repository.mysql.MysqlBankRepository;
import com.restoreempire.mvc.repository.postgres.PostgresBankRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BankService {
    
    @Autowired
    MysqlBankRepository mysqlRepo;

    @Autowired
    PostgresBankRepository postgresRepo;
    
    @Transactional(transactionManager = "mysqlTransactionManager")
    public void saveToMysql(Bank bank){
        mysqlRepo.save(bank);
    }

    @Transactional(transactionManager = "postgresTransactionManager")
    public void saveToPostgres(Bank bank){
        postgresRepo.save(bank);
    }

    @Transactional(transactionManager = "mysqlTransactionManager")
    public List<Bank> showAllBanksFromMysql(){
        return mysqlRepo.findAll();
    }

    @Transactional(transactionManager = "postgresTransactionManager")    
    public List<Bank> showAllBanksFromPostgres(){
        return postgresRepo.findAll();
    }
}
