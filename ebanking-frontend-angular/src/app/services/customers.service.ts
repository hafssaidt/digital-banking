import { Customer } from './../models/customer';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class CustomersService {
  constructor(private http: HttpClient) {}
  url = environment.URL + '/customers';

  getCustomers() {
    return this.http.get(this.url);
  }
  searchCustomers(keyword: any) {
    const params = new HttpParams().set('keyword', keyword);
    return this.http.get(`${this.url}/search`, { params });
  }
  addCustomer(customer: Customer) {
    return this.http.post(this.url, customer);
  }
  deleteCustomer(id: String) {
    return this.http.delete(`${this.url}/${id}`);
  }
}
