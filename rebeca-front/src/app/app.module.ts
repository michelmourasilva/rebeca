import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HomeComponent } from './views/home/home.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import { ProjectListComponent } from './views/home/project-list/project-list.component';
import {MatCardModule} from '@angular/material/card';
import {HttpClientModule} from '@angular/common/http';
import {FlexLayoutModule} from '@angular/flex-layout';
import {MatDialogModule} from '@angular/material/dialog';
import {MatChipsModule} from '@angular/material/chips';
import { ProjetoFormDialogComponent } from './views/home/projeto-form-dialog/projeto-form-dialog.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import { ConfiguracaoFormDialogComponent } from './views/home/configuracao-form-dialog/configuracao-form-dialog.component';
import {MatListModule} from '@angular/material/list';
import {MatGridListModule} from '@angular/material/grid-list';
import { MatSelectModule } from '@angular/material/select';
import { MensagemDialogComponent } from './views/home/mensagem-dialog/mensagem-dialog.component';
import {MatStepperModule} from '@angular/material/stepper';
import {MatExpansionModule} from '@angular/material/expansion';
import { MatSliderModule } from '@angular/material/slider';
import {MatPaginatorModule} from '@angular/material/paginator';
import { EndpointDialogComponent } from './views/home/endpoint-dialog/endpoint-dialog.component';
import {MatDividerModule} from '@angular/material/divider';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import {ClipboardModule} from '@angular/cdk/clipboard';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ProjectListComponent,
    ProjetoFormDialogComponent,
    ConfiguracaoFormDialogComponent,
    MensagemDialogComponent,
    EndpointDialogComponent
  ],
    imports: [
        HttpClientModule,
        BrowserModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        MatToolbarModule,
        MatIconModule,
        MatButtonModule,
        MatCardModule,
        FlexLayoutModule,
      MatDialogModule,
      MatChipsModule,
      FormsModule,
      ReactiveFormsModule,
      MatFormFieldModule,
      MatInputModule,
      MatListModule,
      MatGridListModule,
      MatSelectModule,
      MatStepperModule,
      MatExpansionModule,
      MatSliderModule,
      MatPaginatorModule,
      MatDividerModule,
      MatSlideToggleModule,
      ClipboardModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
