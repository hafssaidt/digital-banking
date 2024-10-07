import { BankAccount } from "./bank-account";

export interface CurrentAccount extends BankAccount{
    overDraft: number,
}