import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PrimeiroCompComponent } from './primeiro-comp.component';

describe('PrimeiroCompComponent', () => {
  let component: PrimeiroCompComponent;
  let fixture: ComponentFixture<PrimeiroCompComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PrimeiroCompComponent]
    });
    fixture = TestBed.createComponent(PrimeiroCompComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
