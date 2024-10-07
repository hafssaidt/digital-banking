import { BankAccount } from './bank-account';

export interface SavingAccount extends BankAccount {
  interestRate: number;
}
