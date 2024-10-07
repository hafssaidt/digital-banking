import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AccountsService } from '../services/accounts.service';
import { BankAccount } from '../models/bank-account';
import { CommonModule } from '@angular/common';
import { CurrentAccount } from '../models/current-account';
import { SavingAccount } from '../models/saving-account';

@Component({
  selector: 'app-view-account',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './view-account.component.html',
  styleUrls: ['./view-account.component.css'], // Fixed property name from styleUrl to styleUrls
})
export class ViewAccountComponent implements OnInit {
  account: BankAccount | SavingAccount | CurrentAccount | null = null; // Use null for initialization
  accountId: string | null = null;

  constructor(
    private accountsService: AccountsService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe((params) => {
      this.accountId = params.get('accountId');
      this.getAccount();
    });
  }

  getAccount() {
    this.accountsService.getAccountById(this.accountId).subscribe(
      (res: any) => {
        console.log('Response:', res);
        // Ensure the response matches the expected types
        this.account = res; // Assign directly
      },
      (error) => {
        alert(error.message);
      }
    );
  }

  // Type guard to check for SavingAccount
  isSavingAccount(
    account: BankAccount | SavingAccount | CurrentAccount
  ): account is SavingAccount {
    return (account as SavingAccount).interestRate !== undefined;
  }

  // Type guard to check for CurrentAccount
  isCurrentAccount(
    account: BankAccount | SavingAccount | CurrentAccount
  ): account is CurrentAccount {
    return (account as CurrentAccount).overDraft !== undefined;
  }
  AddOperation() {
    this.router.navigateByUrl(
      '/accounts/' + this.accountId + '/create-operation'
    );
  }
}
