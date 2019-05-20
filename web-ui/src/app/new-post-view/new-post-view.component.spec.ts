import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewPostViewComponent } from './new-post-view.component';

describe('NewPostViewComponent', () => {
  let component: NewPostViewComponent;
  let fixture: ComponentFixture<NewPostViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewPostViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewPostViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
