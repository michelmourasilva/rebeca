import {ChangeDetectorRef, Component, ElementRef, Inject, OnInit, ViewChild, ViewEncapsulation} from '@angular/core';
import {FormArray, FormBuilder, FormControl, FormGroup, FormGroupDirective, Validators} from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material/dialog';
import {ProjetoService} from '../../../shared/service/projeto.service';
import {ColecaoObjetoService} from '../../../shared/service/colecaoObjeto.service';
import {ConfiguracaoService} from '../../../shared/service/configuracao.service';
import {PageEvent} from '@angular/material/paginator';
import {MensagemDialogComponent} from '../mensagem-dialog/mensagem-dialog.component';
import {ColecaoAtributosService} from '../../../shared/service/colecaoAtributos.service';
import {MatAccordion} from '@angular/material/expansion';
import {FiltroService} from '../../../shared/service/filtro.service';
import {FiltroModel} from '../../../shared/model/filtro.model';
import {ThemePalette} from '@angular/material/core';
import {MatStepper} from '@angular/material/stepper';

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
  @ViewChild('stepper') stepper: MatStepper;
  @ViewChild(FormGroupDirective) formDirective: FormGroupDirective;
  @ViewChild('Tags', {static: true}) Tags: ElementRef<HTMLInputElement>;

  resultado: any = [];

  length: number;
  pageSize = 8;
  pageSizeOptions: number[] = [1, 2, 5, 8];
  pageEvent: PageEvent;
  panelOpenState: boolean[] = [];
  secondFormGroup: FormGroup;

  atributos: any = [];

  color: ThemePalette = 'primary';
  checked = false;
  disabled = false;

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
    private cd: ChangeDetectorRef,
    public dialogRef: MatDialogRef<ConfiguracaoFormDialogComponent>,
  ) {

  }

  ngOnInit(): void {
    this.newReset();
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
    this.configuracoesLista = this.resultado.slice(event.pageIndex * event.pageSize,
      event.pageIndex * event.pageSize + event.pageSize);
    return event;
  }

  // tslint:disable-next-line:typedef
  getConfiguracoesbyProjeto(idProjeto: number){
    this.configuracaoService.getConfiguracaobyProjeto(idProjeto).subscribe(
      data => {
        this.resultado = data;
        this.length = this.resultado.length;
        this.configuracoesLista = this.resultado.slice(0, this.pageSize);
        // this.secondFormGroup.reset();
        // this.firstFormGroup.reset();
      }
    );
  }
  // tslint:disable-next-line:typedef
  mantemConfiguracao(idProjeto: number){

    if (this.firstFormGroup.valid) {
      // console.log('>>> configuracao:', this.firstFormGroup.get('idConfiguracao').value);
      this.firstFormGroup.patchValue({
        idProjeto,
        noProprietarioBanco: 'REBECA'
      });

      // Insert
      const idConfiguracao = this.firstFormGroup.get('idConfiguracao').value;
      if (idConfiguracao){
        // console.log('Alteracao');

        // Realiza update nos dados principais
        this.configuracaoService.putConfiguracao(idConfiguracao, this.firstFormGroup.value).subscribe();

        // Deleta os filtros dessa configuração
        this.configuracaoService.getConfiguracaobyId(idConfiguracao).subscribe(
          data => {
            data.filtros.forEach(function( filtro, index){
              this.filtroService.deleteFiltro(filtro.idFiltro);
            }.bind(this));
          }
        );

        // tslint:disable-next-line:no-shadowed-variable
        let i = this.configuracoesLista.length;
        // tslint:disable-next-line:forin
        while (i--) {
          this.configuracoesLista.splice(i, 1);
        }

        // Reinsert Filtros
        if (this.secondFormGroup.valid) {
          const filtroArray =  this.secondFormGroup.get('Tags').value;

          for (const i in filtroArray){
            const nomeColuna = filtroArray[i].noColuna;
            const novofiltro = new FiltroModel(0, nomeColuna, 'IGUAL', idConfiguracao);
            this.filtroService.postFiltro(novofiltro);
          }
        }

        this.getConfiguracoesbyProjeto(idProjeto);
        console.log('configuracoes>>>>',this.configuracoesLista);
        this.cd.detectChanges();

        const tipo = 'alterar_configuracao_sucesso';

        const dialogRef = this.dialog.open(MensagemDialogComponent, {
          panelClass: 'popup',
          minWidth: '200px',
          minHeight: '200px',
          data: {tipo}
        });
        this.clearAll();
      } else {

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

            for (const i in filtroArray){
              const nomeColuna = filtroArray[i].noColuna;
              const novofiltro = new FiltroModel(0, nomeColuna, 'IGUAL', data);
              this.filtroService.postFiltro(novofiltro);

            }
          }
          this.getConfiguracoesbyProjeto(idProjeto);
          this.cd.detectChanges();

          const tipo = 'cadastrar_configuracao_sucesso';

          const dialogRef = this.dialog.open(MensagemDialogComponent, {
            panelClass: 'popup',
            minWidth: '200px',
            minHeight: '200px',
            data: {tipo}
          });
          this.clearAll();
        }
    );
      }


    }
  }

  clearAll(): void {
    this.stepper.selectedIndex = 0;
    this.formDirective.resetForm();
    this.newReset();
    this.stepper.reset();
    }

  // tslint:disable-next-line:typedef
  atualizar(idConfiguracao: number) {

    this.clearAll();

    this.firstFormGroup.get('idConfiguracao').setValue(idConfiguracao);
    this.configuracaoService.getConfiguracaobyId(idConfiguracao).subscribe(
      data => {
        this.firstFormGroup.get('noModulo').setValue(data.noModulo);
        this.firstFormGroup.get('dsModulo').setValue(data.dsModulo);
        this.firstFormGroup.get('noObjetoBanco').setValue(data.noObjetoBanco);
        this.getColecaoAtributo(data.noObjetoBanco);

        this.colecaoAtributoService.getColecaoAtributos(data.noObjetoBanco).subscribe(
          (result: any) => {
            if (result){
              for (const results in result){
                console.log('subscribe');
              }
            }
          }
        );

        // tslint:disable-next-line:typedef
        data.filtros.forEach(function( atributo, index){

          this.colecaoAtributoService.getAtributo(data.noObjetoBanco, atributo.noAtributo).subscribe(
            (result: any) => {
              // console.log('atributo da edicao', result);
              this.onSelect(result);

              // tslint:disable-next-line:variable-name
              this.atributos.forEach((value_atributos, index_atributos) => {
                  if (value_atributos.noColuna === result.noColuna) {
                    this.atributos.splice(index_atributos, 1);
                  }
              } );
              // console.log('>>>atributos', this.atributos);
            }
          );
        }.bind(this));
        // console.log('Data da alteracao', data);
        // this.onSelect
      }
    );
  }

  // tslint:disable-next-line:typedef
  newReset(){
    this.firstFormGroup = this.fb.group({
      noProprietarioBanco: new FormControl('REBECA'),
      noModulo: new FormControl(null, [Validators.required]),
      dsModulo: new FormControl(null, [Validators.required]),
      noObjetoBanco: new FormControl(null, [Validators.required]),
      idProjeto: new FormControl(this.data.idProjeto),
      idConfiguracao: new FormControl(null)
    });
    this.secondFormGroup = this.fb.group({
      Tags: this.fb.array([])
    });
  }

  // tslint:disable-next-line:typedef
  deletar(idConfiguracao: number, noConfiguracao: string, tipo: string){
    const dialogRef = this.dialog.open(MensagemDialogComponent, {
      panelClass: 'popup',
      minWidth: '200px',
      minHeight: '200px',
      data: {idConfiguracao, noConfiguracao, tipo}
    });

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

  onClose(): void {
    this.dialogRef.close();
    window.location.reload();
  }

}
