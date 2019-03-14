import { Injectable } from '@angular/core';
import { NumbersContainer } from '../model/numbers-container';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SorterHttpService {

  readonly URL_HOMEWORK_API = 'http://localhost:8080//numbers/sort-command';

  constructor(private http: HttpClient) { }

  postNumbersToSort(numbersToSort: NumbersContainer): Observable<any> {
    return this.http.post(this.URL_HOMEWORK_API, numbersToSort);
  }
}
