export class FiltroModel{
  idFiltro: number;
  noAtributo: string;
  tipoFiltro: string;
  idConfiguracao: number;

  constructor(idFiltro: number,
              noAtributo: string,
              tipoFiltro: string,
              idConfiguracao: number,
              ) {
    this.idFiltro = idFiltro;
    this.noAtributo = noAtributo;
    this.tipoFiltro = tipoFiltro;
    this.idConfiguracao = idConfiguracao;
  }
}
