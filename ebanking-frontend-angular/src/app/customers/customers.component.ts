import { Component, OnInit } from '@angular/core';
import { CustomersService } from '../services/customers.service';
import { Customer } from '../models/customer';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-customers',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './customers.component.html',
  styleUrl: './customers.component.css',
})
export class CustomersComponent implements OnInit {
  customers!: Customer[];
  keyword: string = '';
  errMessage!: string;

  constructor(
    private customersService: CustomersService,
    private router: Router
  ) {}
  ngOnInit(): void {
    this.searchCustomers();
  }

  searchCustomers(): void {
    this.customersService.searchCustomers(this.keyword).subscribe(
      (res: any) => {
        this.customers = res;
      },
      (error) => {
        this.errMessage = error.message;
      }
    );
  }
  createNewCustomer() {
    this.router.navigateByUrl('/customers/create');
  }
  deleteCustomer(id: string) {
    this.customersService.deleteCustomer(id).subscribe(() => {
      const index = this.customers.findIndex((customer) => customer.id === id);
      if (index !== -1) {
        this.customers.splice(index, 1);
      }
    });
  }
  getAccountsByCustomerId(customerId: string) {
    this.router.navigateByUrl(`/accounts/customer/${customerId}`);
  }
}
