package com.restoreempire.model;

import java.math.BigDecimal;

import com.restoreempire.exceptions.AccountFundsValidationException;
import com.restoreempire.logging.Logger;
import com.restoreempire.processing.data.generators.AccountNumberGenerator;
import com.restoreempire.processing.data.validators.Validation;

public class Account extends BaseModel{

    private String accountNumber;
    private Long bankId;
    private Long clientId;
    private BigDecimal funds = BigDecimal.ZERO;


    public Account(){

    }

    public Account(long id, String accountNumber, long bankId, long clientId, BigDecimal funds){
        setClientId(clientId);
        setBankId(bankId);
        setAccountNumber(accountNumber);
        setFunds(funds);
        setId(id);
        // ID, ACCOUNT_NUMBER, BANK_ID, CLIENT_ID, FUNDS
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public BigDecimal getFunds(){
        return funds;
    }

    public void setFunds(BigDecimal funds) {
        this.funds = Validation.validateAccountFunds(funds) ? funds : this.funds;
    }

    public void setFunds(String funds) {
        BigDecimal bd = new BigDecimal(funds);
        setFunds(bd);
    }
    public String getAccountNumber(){
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber){
        this.accountNumber = accountNumber;
    }

    public void setRandomAccountNumber(){
        setAccountNumber(new AccountNumberGenerator().generate());
    }

    public <T extends Number> void addFunds(T transferSum) {
        BigDecimal currentTransfer = new BigDecimal(transferSum.toString());
        try {
            if (currentTransfer.compareTo(BigDecimal.ZERO) > 0)
                setFunds(getFunds().add(currentTransfer));
            else
                throw new AccountFundsValidationException("Amount of the transferred money should be more than 0");
        } catch (Exception e) {
            e.printStackTrace();
            Logger.write(e.getMessage(), Logger.Status.ERROR);
        }
    }   
    
    public <T extends Number> void withdrawFunds(T transferSum) {
        BigDecimal currentTransfer = new BigDecimal(transferSum.toString());
        try {
            if (currentTransfer.compareTo(getFunds()) < 0)
                setFunds(getFunds().subtract(currentTransfer));
            else
                throw new AccountFundsValidationException("Amount of the withdrawn money should be more than 0");
        } catch (Exception e) {
            e.printStackTrace();
            Logger.write(e.getMessage(), Logger.Status.ERROR);
        }
        
    }
 
    @Override
    public String toString() {
        return  getAccountNumber();
    }
}