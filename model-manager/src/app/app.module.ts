import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

// import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ReactiveFormsModule } from '@angular/forms';
import { ModelImportComponent } from './model-import/model-import.component';

@NgModule({
  declarations: [
    AppComponent,
    ModelImportComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule
    // AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
