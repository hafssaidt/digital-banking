import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Customer } from '../models/customer';
import { CustomersService } from '../services/customers.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-customer',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './create-customer.component.html',
  styleUrl: './create-customer.component.css',
})
export class CreateCustomerComponent {
  customer: Customer = {
    id: '',
    name: '',
    mail: '',
  };

  constructor(
    private customersService: CustomersService,
    private router: Router
  ) {}

  createCustomer() {
    this.customersService
      .addCustomer(this.customer)
      .subscribe((res) => this.router.navigateByUrl('/customers'));
  }
}
