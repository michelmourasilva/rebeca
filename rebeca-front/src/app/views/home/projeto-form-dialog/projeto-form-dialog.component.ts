import { Component, OnInit } from '@angular/core';
import {MatDialogRef} from '@angular/material/dialog';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ProjetoService} from '../../../shared/service/projeto.service';
import {MAT_DIALOG_DATA} from '@angular/material/dialog';
import { Inject } from '@angular/core';

@Component({
  selector: 'app-projeto-form-dialog',
  templateUrl: './projeto-form-dialog.component.html',
  styleUrls: ['./projeto-form-dialog.component.css']
})
export class ProjetoFormDialogComponent implements OnInit {

  public projetoForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private rest: ProjetoService,
    public dialogRef: MatDialogRef<ProjetoFormDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) { }

  ngOnInit(): void {

    this.projetoForm = this.fb.group({
      noProjeto: [null, [Validators.required]],
      dsProjeto: [null, [Validators.required, Validators.maxLength(4000)]],
      txtURL: [''],
      txtCaminho: ['']
    });
    this.carregardados();
  }

  // tslint:disable-next-line:typedef
  carregardados(){
    if (this.data.tipo === 'update'){
      this.rest.getProjeto(this.data.idProjeto).subscribe(res => {
        console.log('entrou na atualizaćão');
        console.log(res);

        this.projetoForm.patchValue({
          noProjeto: [res.noProjeto],
          dsProjeto: [res.dsProjeto],
          txtURL: [res.txtURL],
          txtCaminho: [res.txtCaminho]
        });
      });
    }
  }

  // tslint:disable-next-line:typedef
  createProjeto(){
   if (this.projetoForm.valid) {
    this.rest.postProjetos(this.projetoForm.value).subscribe(result => {});
    this.dialogRef.close();
    this.projetoForm.reset();
    window.location.reload();
   }
  }

  // tslint:disable-next-line:typedef
  updateProjeto(){
    if (this.projetoForm.valid) {
      this.rest.updateProjeto(this.data.idProjeto, this.projetoForm.value).subscribe(result => {});
      this.dialogRef.close();
      this.projetoForm.reset();
      window.location.reload();
    }
  }

  cancelar(): void {
    this.dialogRef.close();
    this.projetoForm.reset();
  }

}
