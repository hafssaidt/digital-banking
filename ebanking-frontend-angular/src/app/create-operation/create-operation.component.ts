import { Component, OnInit } from '@angular/core';
import { AccountsService } from '../services/accounts.service';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { error } from 'console';

@Component({
  selector: 'app-create-operation',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './create-operation.component.html',
  styleUrls: ['./create-operation.component.css'],
})
export class CreateOperationComponent implements OnInit {
  accountId: string | null = '';

  creditAmount: number = 0;
  creditDescription: string = '';

  debitAmount: number = 0;
  debitDescription: string = '';

  toAccountId: string = '';
  transferAmount: number = 0;

  // Track the active tab
  activeTab: string = 'credit';

  constructor(
    private accountsService: AccountsService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe((params) => {
      this.accountId = params.get('accountId');
    });
  }

  // Method to set the active tab
  setActiveTab(tab: string): void {
    this.activeTab = tab;
  }

  // Submit Credit Operation
  onSubmitCredit(form: any) {
    if (form.valid) {
      this.accountsService
        .addCreditOperation(
          this.accountId,
          this.creditAmount,
          this.creditDescription
        )
        .subscribe(
          (response) => {
            console.log('Credit Operation Success:', response);
            form.reset();
          },
          (error) => alert(error.error.message)
        );
    }
  }

  // Submit Debit Operation
  onSubmitDebit(form: any) {
    if (form.valid) {
      this.accountsService
        .addDebitOperation(
          this.accountId,
          this.debitAmount,
          this.debitDescription
        )
        .subscribe(
          (response) => {
            console.log('Debit Operation Success:', response);
            form.reset();
          },
          (error) => alert(error.error.message)
        );
    }
  }

  // Submit Transfer Operation
  onSubmitTransfer(form: any) {
    if (form.valid) {
      this.accountsService
        .addTransferOperation(
          this.accountId,
          this.toAccountId,
          this.transferAmount
        )
        .subscribe(
          (response) => {
            console.log('Transfer Operation Success:', response);
            form.reset();
          },
          (error) => alert(error.message)
        );
    }
  }
}
