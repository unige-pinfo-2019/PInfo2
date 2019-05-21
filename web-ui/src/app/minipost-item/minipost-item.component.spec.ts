import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MinipostItemComponent } from './minipost-item.component';

describe('MinipostItemComponent', () => {
  let component: MinipostItemComponent;
  let fixture: ComponentFixture<MinipostItemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MinipostItemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MinipostItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
