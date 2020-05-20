import { Component } from '@angular/core';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-model-import',
  templateUrl: './model-import.component.html',
  styleUrls: ['./model-import.component.less']
})
export class ModelImportComponent {
  file = new FormControl('');

    importFile(): void {
      if(this.file.value == '') {
        console.log("Aucun fichier")
      } else {
        console.log(this.file.value)
      }
    }
}
