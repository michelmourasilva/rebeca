import {ChangeDetectorRef, Component, Inject, OnInit, ViewChild, ViewEncapsulation} from '@angular/core';
import {FormArray, FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialog} from '@angular/material/dialog';
import {ProjetoService} from '../../../shared/service/projeto.service';
import {ColecaoObjetoService} from '../../../shared/service/colecaoObjeto.service';
import {ConfiguracaoService} from '../../../shared/service/configuracao.service';
import {PageEvent} from '@angular/material/paginator';
import {MensagemDialogComponent} from '../mensagem-dialog/mensagem-dialog.component';
import {ColecaoAtributosService} from '../../../shared/service/colecaoAtributos.service';
import {MatAccordion} from '@angular/material/expansion';
import {FiltroService} from '../../../shared/service/filtro.service';
import {FiltroModel} from '../../../shared/model/filtro.model';

@Component({
  selector: 'app-configuracao-form-dialog',
  templateUrl: './configuracao-form-dialog.component.html',
  styleUrls: ['./configuracao-form-dialog.component.css']
})
export class ConfiguracaoFormDialogComponent implements OnInit {

  isLinear = true;
  firstFormGroup: FormGroup;
  colecaoObjetoLista: any = [];

  configuracoesLista: any = [];
  configuracoesListaTemp: any = [];
  @ViewChild('configuracoesLista') public acordion: MatAccordion;

  resultado: any = [];

  length: number;
  pageSize = 8;
  pageSizeOptions: number[] = [1, 2, 5, 8];
  pageEvent: PageEvent;
  panelOpenState: boolean[] = [];
  secondFormGroup: FormGroup;

  atributos: any = [];

  // tslint:disable-next-line:typedef
  setPageSizeOptions(setPageSizeOptionsInput: string) {
    if (setPageSizeOptionsInput) {
      this.pageSizeOptions = setPageSizeOptionsInput.split(',').map(str => +str);
    }
  }
  constructor(
    public colecaoObjetoService: ColecaoObjetoService,
    public configuracaoService: ConfiguracaoService,
    public colecaoAtributoService: ColecaoAtributosService,
    public filtroService: FiltroService,
    private fb: FormBuilder,
    @Inject(MAT_DIALOG_DATA) public data: any,
    public dialog: MatDialog,
    private cd: ChangeDetectorRef
  ) {

  }

  ngOnInit(): void {

    this.firstFormGroup = this.fb.group({
      noProprietarioBanco: new FormControl('REBECA'),
      noModulo: new FormControl(null, [Validators.required]),
      dsModulo: new FormControl(null, [Validators.required]),
      noObjetoBanco: new FormControl(null, [Validators.required]),
      idProjeto: new FormControl(this.data.idProjeto)
    });
    this.secondFormGroup = this.fb.group({
      Tags: this.fb.array([])
    });
    this.getColecaoObjeto();
    this.getConfiguracoesbyProjeto(this.data.idProjeto);

  }

  // tslint:disable-next-line:typedef
  getColecaoAtributo(noObjeto: string){
    this.colecaoAtributoService.getColecaoAtributos(noObjeto).subscribe(
      data => {
        this.atributos = data;
      }
    );
  }

  // tslint:disable-next-line:typedef
  getColecaoObjeto(){
    this.colecaoObjetoService.getColecaoObjeto().subscribe(
      data => {
        this.colecaoObjetoLista = data;
      }
    );
  }

  // tslint:disable-next-line:typedef
  getData( event?: PageEvent) {
    console.log(event);
    this.configuracoesLista = this.resultado.slice(event.pageIndex * event.pageSize,
      event.pageIndex * event.pageSize + event.pageSize);
    console.log(this.configuracoesLista);
    return event;
  }

  // tslint:disable-next-line:typedef
  getConfiguracoesbyProjeto(idProjeto: number){
    this.configuracaoService.getConfiguracaobyProjeto(idProjeto).subscribe(
      data => {
        this.resultado = data;
        this.length = this.resultado.length;
        this.configuracoesLista = this.resultado.slice(0, this.pageSize);
        this.secondFormGroup.reset();
        this.firstFormGroup.reset();
      }
    );
  }
  // tslint:disable-next-line:typedef
  mantemConfiguracao(idProjeto: number){
    if (this.firstFormGroup.valid) {

      this.firstFormGroup.patchValue({
        idProjeto,
        noProprietarioBanco: 'REBECA'
      });

      this.configuracaoService.postConfiguracao(this.firstFormGroup.value).subscribe(
        data => {

          // tslint:disable-next-line:no-shadowed-variable
          let i = this.configuracoesLista.length;
          // tslint:disable-next-line:forin
          while (i--) {
            this.configuracoesLista.splice(i, 1);
          }

          if (this.secondFormGroup.valid) {
            const filtroArray =  this.secondFormGroup.get('Tags').value;
            console.log('999999999999999999',this.secondFormGroup.get('Tags').value);
            for (const i in filtroArray){
              const nomeColuna = filtroArray[i].noColuna;
              console.log('0000000000000000000000000', nomeColuna);
              const novofiltro = new FiltroModel(0, nomeColuna, 'IGUAL', data);
              console.log('>>>>>>>>>>>>>', novofiltro);

              this.filtroService.postFiltro(novofiltro);

            }
          }

          this.getConfiguracoesbyProjeto(idProjeto);
          this.cd.detectChanges();

        }

      );

    }



  }

  // tslint:disable-next-line:typedef
  atualizar() {
    console.log('atualizando');
  }

  // tslint:disable-next-line:typedef
  deletar(idConfiguracao: number, noConfiguracao: string, tipo: string){
    console.log(idConfiguracao, noConfiguracao, tipo);
    const dialogRef = this.dialog.open(MensagemDialogComponent, {
      panelClass: 'popup',
      minWidth: '200px',
      minHeight: '200px',
      data: {idConfiguracao, noConfiguracao, tipo}
    });
    console.log('deletando configuracao', idConfiguracao) ;

    dialogRef.afterClosed().subscribe(() => {

      // tslint:disable-next-line:no-shadowed-variable
      let i = this.configuracoesLista.length;
      // tslint:disable-next-line:forin
      while (i--){
        this.configuracoesLista.splice(i, 1);
      }
      this.getConfiguracoesbyProjeto(this.data.idProjeto);
      this.cd.detectChanges();
    });
  }

  // tslint:disable-next-line:typedef
  get tagsArr() {
    return this.secondFormGroup.get('Tags') as FormArray;
  }

  // tslint:disable-next-line:typedef
  onSelect(tag: any) {
    this.tagsArr.push(
      this.fb.group({
        noColuna: tag.noColuna
      })
    );
    this.atributos.splice(this.atributos.indexOf(tag), 1);
  }

  onRemove(index, tag): void {
    this.tagsArr.removeAt(index);
    this.atributos.push(tag.value);
  }



}
