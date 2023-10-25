import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SegundoCompComponent } from './segundo-comp.component';

describe('SegundoCompComponent', () => {
  let component: SegundoCompComponent;
  let fixture: ComponentFixture<SegundoCompComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SegundoCompComponent]
    });
    fixture = TestBed.createComponent(SegundoCompComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
