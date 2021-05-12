CREATE TABLE REBECA.TB_PROJETO
(
    ID_PROJETO NUMBER(14, 0) GENERATED ALWAYS AS IDENTITY NOT NULL,
    NO_PROJETO VARCHAR2(30 BYTE)                          NOT NULL,
    DS_PROJETO VARCHAR2(4000 BYTE)                         NOT NULL,
    TX_URL VARCHAR2(4000 BYTE)   				NULL
    TX_CAMINHO_IMAGEM VARCHAR2(4000 BYTE)    		NULL
    CONSTRAINT PK_PROJETO PRIMARY KEY(ID_PROJETO) ENABLE
);

COMMENT ON TABLE REBECA.TB_PROJETO IS 'Projeto que disponbilizará dados em formato Rest para o Rebeca';
comment on column REBECA.TB_PROJETO.ID_PROJETO IS 'Identificador gerado automaticamente pelo Oracle. Auxilia na identificação da configuração do serviço REST.';
comment on column REBECA.TB_PROJETO.NO_PROJETO IS 'Nome único do projeto que irá disponibilizar dados para o Rebeca';
comment on column REBECA.TB_PROJETO.DS_PROJETO IS 'Breve descrição do projeto.';
comment on column REBECA.TB_PROJETO.TX_URL IS 'URL que pode ser apresentada nos cards do projeto';
comment on column REBECA.TB_PROJETO.TX_CAMINHO_IMAGEM IS 'Caminho de qualquer imagem que pode ser apresentada nos cards do projeto.';

ALTER TABLE REBECA.TB_PROJETO
    ADD CONSTRAINT UK_PROJETO_01 UNIQUE ( NO_PROJETO ) ENABLE;

CREATE TABLE REBECA.TB_CONFIGURACAO_SERVICO
(
    ID_CONFIGURACAO_SERVICO NUMBER(14, 0) GENERATED ALWAYS AS IDENTITY NOT NULL,
    ID_PROJETO              NUMBER(14, 0)                              NOT NULL,
    NO_MODULO               VARCHAR2(20 BYTE)                          NOT NULL,
    DS_MODULO               VARCHAR2(300 BYTE)                         NOT NULL,
    NO_OBJETO_BANCO         VARCHAR2(30 BYTE)                          NOT NULL,
    NO_PROPRIETARIO_BANCO   VARCHAR2(30 BYTE)                          NOT NULL,
    CONSTRAINT PK_CONFIGURACAO_SERVICO PRIMARY KEY ( ID_CONFIGURACAO_SERVICO ) ENABLE
);

ALTER TABLE REBECA.TB_CONFIGURACAO_SERVICO
    ADD CONSTRAINT UK_CONFIGURACAO_SERVICO_01 UNIQUE ( ID_PROJETO, NO_MODULO, NO_OBJETO_BANCO, NO_PROPRIETARIO_BANCO ) ENABLE;

ALTER TABLE REBECA.TB_CONFIGURACAO_SERVICO
    ADD CONSTRAINT FK_CONFIGURACAO_PROJETO FOREIGN KEY ( ID_PROJETO )
        REFERENCES REBECA.TB_PROJETO ( ID_PROJETO ) ENABLE;

COMMENT ON TABLE REBECA.TB_CONFIGURACAO_SERVICO IS 'A Representational State Transfer (REST), em português Transferência de Estado Representacional, é uma abstração da arquitetura da World Wide Web, mais precisamente, é um estilo arquitetural que consiste de um conjunto coordenado de restrições arquiteturais aplicadas a componentes, conectores e elementos de dados dentro de um sistema de hipermídia distribuído.
O REST ignora os detalhes da implementação de componente e a sintaxe de protocolo com o objetivo de focar nos papéis dos componentes, nas restrições sobre sua interação com outros componentes e na sua interpretação de elementos de dados significantes.
Esta entidade irá auxiliar na configuração do serviço rest e a camada de acesso aos dados, fazendo com que o serviço seja acessado dinamicamente tendo como base a configuração definida pela administração de dados.';

comment on column REBECA.TB_CONFIGURACAO_SERVICO.ID_CONFIGURACAO_SERVICO IS 'Identificador gerado  automaticamente pelo Oracle. Auxilia na identificação da configuração do serviço REST.';
comment on column REBECA.TB_CONFIGURACAO_SERVICO.ID_PROJETO IS 'Código do projeto ';
comment on column REBECA.TB_CONFIGURACAO_SERVICO.NO_MODULO IS 'Nome do módulo que será passada pela URI do serviço REST (URI - Identificador de Recursos Universal, como diz o próprio nome, é o identificador do recurso. Pode ser uma imagem, uma página, etc, pois tudo o que está disponível na internet precisa de um identificador único para que não seja confundido.)';
comment on column REBECA.TB_CONFIGURACAO_SERVICO.DS_MODULO IS 'Breve descrição do módulo que está sendo acessado.';
comment on column REBECA.TB_CONFIGURACAO_SERVICO.NO_OBJETO_BANCO IS 'Nome físico do objeto dentro do banco de dados.';
comment on column REBECA.TB_CONFIGURACAO_SERVICO.NO_PROPRIETARIO_BANCO IS 'Nome do owner do objeto dentro do banco de dados';

