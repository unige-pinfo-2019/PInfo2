import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PasswordRecoveryViewComponent } from './password-recovery-view.component';

describe('PasswordRecoveryViewComponent', () => {
  let component: PasswordRecoveryViewComponent;
  let fixture: ComponentFixture<PasswordRecoveryViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PasswordRecoveryViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PasswordRecoveryViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
