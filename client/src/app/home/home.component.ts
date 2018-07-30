import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})

export class HomeComponent implements OnInit {

  @ViewChild("tref", {read: ElementRef}) tref: ElementRef;

  constructor() { }

  ngOnInit() {
    console.log(this.tref.nativeElement.outerHTML);

  }

  

  ngAfterViewInit(): void {
    // outputs `I am span`
}

}
