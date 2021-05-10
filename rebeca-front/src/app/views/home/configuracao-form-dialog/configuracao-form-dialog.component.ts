import {ChangeDetectorRef, Component, Inject, OnInit, ViewEncapsulation} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {MAT_DIALOG_DATA} from '@angular/material/dialog';
import {ProjetoService} from '../../../shared/service/projeto.service';
import {ColecaoObjetoService} from '../../../shared/service/colecaoObjeto.service';
import {ConfiguracaoService} from '../../../shared/service/configuracao.service';

@Component({
  selector: 'app-configuracao-form-dialog',
  templateUrl: './configuracao-form-dialog.component.html',
  styleUrls: ['./configuracao-form-dialog.component.css']
})
export class ConfiguracaoFormDialogComponent implements OnInit {
  isLinear = false;
  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  panelOpenState = false;
  colecaoObjetoLista: any = [];
  configuracoesLista: any = [];

  constructor(
    public colecaoObjetoService: ColecaoObjetoService,
    public configuracaoService: ConfiguracaoService,
    private fb: FormBuilder,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private rest: ConfiguracaoService,
    private cd: ChangeDetectorRef
  ) { }


  ngOnInit(): void {

    this.firstFormGroup = this.fb.group({
      noProprietarioBanco: new FormControl('REBECA'),
      noModulo: new FormControl(null, [Validators.required]),
      dsModulo: new FormControl(null, [Validators.required]),
      noObjetoBanco: new FormControl(null, [Validators.required]),
      idProjeto: new FormControl(this.data.idProjeto)
    });
    this.secondFormGroup = this.fb.group({
      secondCtrl: ['', Validators.required]
    });
    this.getColecaoObjeto();
    this.getConfiguracoesbyProjeto(this.data.idProjeto);
  }

  // tslint:disable-next-line:typedef
  getColecaoObjeto(){
    this.colecaoObjetoService.getColecaoObjeto().subscribe(
      data => {
        // console.log(typeof data);
        // console.log(typeof this.projetoLista.length);
        // console.log(this.projetoLista.length);
        this.colecaoObjetoLista = data;
      }
    );
  }

  // tslint:disable-next-line:typedef
  getConfiguracoesbyProjeto(idProjeto: number){
    this.configuracaoService.getConfiguracaobyProjeto(idProjeto).subscribe(
      data => {
          this.configuracoesLista = data;
      }
    );
  }
  // tslint:disable-next-line:typedef
  mantemConfiguracao(){
    console.log(this.firstFormGroup);
    if (this.firstFormGroup.valid) {
        this.rest.postConfiguracao(this.firstFormGroup.value);

        this.configuracoesLista.push(
        {
          'noModulo': this.firstFormGroup.value.noModulo,
          'dsModulo':  this.firstFormGroup.value.dsModulo,
          'noObjetoBanco':  this.firstFormGroup.value.noObjetoBanco,
        }
      );
        this.configuracoesLista.sort();
        console.log('Cadastrando projeto');
      // this.dialogRef.close();
        this.firstFormGroup.reset();
        this.secondFormGroup.reset();
        this.cd.detectChanges();
    }
  }


}
