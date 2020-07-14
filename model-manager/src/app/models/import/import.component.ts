import { Component, ElementRef, ViewChild } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-model-import',
  templateUrl: './import.component.html',
  styleUrls: ['./import.component.less']
})
export class ModelImportComponent {
  @ViewChild('input', { static: true }) fileInput: ElementRef;

  file: File = null;

  constructor(private ref: MatDialogRef<ModelImportComponent>) { }

  close() {
    this.ref.close();
  }

  import() {
    this.ref.close(this.file);
  }

  triggerSelectFile() {
    this.fileInput.nativeElement.click();
  }

  fileWasSelected(ev: Event) {
    this.file = (ev.currentTarget as HTMLInputElement).files[0];
  }

}
