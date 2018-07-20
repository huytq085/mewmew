import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfileRoutingComponent } from './profile-routing.component';

describe('ProfileRoutingComponent', () => {
  let component: ProfileRoutingComponent;
  let fixture: ComponentFixture<ProfileRoutingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProfileRoutingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfileRoutingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
