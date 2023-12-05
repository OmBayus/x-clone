import { ComponentFixture, TestBed } from '@angular/core/testing';

import { XbuttonComponent } from './xbutton.component';

describe('XbuttonComponent', () => {
  let component: XbuttonComponent;
  let fixture: ComponentFixture<XbuttonComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [XbuttonComponent]
    });
    fixture = TestBed.createComponent(XbuttonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
