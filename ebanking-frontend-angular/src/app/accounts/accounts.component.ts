import { Component, OnInit } from '@angular/core';
import { BankAccount } from '../models/bank-account';
import { AccountsService } from '../services/accounts.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-accounts',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './accounts.component.html',
  styleUrl: './accounts.component.css',
})
export class AccountsComponent implements OnInit {
  accounts!: BankAccount[];
  keyword: string = '';
  errMessage!: string;
  customerId: string | null = null;

  constructor(
    private accountsService: AccountsService,
    private router: Router,
    private route: ActivatedRoute
  ) {}
  ngOnInit(): void {
    this.route.paramMap.subscribe((params) => {
      this.customerId = params.get('customerId');
      if (this.customerId) {
        this.getAccountsByCustomerId(this.customerId);
      } else {
        this.getAllAccounts();
      }
    });
  }

  getAllAccounts(): void {
    this.accountsService.getAccounts().subscribe(
      (res: any) => {
        this.accounts = res;
      },
      (error) => {
        this.errMessage = error.message;
      }
    );
  }
  getAccountsByCustomerId(customerId: string): void {
    this.accountsService.getAccountsByCustomerId(customerId).subscribe(
      (res: any) => {
        this.accounts = res;
      },
      (error) => {
        this.errMessage = error.message;
      }
    );
  }
  createNewAccount() {
    this.router.navigateByUrl('/accounts/create/' + this.customerId);
  }
  viewAccount(accountId: string) {
    this.router.navigateByUrl('/accounts/' + accountId);
  }
  deleteAccount(id: string) {
    this.accountsService.deleteAccount(id).subscribe(() => {
      const index = this.accounts.findIndex((account) => account.id === id);
      if (index !== -1) {
        this.accounts.splice(index, 1);
      }
    });
  }
}