GRANT SELECT, INSERT, UPDATE, DELETE ON REBECA.TB_CONFIGURACAO_SERVICO TO REBECA;

CREATE TABLE REBECA.TB_FILTRO_SERVICO
(
    ID_FILTRO_SERVICO       NUMBER(14, 0) GENERATED ALWAYS AS IDENTITY NOT NULL,
    NO_ATRIBUTO             VARCHAR2(30)                               not null,
    ID_CONFIGURACAO_SERVICO NUMBER(14, 0)                              NOT NULL,
    TP_CONDICAO             NUMBER(1,0)                                NOT NULL,
    CONSTRAINT PK_FILTRO_SERVICO PRIMARY KEY (ID_FILTRO_SERVICO) ENABLE
);

ALTER TABLE REBECA.TB_FILTRO_SERVICO
    ADD CONSTRAINT FK_FILTRO_SERVICO_CONFIGURACAO FOREIGN KEY
        ( ID_CONFIGURACAO_SERVICO )
        REFERENCES REBECA.TB_CONFIGURACAO_SERVICO
            ( ID_CONFIGURACAO_SERVICO ) ON DELETE CASCADE ENABLE ;

COMMENT ON TABLE REBECA.TB_FILTRO_SERVICO IS 'Cada configuração poderá utilizar mais de uma condição de filtro juntamente com um objecto específico do banco. ';
comment on column REBECA.TB_FILTRO_SERVICO.ID_FILTRO_SERVICO IS 'Identificador gerado automaticamente pelo Oracle. Identificador que auxilia na identificação de um filtro que poderá ser utilizado dentro de uma configuração do serviço REST';
comment on column REBECA.TB_FILTRO_SERVICO.TP_CONDICAO is 'Tipo de operacao que podera ser aplicada em um campo especifico do objeto
Valores possiveis:
IGUAL(0, "= :1"),
DIFERENTE(1, "!= :1"),
MAIOR(2, "> :1"),
MENOR(3, "< :1"),
MAIOROUIGUAL(4, ">= :1"),
MENOROUIGUAL(5, "<= :1"),
IN(6, "( select * from (TABLE(REBECA.fnc_string_virgula_tabela(:1))))");';

comment on column REBECA.TB_FILTRO_SERVICO.ID_CONFIGURACAO_SERVICO IS 'Identificador gerado automaticamente pela sequence seq_configuracao_servico. Auxilia na identificação da configuração do serviço REST.';

GRANT SELECT, INSERT, UPDATE, DELETE ON REBECA.TB_FILTRO_SERVICO TO REBECA;

create or replace view REBECA.VW_TIPO_FILTRO as
WITH CTE_TIPOFILTRO AS (
    SELECT 0 ID_TIPO_FILTRO, '= :1' DS_TIPO_FILTRO FROM DUAL
    UNION ALL
    SELECT 1 ID_TIPO_FILTRO, '!= :1' DS_TIPO_FILTRO FROM DUAL
    UNION ALL
    SELECT 2 ID_TIPO_FILTRO, '> :1' DS_TIPO_FILTRO FROM DUAL
    UNION ALL
    SELECT 3 ID_TIPO_FILTRO, '< :1' DS_TIPO_FILTRO FROM DUAL
    UNION ALL
    SELECT 4 ID_TIPO_FILTRO, '>= :1' DS_TIPO_FILTRO FROM DUAL
    UNION ALL
    SELECT 5 ID_TIPO_FILTRO, '<= :1' DS_TIPO_FILTRO FROM DUAL
    UNION ALL
    SELECT 6 ID_TIPO_FILTRO, '( select * from (TABLE(REBECA.fnc_string_virgula_tabela(:1))))' FROM DUAL)
SELECT ID_TIPO_FILTRO, DS_TIPO_FILTRO
FROM CTE_TIPOFILTRO;

comment on table REBECA.VW_TIPO_FILTRO is 'Conversão dos valores numéricos dosoperadores que podem ser usados nos filtros juntamente com os atributos de um objeto';
comment on column REBECA.VW_TIPO_FILTRO.ID_TIPO_FILTRO IS 'Identificador do tipo do filtro';
comment on column REBECA.VW_TIPO_FILTRO.DS_TIPO_FILTRO IS 'Combinação do operador de comparação com o valor a ser substituido em tempo real na consulta dos conjuntos de dados';

