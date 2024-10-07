import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class AccountsService {
  constructor(private http: HttpClient) {}
  url = environment.URL + '/accounts';

  getAccounts() {
    return this.http.get(this.url);
  }
  getAccountById(accountId: any) {
    return this.http.get(`${this.url}/${accountId}`);
  }
  getAccountsByCustomerId(customerId: string) {
    return this.http.get(`${this.url}/customer/${customerId}`);
  }
  addCurrentAccount(balance: number, overDraft: number, customerId: any) {
    const params = new HttpParams()
      .set('initialBalance', balance)
      .set('overDraft', overDraft.toString())
      .set('customerId', customerId);
    return this.http.post(`${this.url}/current`, null, { params });
  }

  addSavingAccount(balance: number, interestRate: number, customerId: any) {
    const params = new HttpParams()
      .set('initialBalance', balance)
      .set('interestRate', interestRate.toString())
      .set('customerId', customerId);
    return this.http.post(`${this.url}/saving`, null, { params });
  }
  deleteAccount(id: String) {
    return this.http.delete(`${this.url}/${id}`);
  }
  addCreditOperation(accountId: any, amount: number, description: string) {
    const params = new HttpParams()
      .set('amount', amount)
      .set('description', description.toString());
    return this.http.patch(`${this.url}/${accountId}/credit`, null, { params });
  }
  addDebitOperation(accountId: any, amount: number, description: string) {
    const params = new HttpParams()
      .set('amount', amount)
      .set('description', description.toString());
    return this.http.patch(`${this.url}/${accountId}/debit`, null, { params });
  }
  addTransferOperation(accountId: any, toAccount: string, amount: number) {
    const params = new HttpParams()
      .set('amount', amount)
      .set('fromAccountId', accountId)
      .set('toAccountId', toAccount);
    return this.http.patch(`${this.url}/transfer`, null, {
      params,
    });
  }
}
