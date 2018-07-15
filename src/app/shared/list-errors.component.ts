import { Component, OnInit, Input } from '@angular/core';
import { Errors } from '../core';

@Component({
  selector: 'list-errors',
  templateUrl: './list-errors.component.html',
  styleUrls: ['./list-errors.component.css']
})
export class ListErrorsComponent implements OnInit {

  error: string;

  @Input()
  set errors(error: string) {
    this.error = error;
  };

  get errorList() { return this.error; }


  constructor() { }

  ngOnInit() {
  }

}
