import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ComponentTest1Component } from './component-test1.component';

describe('ComponentTest1Component', () => {
  let component: ComponentTest1Component;
  let fixture: ComponentFixture<ComponentTest1Component>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ComponentTest1Component]
    });
    fixture = TestBed.createComponent(ComponentTest1Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
