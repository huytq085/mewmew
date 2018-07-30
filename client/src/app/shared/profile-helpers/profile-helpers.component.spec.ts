import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfileHelpersComponent } from './profile-helpers.component';

describe('ProfileHelpersComponent', () => {
  let component: ProfileHelpersComponent;
  let fixture: ComponentFixture<ProfileHelpersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProfileHelpersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfileHelpersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
