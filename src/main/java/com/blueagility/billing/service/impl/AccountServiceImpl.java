package com.blueagility.billing.service.impl;

import com.blueagility.billing.dao.AccountDao;
import com.blueagility.billing.exception.ValidationException;
import com.blueagility.billing.service.AccountService;
import com.blueagility.billing.entity.Account;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * Responsible for managing accounts
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountDao accountDao;

    /**
     * Create a new account
     * @param account The Account to create
     * @return
     */
    public Account createAccount(Account account) throws ValidationException {
        account.validate();

        if(accountDao.findAccountByPhoneNumber(account.getPhoneNumber()) != null) {
            throw new ValidationException("Account already exists for phone number: " + account.getPhoneNumber());
        }

        return accountDao.save(account);
    }

    /**
     * Update an existing account
     * @param account The account to update
     * @return
     * @throws ValidationException
     */
    public Account updateAccount(Account account) throws ValidationException {
        account.validate();

        if(accountDao.findAccountByPhoneNumber(account.getPhoneNumber()) == null) {
            throw new ValidationException("Account does not exist for phone number: " + account.getPhoneNumber());
        }

        return accountDao.save(account);
    }

    /**
     * Answers all the accounts in the system.
     * @return
     */
    public Collection<Account> findAll() {
        return accountDao.findAll();
    }

    /**
     * Returns an account for the specified phone number
     *
     * @param phoneNumber
     * @return Account
     */
    public Account findAccountByPhoneNumber(String phoneNumber) {
        return accountDao.findAccountByPhoneNumber(phoneNumber);
    }

    /**
     * Deletes all accounts in the system.
     */
    public void deleteAll() {
        accountDao.deleteAll();
    }
}