GRANT SELECT ON REBECA.VW_TIPO_FILTRO TO REBECA;

CREATE OR REPLACE VIEW REBECA.VW_END_POINT
AS
select sum(nvl(Null, 1)) over (order by origem.END_POINT ROWS UNBOUNDED PRECEDING) ID, origem.*
from (
         SELECT '/dataset/' || PROJETO.NO_PROJETO || '/' || servico.no_modulo || '/' END_POINT,
                '_NA' ATRIBUTO_FILTRO,
                PROJETO.NO_PROJETO,
                SERVICO.NO_MODULO,
                SERVICO.DS_MODULO
         FROM REBECA.TB_CONFIGURACAO_SERVICO SERVICO
                  INNER JOIN REBECA.TB_PROJETO PROJETO ON SERVICO.ID_PROJETO = PROJETO.ID_PROJETO
         UNION ALL
         SELECT '/dataset/' || PROJETO.NO_PROJETO || '/' || servico.no_modulo || '/' || filtro.id_filtro_servico ||  '/?' END_POINT,
                filtro.NO_ATRIBUTO || ' ' || TIPOFILTRO.DS_TIPO_FILTRO,
                PROJETO.NO_PROJETO,
                SERVICO.NO_MODULO,
                SERVICO.DS_MODULO
         FROM REBECA.TB_CONFIGURACAO_SERVICO SERVICO
                  INNER JOIN REBECA.TB_PROJETO PROJETO ON SERVICO.ID_PROJETO = PROJETO.ID_PROJETO
                  INNER JOIN REBECA.TB_FILTRO_SERVICO FILTRO
                             ON SERVICO.ID_CONFIGURACAO_SERVICO = filtro.ID_CONFIGURACAO_SERVICO
                  INNER JOIN REBECA.VW_TIPO_FILTRO TIPOFILTRO ON FILTRO.TP_CONDICAO = TIPOFILTRO.ID_TIPO_FILTRO
     ) origem
;
GRANT SELECT ON REBECA.VW_END_POINT TO REBECA;

COMMENT ON TABLE REBECA.TB_FILTRO_SERVICO IS 'End points da API gerados a partir das configurações feitas para o projeto. Obs: quando o end point possui um filtro específico ele irá ser apresentado envolto com chaves ({}), porém ao ser usado na API deverá ser informado somente o valor que deseja filtrar. Ex: filtro com ID de número 1 e condição definida como CAMPO = :1 / Como deverá ser passado no endpoint /1/VALORDOCAMPO';

comment on column REBECA.VW_END_POINT.ID IS 'Identificador gerado automaticamente somente para referência da linha. ';
comment on column REBECA.VW_END_POINT.END_POINT IS 'Exemplo de como está sedo gerado um endpoint para disponibilização de dados. Se baseia na união de várias informações das outras tabelas.';
comment on column REBECA.VW_END_POINT.ATRIBUTO_FILTRO IS 'Caso um endpoint possua um filtro em um campo específico, será apresentado a regra correspondente para o símbolo "?" apresentado no campo END_POINT. ';
comment on column REBECA.VW_END_POINT.NO_PROJETO IS 'Nome do projeto que está disponibilizando os dados. ';
comment on column REBECA.VW_END_POINT.NO_MODULO IS 'Nome do módulo deste sistema.';
comment on column REBECA.VW_END_POINT.DS_MODULO IS 'Breve descrição do módulo de um sistema. ';

create or replace view REBECA.VW_OBJETOS_DISPONIVEIS
as
select colunas.column_id ID_COLUNA,
       colunas.table_name NO_OBJETO,
       colunas.column_name NO_COLUNA,
       colunas.data_type TP_COLUNA,
       colunas.data_length NU_TAMANHO_COLUNA,
       colunas.data_precision NU_PRECISAO_COLUNA,
       decode(colunas.nullable,'N','N','Y','S') TP_NULO
from sys.all_tab_columns colunas
inner join sys.all_views views on colunas.owner = views.owner
                              and colunas.table_name = views.view_name
where views.owner = 'REBECA'
and table_name not in ('VW_OBJETOS_DISPONIVEIS', 'VW_TIPO_FILTRO', 'VW_END_POINT')
order by colunas.owner, colunas.table_name, colunas.column_id;

GRANT SELECT ON REBECA.VW_OBJETOS_DISPONIVEIS TO REBECA;

COMMENT ON TABLE REBECA.VW_OBJETOS_DISPONIVEIS IS 'Objetos disponíveis para serem utilizados e apresentados como endPoint. Está sendo utilizado os proprios objetos que apresentam as coleções e objetos do Oracle';



