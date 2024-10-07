import { AccountStatus } from './account-status';
import { Customer } from './customer';
import { Operation } from './operation';

export interface BankAccount {
  id: string;
  createdAt: Date | null;
  balance: number;
  status?: AccountStatus;
  currency: string;
  accountType: string;
  customer?: Customer;
  operations: Operation[];
}
