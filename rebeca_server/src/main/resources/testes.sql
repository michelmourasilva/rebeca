call corp.pkg_utilitario.prc_set_usuario_sistema(1,'172.0.0.1');

insert into corp.tb_configuracao_servico(
id_configuracao_servico
,co_projeto
,no_modulo
,ds_modulo
,no_objeto_banco
,no_proprietario_banco)
values
(corp.seq_configuracao_servico.nextval,1710601,'MATRICULA','Relação das matrículas','mv_data_escola_brasil_matricul','CENSO_BASICO');

grant select on censo_basico.mv_data_escola_brasil_matricul to corp;

insert into corp.tb_configuracao_servico(
id_configuracao_servico
,co_projeto
,no_modulo
,ds_modulo
,no_objeto_banco
,no_proprietario_banco)
values
(corp.seq_configuracao_servico.nextval,1710401,'AVALOBJ','Avaliação objetiva','vw_avaliacao_objetiva','ENEM');

grant select on enem.vw_avaliacao_objetiva to corp;


/*
censo basico
testes - co_entidade = 35228266, 35274100, 35442987
http://localhost:9093/configuracoes/all/EDUCASENSO/MATRICULA
http://localhost:9093/configuracoes/all/EDUCASENSO/MATRICULA/1/35228266
http://localhost:9093/configuracoes/all/EDUCASENSO/MATRICULA/2/70
http://localhost:9093/configuracoes/all/EDUCASENSO/MATRICULA/6/35228266,35274100,35442987
*/
SELECT * FROM censo_basico.mv_data_escola_brasil_matricul where co_entidade = 35228266
insert into corp.tb_filtro_servico(ID_FILTRO_SERVICO,CD_CONDICAO,ID_CONFIGURACAO_SERVICO) values (corp.seq_filtro_servico.nextval, 'CO_ENTIDADE = :1', 1);
insert into corp.tb_filtro_servico(ID_FILTRO_SERVICO,CD_CONDICAO,ID_CONFIGURACAO_SERVICO) values (corp.seq_filtro_servico.nextval, 'QT_MATRICULAS > :1', 1);
insert into corp.tb_filtro_servico(ID_FILTRO_SERVICO,CD_CONDICAO,ID_CONFIGURACAO_SERVICO) values (corp.seq_filtro_servico.nextval, 'CO_ENTIDADE in ( select * from (TABLE(corp.fnc_string_virgula_tabela(:1))))', 1); -- 35228266, 35274100, 35442987



/*
enem
enem.vw_avaliacao_objetiva 
http://localhost:9093/configuracoes/all/ENEM/AVALOBJ
http://localhost:9093/configuracoes/all/ENEM/AVALOBJ/3/20
http://localhost:9093/configuracoes/all/ENEM/AVALOBJ/4/5

*/
insert into corp.tb_filtro_servico(ID_FILTRO_SERVICO,CD_CONDICAO,ID_CONFIGURACAO_SERVICO) values (corp.seq_filtro_servico.nextval, 'VL_RESULTADO > :1', 2);
insert into corp.tb_filtro_servico(ID_FILTRO_SERVICO,CD_CONDICAO,ID_CONFIGURACAO_SERVICO) values (corp.seq_filtro_servico.nextval, 'QT_ACERTOS > :1', 2);



COMMIT

SELECT * FROM CORP.TB_PROJETO WHERE CO_PROJETO = 1710601