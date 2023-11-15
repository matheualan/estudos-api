import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EventTest02Component } from './event-test02.component';

describe('EventTest02Component', () => {
  let component: EventTest02Component;
  let fixture: ComponentFixture<EventTest02Component>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EventTest02Component]
    });
    fixture = TestBed.createComponent(EventTest02Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
