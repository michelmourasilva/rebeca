CREATE TABLE REBECA.TB_PROJETO
(
  ID_PROJETO NUMBER(14,0) NOT NULL
, NO_PROJETO  VARCHAR2(30 BYTE)
, DS_PROJETO  VARCHAR2(300 BYTE)
, CONSTRAINT PK_PROJETO PRIMARY KEY 
  (
    ID_PROJETO 
  )
  ENABLE 
);

ALTER TABLE REBECA.TB_PROJETO
ADD CONSTRAINT UK_PROJETO_01 UNIQUE 
(
  NO_PROJETO
)
ENABLE;

CREATE TABLE REBECA.TB_CONFIGURACAO_SERVICO 
(
  ID_CONFIGURACAO_SERVICO NUMBER(14, 0) NOT NULL 
, ID_PROJETO NUMBER(14, 0) NOT NULL 
, NO_MODULO VARCHAR2(20 BYTE) NOT NULL 
, DS_MODULO VARCHAR2(300 BYTE) NOT NULL 
, NO_OBJETO_BANCO VARCHAR2(30 BYTE) NOT NULL 
, NO_PROPRIETARIO_BANCO VARCHAR2(30 BYTE) NOT NULL 
, CONSTRAINT PK_CONFIGURACAO_SERVICO PRIMARY KEY 
  (
    ID_CONFIGURACAO_SERVICO 
  )
  ENABLE 
);

ALTER TABLE REBECA.TB_CONFIGURACAO_SERVICO
ADD CONSTRAINT UK_CONFIGURACAO_SERVICO_01 UNIQUE 
(
  ID_PROJETO 
, NO_MODULO 
, NO_OBJETO_BANCO 
, NO_PROPRIETARIO_BANCO 
)
ENABLE;

ALTER TABLE REBECA.TB_CONFIGURACAO_SERVICO
ADD CONSTRAINT FK_CONFIGURACAO_PROJETO FOREIGN KEY
(
  ID_PROJETO 
)
REFERENCES REBECA.TB_CONFIGURACAO_SERVICO
(
  ID_CONFIGURACAO_SERVICO 
)
ENABLE;

COMMENT ON TABLE REBECA.TB_CONFIGURACAO_SERVICO IS 'A Representational State Transfer (REST), em português Transferência de Estado Representacional, é uma abstração da arquitetura da World Wide Web, mais precisamente, é um estilo arquitetural que consiste de um conjunto coordenado de restrições arquiteturais aplicadas a componentes, conectores e elementos de dados dentro de um sistema de hipermídia distribuído.
O REST ignora os detalhes da implementação de componente e a sintaxe de protocolo com o objetivo de focar nos papéis dos componentes, nas restrições sobre sua interação com outros componentes e na sua interpretação de elementos de dados significantes.
Esta entidade irá auxiliar na configuração do serviço rest e a camada de acesso aos dados, fazendo com que o serviço seja acessado dinamicamente tendo como base a configuração definida pela administração de dados.';

comment on column REBECA.TB_CONFIGURACAO_SERVICO.ID_CONFIGURACAO_SERVICO IS 'Identificador gerado automaticamente pela sequence seq_configuracao_servico. Auxilia na identificação da configuração do serviço REST.';

comment on column REBECA.TB_CONFIGURACAO_SERVICO.ID_PROJETO IS 'Código do projeto ';

comment on column REBECA.TB_CONFIGURACAO_SERVICO.NO_MODULO IS 'Nome do módulo que será passada pela URI do serviço REST (URI - Identificador de Recursos Universal, como diz o próprio nome, é o identificador do recurso. Pode ser uma imagem, uma página, etc, pois tudo o que está disponível na internet precisa de um identificador único para que não seja confundido.)';

comment on column REBECA.TB_CONFIGURACAO_SERVICO.DS_MODULO IS 'Breve descrição do módulo que está sendo acessado.';

comment on column REBECA.TB_CONFIGURACAO_SERVICO.NO_OBJETO_BANCO IS 'Nome físico do objeto dentro do banco de dados.';

comment on column REBECA.TB_CONFIGURACAO_SERVICO.NO_PROPRIETARIO_BANCO IS 'Nome do owner do objeto dentro do banco de dados';


GRANT SELECT, INSERT, UPDATE, DELETE ON REBECA.TB_CONFIGURACAO_SERVICO  TO REBECA;

CREATE TABLE REBECA.TB_FILTRO_SERVICO 
(
  ID_FILTRO_SERVICO NUMBER(14, 0) NOT NULL 
, CD_CONDICAO VARCHAR2(4000 BYTE) NOT NULL 
, ID_CONFIGURACAO_SERVICO NUMBER(14, 0) NOT NULL 
, CONSTRAINT PK_FILTRO_SERVICO PRIMARY KEY  (ID_FILTRO_SERVICO )ENABLE 
);

ALTER TABLE REBECA.TB_FILTRO_SERVICO
ADD CONSTRAINT FK_FILTRO_SERVICO_CONFIGURACAO FOREIGN KEY
(
  ID_CONFIGURACAO_SERVICO 
)
REFERENCES REBECA.TB_CONFIGURACAO_SERVICO
(
  ID_CONFIGURACAO_SERVICO 
)
ENABLE;

COMMENT ON TABLE REBECA.TB_FILTRO_SERVICO IS 'Cada configuração poderá utilizar mais de uma condição de filtro juntamente com um objecto específico do banco. ';

comment on column REBECA.TB_FILTRO_SERVICO.ID_FILTRO_SERVICO IS 'Identificador automatico gerado pela sequence seq_filtro_servico. Identificador que auxilia na identificação de um filtro que poderá ser utilizado dentro de uma configuração do serviço REST';

comment on column REBECA.TB_FILTRO_SERVICO.CD_CONDICAO IS 'Condição que será utilizada pela configuração do serviço REST. Os valores que serão subistituidos deverão seguir o padrão de binds de aplicação, isto é, utilizando dois pontos e um número que representa a ordem da troca do bind.
Ex: co_ies = :1 and id_ies = :2. No caso de instruções que utilizam a condição IN o valor deste campo deve utilizar a função REBECA.fnc_string_virgula_tabela. Ex: CO_IES in ( select * from (TABLE(REBECA.fnc_string_virgula_tabela(:1))));';

comment on column REBECA.TB_FILTRO_SERVICO.ID_CONFIGURACAO_SERVICO IS 'Identificador gerado automaticamente pela sequence seq_configuracao_servico. Auxilia na identificação da configuração do serviço REST.';


GRANT SELECT, INSERT, UPDATE, DELETE ON REBECA.TB_FILTRO_SERVICO TO REBECA;
