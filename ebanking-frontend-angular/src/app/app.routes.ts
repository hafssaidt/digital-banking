import { Routes } from '@angular/router';
import { CustomersComponent } from './customers/customers.component';
import { AccountsComponent } from './accounts/accounts.component';
import { CreateCustomerComponent } from './create-customer/create-customer.component';
import { CreateAccountComponent } from './create-account/create-account.component';
import { ViewAccountComponent } from './view-account/view-account.component';
import { CreateOperationComponent } from './create-operation/create-operation.component';

export const routes: Routes = [
  { path: 'customers', component: CustomersComponent },
  { path: 'accounts', component: AccountsComponent },
  { path: 'customers/create', component: CreateCustomerComponent },
  { path: 'accounts', component: AccountsComponent },
  { path: 'accounts/:accountId', component: ViewAccountComponent },
  { path: 'accounts/customer/:customerId', component: AccountsComponent },
  { path: 'accounts/create/:customerId', component: CreateAccountComponent },
  {
    path: 'accounts/:accountId/create-operation',
    component: CreateOperationComponent,
  },
];
