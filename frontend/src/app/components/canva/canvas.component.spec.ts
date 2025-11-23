import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Canvas } from './canvas.component';

describe('Canva', () => {
  let component: Canvas;
  let fixture: ComponentFixture<Canvas>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Canvas]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Canvas);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
