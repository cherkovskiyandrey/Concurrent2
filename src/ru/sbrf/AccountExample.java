package ru.sbrf;

public class AccountExample implements Runnable {
    private Account acct = new Account();

    private synchronized void makeWithdrawal(int amt) {
        if (acct.getBalance() >= amt) {
            Thread.yield();
            acct.withdraw(amt);
        }
    }

    @Override
    public void run() {
        for (int x = 0; x < 500; x++) {
            makeWithdrawal(1);
            if (acct.getBalance() < 0) {
                System.out.println("account is overdrawn!");
            }
        }
        System.out.println(acct.getBalance());
    }
}
