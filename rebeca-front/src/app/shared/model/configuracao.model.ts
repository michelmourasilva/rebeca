import {FiltroModel} from './filtro.model';

export class ConfiguracaoModel{
  idProjeto: number;
  dsModulo: string;
  noModulo: string;
  noProprietarioBanco: string;
  noObjetoBanco: string;
  filtros: FiltroModel[];
}
