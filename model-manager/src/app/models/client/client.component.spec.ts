import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ModelClientComponent } from './client.component';

describe('ModelClientComponent', () => {
  let component: ModelClientComponent;
  let fixture: ComponentFixture<ModelClientComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ModelClientComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ModelClientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
