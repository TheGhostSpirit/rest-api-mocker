import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Model } from 'model-share/types/model';
import { of } from 'rxjs';
import { tap, switchMap, catchError } from 'rxjs/operators';

import { ModelsService } from '../../core/services/models/models.service';
import { ConvertService } from '../../core/services/convert/convert.service';

@Component({
  selector: 'app-model-editor',
  templateUrl: './editor.component.html',
  styleUrls: ['./editor.component.less']
})
export class ModelEditorComponent implements OnInit {

  model: Model;
  yamlDocument: string;

  constructor(
    private route: ActivatedRoute,
    private service: ModelsService,
    private convert: ConvertService,
    private snackbar: MatSnackBar
  ) { }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    this.service.findOne(id).pipe(
      tap(model => this.model = model),
      switchMap(model => this.convert.jsonToYaml(model))
    )
    .subscribe(yaml => this.yamlDocument = yaml);
  }

  save() {
    this.convert.yamlToJson(this.yamlDocument).pipe(
      switchMap(model => this.service.update(this.model._id, model)),
      catchError(err => {
        this.snackbar.open(`Unable to save: ${err.error.message}`, 'Close', {
          duration: 10000
        });
        return of({});
      })
    ).subscribe();
  }

}
