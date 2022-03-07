package com.restoreempire.mvc.repository;

import java.util.Arrays;

import javax.transaction.Transactional;

import com.restoreempire.mvc.model.Bank;
import com.restoreempire.mvc.repository.mysql.MysqlBankRepository;

import org.jboss.logging.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = com.restoreempire.mvc.config.PostgresJpaConfig.class)
@Transactional
public class BankRepositoryTest {

    Logger log = Logger.getLogger(BankRepositoryTest.class);

    @Autowired
    private MysqlBankRepository bankRepository;


     @BeforeEach
     @Rollback
     public void testData(){
         Bank tinkoff = new Bank();
         tinkoff.setName("Tinkoff");


         Bank sber = new Bank();
         tinkoff.setName("Sber");


         Bank vtb = new Bank();
         tinkoff.setName("Vtb");


         Bank alfa = new Bank();
         tinkoff.setName("Alfa");

         bankRepository.saveAll(Arrays.asList(tinkoff, sber, vtb, alfa));
     }

    @Test
    public void findAlltest(){

        var list = bankRepository.findAll();
        list.forEach((s)-> log.info(s.toString()));
    }
}
