import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { HomeComponent } from './home/home.component';
import { SorterComponent } from './sorter/sorter.component';
import { SorterHttpService } from './services/sorter-http.service';

@NgModule({
  declarations: [
    HomeComponent,
    SorterComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [SorterHttpService],
  bootstrap: [HomeComponent]
})
export class AppModule { }
