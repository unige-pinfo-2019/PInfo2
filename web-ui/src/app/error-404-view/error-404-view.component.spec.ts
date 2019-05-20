import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Error404ViewComponent } from './error-404-view.component';

describe('Error404ViewComponent', () => {
  let component: Error404ViewComponent;
  let fixture: ComponentFixture<Error404ViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Error404ViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Error404ViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
