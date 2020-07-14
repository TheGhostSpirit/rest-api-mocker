import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ModelImportComponent } from './import.component';

describe('ImportComponent', () => {
  let component: ModelImportComponent;
  let fixture: ComponentFixture<ModelImportComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ModelImportComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ModelImportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
