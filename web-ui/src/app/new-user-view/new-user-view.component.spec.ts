import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewUserViewComponent } from './new-user-view.component';

describe('NewUserViewComponent', () => {
  let component: NewUserViewComponent;
  let fixture: ComponentFixture<NewUserViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewUserViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewUserViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
