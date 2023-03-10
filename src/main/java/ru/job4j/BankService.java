package ru.job4j;

import ru.job4j.bank.Account;
import ru.job4j.bank.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankService {
    private final Map<User, List<Account>> users = new HashMap<>();

    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    public boolean deleteUser(String passport) {
        return users.keySet().remove(findByPassport(passport));
    }

    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        List<Account> accounts = getAccounts(user);
        if (!accounts.contains(account)) {
            accounts.add(account);
        }
    }

    public User findByPassport(String passport) {
        User user = null;
        for (User us : users.keySet()) {
            if (us.getPassport().equals(passport)) {
                user = us;
                break;
            }
        }
        return user;
    }

    public Account findByRequisite(String passport, String requisite) {
        Account account = null;
        User user = findByPassport(passport);
        if (user != null) {
            for (Account acc : getAccounts(user)) {
                if (acc.getRequisite().equals(requisite)) {
                    account = acc;
                    break;
                }
            }
        }
        return account;
    }

    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        boolean rsl = false;
        if (findByRequisite(srcPassport, srcRequisite) != null
                && findByRequisite(destPassport, destRequisite) != null
                && findByRequisite(srcPassport, srcRequisite).getBalance() >= amount) {
            double sourceBalance = findByRequisite(srcPassport, srcRequisite).getBalance();
            double destBalance = findByRequisite(destPassport, destRequisite).getBalance();
            findByRequisite(srcPassport, srcRequisite).setBalance(sourceBalance - amount);
            findByRequisite(destPassport, destRequisite).setBalance(destBalance + amount);
            rsl = true;
        }
        return rsl;
    }

    public List<Account> getAccounts(User user) {
        return users.get(user);
    }
}