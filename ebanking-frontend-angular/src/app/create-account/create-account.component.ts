import { Component, OnInit } from '@angular/core';
import { AccountsService } from '../services/accounts.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { error } from 'console';

@Component({
  selector: 'app-create-account',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './create-account.component.html',
  styleUrl: './create-account.component.css',
})
export class CreateAccountComponent implements OnInit {
  balance: number = 0;
  accountType: string = '';
  customerId: string | null = null;
  overDraft: number = 0;
  interestRate: number = 0;

  constructor(
    private accountsService: AccountsService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe((params) => {
      this.customerId = params.get('customerId');
    });
  }

  createAccount() {
    if (this.accountType === 'CURRENT') {
      this.accountsService
        .addCurrentAccount(this.balance, this.overDraft, this.customerId)
        .subscribe(
          (res) => this.router.navigateByUrl('/accounts'),
          (error) => alert(error.error.message)
        );
    } else {
      this.accountsService
        .addSavingAccount(this.balance, this.interestRate, this.customerId)
        .subscribe(
          (res) => this.router.navigateByUrl('/accounts'),
          (error) => alert(error.error.message)
        );
    }
  }
}
