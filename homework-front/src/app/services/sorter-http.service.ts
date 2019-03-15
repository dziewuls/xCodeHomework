import { Injectable } from '@angular/core';
import { NumbersContainer } from '../model/numbers-container';
import { Observable, throwError } from 'rxjs';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class SorterHttpService {

  readonly URL_HOMEWORK_API = 'http://localhost:8080/numbers/sort-command';

  constructor(private http: HttpClient) { }

  postNumbersToSort(numbersToSort: NumbersContainer): Observable<any> {
    return this.http
      .post(this.URL_HOMEWORK_API, numbersToSort)
      .pipe(catchError(this.handleError));
  }

  private handleError(error: HttpErrorResponse) {
    return throwError(
      'Something go wrong. Try again.');
  }
}
