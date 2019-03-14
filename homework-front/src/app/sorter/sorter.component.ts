import { Component, OnInit } from '@angular/core';
import { SorterHttpService } from '../services/sorter-http.service';
import { FormGroup, FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';
import { NumbersContainer } from '../model/numbers-container';

@Component({
  selector: 'app-sorter',
  templateUrl: './sorter.component.html',
  styleUrls: ['./sorter.component.css']
})
export class SorterComponent implements OnInit {

  private numbersForm: FormGroup;
  private numbersToSort: NumbersContainer;
  private sortedNumbers: NumbersContainer;
  private areSortedNumbersVisible: boolean;

  constructor(
    private fb: FormBuilder,
    private sorterHttpService: SorterHttpService
  ) { }

  ngOnInit() {
    this.areSortedNumbersVisible = false;

    this.numbersForm = this.fb.group({
      numbers: [''],
      order: ['']
    });
  }

  private submit(): void {
    this.numbersToSort = {
      numbers: this.parseToArrayNumbers(this.numbersForm.get('numbers').value),
      order: this.numbersForm.get('order').value
    };
    console.log(this.numbersToSort);
    this.sortNumbers();
  }

  private parseToArrayNumbers(numbersInString: string): Array<number> {
    return numbersInString
      .split(',')
      .map(n => Number.parseInt(n.trim(), 10))
      .filter(n => n);
  }

  private sortNumbers(): void {
    this.sorterHttpService
      .postNumbersToSort(this.numbersToSort)
      .subscribe(result => {
        this.sortedNumbers = result;
        this.areSortedNumbersVisible = true;
      });
  }
}
