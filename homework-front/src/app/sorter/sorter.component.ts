import { Component, OnInit } from '@angular/core';
import { SorterHttpService } from '../services/sorter-http.service';
import { FormGroup, FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';
import { NumbersContainer } from '../model/numbers-container';
import { AngularWaitBarrier } from 'blocking-proxy/built/lib/angular_wait_barrier';

@Component({
  selector: 'app-sorter',
  templateUrl: './sorter.component.html',
  styleUrls: ['./sorter.component.css']
})
export class SorterComponent implements OnInit {

  private numbersForm: FormGroup;
  private numbersToSort: NumbersContainer;
  private sortedNumbers: NumbersContainer;
  private isErrorMessageVisible: boolean;
  private errorMessage: string;

  constructor(
    private fb: FormBuilder,
    private sorterHttpService: SorterHttpService
  ) { }

  ngOnInit() {
    this.isErrorMessageVisible = false;
    this.initialForm();
  }

  private initialForm() {
    this.numbersForm = this.fb.group({
      numbers: [''],
      order: ['ASC']
    });
  }

  private submit(): void {
    this.numbersToSort = {
      numbers: this.parseToArrayNumbers(this.numbersForm.get('numbers').value),
      order: this.numbersForm.get('order').value
    };

    this.sortNumbers();
    this.reset();
  }

  private reset(): void {
    this.numbersForm.get('numbers').setValue('');
    this.numbersForm.get('order').setValue('ASC');
  }

  private parseToArrayNumbers(numbersFromForm: string): Array<number> {
    if (!numbersFromForm) {
      this.viewError('Please enter correct numbers.');
      return new Array<number>();
    }

    const numbersInStr: Array<string> = numbersFromForm
      .split(' ')
      .map(n => n.trim());

    if (numbersInStr.find(n => this.isNotNumeric(n))) {
      this.viewError('Please enter correct numbers.');
      return new Array<number>();
    }

    return numbersInStr
      .map(n => Number.parseInt(n, 10))
      .filter(n => n);
  }

  private isNotNumeric(num: string): boolean {
    for (let i = 0; i < num.length; i++) {
      if (!num.charAt(i).match('[0-9]')) {
        return true;
      }
    }
    return false;
  }

  private sortNumbers(): void {
    this.sorterHttpService
      .postNumbersToSort(this.numbersToSort)
      .subscribe(
        result => {
          this.sortedNumbers = result;
        },
        error => {
          this.viewError(error);
        }
      );
  }

  private viewError(message: string): void {
    this.errorMessage = message;
    this.isErrorMessageVisible = true;
  }

  private hideError(): void {
    this.errorMessage = '';
    this.isErrorMessageVisible = false;
  }
}
