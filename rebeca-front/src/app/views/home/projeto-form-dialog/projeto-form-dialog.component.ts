import { Component, OnInit } from '@angular/core';
import {MatDialog, MatDialogRef} from '@angular/material/dialog';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ProjetoService} from '../../../shared/service/projeto.service';
import {MAT_DIALOG_DATA} from '@angular/material/dialog';
import { Inject } from '@angular/core';
import {MensagemDialogComponent} from '../mensagem-dialog/mensagem-dialog.component';

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
    @Inject(MAT_DIALOG_DATA) public data: any,
    public dialog: MatDialog
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
        // console.log('entrou na atualização');
        // console.log(res);

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

     const objProjeto = this.projetoForm.value;
     const objConstProjeto = objProjeto.idProjeto;
     const noProjeto = objProjeto.noProjeto;
     let tipo = 'cadastrar_projeto_sucesso';

     if (this.data.tipo === 'update'){
       tipo = 'alterar_projeto_sucesso';
       this.rest.updateProjeto(this.data.idProjeto, objProjeto);
       // console.log('Atualizando projeto');
     } else if (this.data.tipo === 'new'){
       tipo = 'cadastrar_projeto_sucesso';
       this.rest.postProjeto(objProjeto);
       // console.log(this.projetoForm.value, 'Cadastrando projeto');
     }

     const dialogRef = this.dialog.open(MensagemDialogComponent, {
       panelClass: 'popup',
       minWidth: '200px',
       minHeight: '200px',
       data: {objConstProjeto, noProjeto, tipo}
     });

     // this.dialogRef.close();
     // this.projetoForm.reset();
     // window.location.reload();
   }
  }
  cancelar(): void {
    this.dialogRef.close();
    this.projetoForm.reset();
  }

}
