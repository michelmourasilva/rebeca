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
      txtCaminho: [null]
    });
    this.carregarDados();
  }

  // tslint:disable-next-line:typedef
  carregarDados(){
    if (this.data.tipo === 'update' || this.data.tipo === 'view'){
      this.rest.getProjeto(this.data.idProjeto).subscribe(res => {
        console.log('entrou na atualizaćão');
        console.log(res);

        this.projetoForm.patchValue({
          noProjeto: res.noProjeto,
          dsProjeto: res.dsProjeto,
          txtURL: res.txtURL,
          txtCaminho: ''
        });
      });
    }
  }

  // tslint:disable-next-line:typedef
  mantemProjeto(){
   if (this.projetoForm.valid) {
     if (this.data.tipo === 'update'){
       console.log(this.projetoForm.value);
       this.rest.updateProjeto(this.data.idProjeto, this.projetoForm.value);
       console.log('Atualizando projeto');
     } else  if (this.data.tipo === 'new'){
      this.rest.postProjeto(this.projetoForm.value);
      console.log('Cadastrando projeto');
     }
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
