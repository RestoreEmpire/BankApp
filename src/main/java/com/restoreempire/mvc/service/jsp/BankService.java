package com.restoreempire.mvc.service.jsp;

import java.util.List;

import com.restoreempire.mvc.model.Bank;
import com.restoreempire.mvc.repository.mysql.MysqlBankRepository;
import com.restoreempire.mvc.repository.postgres.PostgresBankRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankService {
    
    @Autowired
    MysqlBankRepository mysqlRepo;

    @Autowired
    PostgresBankRepository postgresRepo;
    
    public void saveToMysql(Bank bank){
        mysqlRepo.save(bank);
    }

    public void saveToPostgres(Bank bank){
        postgresRepo.save(bank);
    }

    public List<Bank> showAllBanksFromMysql(){
        return mysqlRepo.findAll();
    }

    public List<Bank> showAllBanksFromPostgres(){
        return postgresRepo.findAll();
    }
}
