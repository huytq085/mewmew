import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddfriendButtonComponent } from './addfriend-button.component';

describe('AddfriendButtonComponent', () => {
  let component: AddfriendButtonComponent;
  let fixture: ComponentFixture<AddfriendButtonComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddfriendButtonComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddfriendButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
