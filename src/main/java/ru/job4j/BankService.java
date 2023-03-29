package ru.job4j;

import ru.job4j.bank.Account;
import ru.job4j.bank.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс описывает добавление, удаление в базу клиентов банка, добавление счета банка,
 * поиск клиента по паспорту, поиск счета ао реквизитам, а так же перевод денег между
 * счетами.
 * @author Vasya Pupkin
 * @version 1.0
 */
public class BankService {
    /**
     * Хранение клиентов банка и их счетов осуществляется в коллекции типа HashMap.
     */
    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     * Метод принимает на вход клиента банка и добавляет нового клиента в базу с одновременным
     * созданием пустого списка его счетов.
     * @param user пользователь, который добавляется в базу клиентов банка.
     */
    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Метод удаляет пользователя из клиентской базы банка.
     * @param passport номер паспорта по которому осуществляется удаление пользователя из
     *                 клиентской базы банка.
     * @return возвращает true при успешном удалении пользователя из клиентской базы банка и false в
     * противном случае.
     */
    public boolean deleteUser(String passport) {
        return users.remove(new User(passport, "")) != null;
    }

    /**
     * Метод добавляет банковский счет для существующего клиента банка.
     * @param passport номер паспорта клиента банка, которому добавляется счет.
     * @param account добавляемый банковский счет.
     */
    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> accounts = getAccounts(user);
            if (!accounts.contains(account)) {
                accounts.add(account);
            }
        }

    }

    /**
     * Метод возвращает клиента банка по номеру паспорта.
     * @param passport номер паспорта клиента банка.
     * @return возвращает клиента банка или null если клиента с таким номером паспорта
     * не существует.
     */
    public User findByPassport(String passport) {
        return users.keySet()
                .stream()
                .filter(s -> s.getPassport().equals(passport))
                .findFirst()
                .orElse(null);
    }

    /**
     * Метод ищет банковский счет по реквизитам.
     * @param passport номер паспорта клиента банка.
     * @param requisite реквизиты счета банка.
     * @return возвращает банкаовский счет или null если такой счет отстутствует.
     */
    public Account findByRequisite(String passport, String requisite) {
        User user = findByPassport(passport);
        if (user != null) {
            return getAccounts(user)
                    .stream()
                    .filter(acc -> acc.getRequisite().equals(requisite))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    /**
     * Метод переводит деньги с одного счета на другой.
     * @param srcPassport номер паспорта клиента банка со счета которого будут переодится денежные
     *                    средства.
     * @param srcRequisite номер счета клиента с которого будут переводится денежные средства.
     * @param destPassport номер паспорта клиента банка на счет которого будут переодится денежные
     *      *                    средства.
     * @param destRequisite номер счета клиента на который будут переводится денежные средства.
     * @param amount величина переводимых средств.
     * @return возвращает true при успешном переводе средств и false в противном случае.
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        boolean rsl = false;
        Account srcAccount = findByRequisite(srcPassport, srcRequisite);
        Account destAccount = findByRequisite(destPassport, destRequisite);
        if (srcAccount != null && destAccount != null && srcAccount.getBalance() >= amount) {
            srcAccount.setBalance(srcAccount.getBalance() - amount);
            destAccount.setBalance(destAccount.getBalance() + amount);
            rsl = true;
        }
        return rsl;
    }

    /**
     * Метод возвращает список счетов пользователся в банке.
     * @param user пользователь банка для которого запрашивается список банковских счетов.
     * @return возвращает список банковских счетов пользователя в виде ArrayList.
     */
    public List<Account> getAccounts(User user) {
        return users.get(user);
    }
}