import { Component, OnInit } from '@angular/core';
import { SorterHttpService } from '../services/sorter-http.service';

@Component({
  selector: 'app-sorter',
  templateUrl: './sorter.component.html',
  styleUrls: ['./sorter.component.css']
})
export class SorterComponent implements OnInit {

  constructor(private sorterHttpService: SorterHttpService) { }

  ngOnInit() {
  }


}
