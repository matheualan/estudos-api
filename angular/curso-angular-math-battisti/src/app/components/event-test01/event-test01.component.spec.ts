import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EventTest01Component } from './event-test01.component';

describe('EventTest01Component', () => {
  let component: EventTest01Component;
  let fixture: ComponentFixture<EventTest01Component>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EventTest01Component]
    });
    fixture = TestBed.createComponent(EventTest01Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
