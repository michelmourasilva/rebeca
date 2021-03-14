CREATE TABLE CORP.TB_CONFIGURACAO_SERVICO 
(
  ID_CONFIGURACAO_SERVICO NUMBER(14, 0) NOT NULL 
, CO_PROJETO NUMBER(14, 0) NOT NULL 
, NO_MODULO VARCHAR2(20 BYTE) NOT NULL 
, DS_MODULO VARCHAR2(300 BYTE) NOT NULL 
, NO_OBJETO_BANCO VARCHAR2(30 BYTE) NOT NULL 
, NO_PROPRIETARIO_BANCO VARCHAR2(30 BYTE) NOT NULL 
, ID_USUARIO_OPERACAO  NUMBER(14)           not null,
, DT_OPERACAO          DATE                 not null,
, NU_OPERACAO          NUMBER(5)            not null,
, NU_IP_OPERACAO       VARCHAR2(16)         not null,
, CONSTRAINT PK_CONFIGURACAO_SERVICO PRIMARY KEY 
  (
    ID_CONFIGURACAO 
  )
  ENABLE 
) 
TABLESPACE TBS_CORP_D;

ALTER TABLE CORP.TB_CONFIGURACAO_SERVICO
ADD CONSTRAINT UK_CONFIGURACAO_SERVICO_01 UNIQUE 
(
  CO_PROJETO 
, NO_MODULO 
, NO_OBJETO_BANCO 
, NO_PROPRIETARIO_BANCO 
)
ENABLE;

COMMENT ON TABLE CORP.TB_CONFIGURACAO_SERVICO IS 'A Representational State Transfer (REST), em português Transferência de Estado Representacional, é uma abstração da arquitetura da World Wide Web, mais precisamente, é um estilo arquitetural que consiste de um conjunto coordenado de restrições arquiteturais aplicadas a componentes, conectores e elementos de dados dentro de um sistema de hipermídia distribuído.
O REST ignora os detalhes da implementação de componente e a sintaxe de protocolo com o objetivo de focar nos papéis dos componentes, nas restrições sobre sua interação com outros componentes e na sua interpretação de elementos de dados significantes.
Esta entidade irá auxiliar na configuração do serviço rest e a camada de acesso aos dados, fazendo com que o serviço seja acessado dinamicamente tendo como base a configuração definida pela administração de dados.';

comment on column CORP.TB_CONFIGURACAO_SERVICO.ID_CONFIGURACAO IS 'Identificador gerado automaticamente pela sequence seq_configuracao_servico. Auxilia na identificação da configuração do serviço REST.';

comment on column CORP.TB_CONFIGURACAO_SERVICO.CO_PROJETO IS 'Código do projeto ';

comment on column CORP.TB_CONFIGURACAO_SERVICO.NO_MODULO IS 'Nome do módulo que será passada pela URI do serviço REST (URI - Identificador de Recursos Universal, como diz o próprio nome, é o identificador do recurso. Pode ser uma imagem, uma página, etc, pois tudo o que está disponível na internet precisa de um identificador único para que não seja confundido.)';

comment on column CORP.TB_CONFIGURACAO_SERVICO.DS_MODULO IS 'Breve descrição do módulo que está sendo acessado.';

comment on column CORP.TB_CONFIGURACAO_SERVICO.NO_OBJETO_BANCO IS 'Nome físico do objeto dentro do banco de dados.';

comment on column CORP.TB_CONFIGURACAO_SERVICO.NO_PROPRIETARIO_BANCO IS 'Nome do owner do objeto dentro do banco de dados';

comment on column CORP.TB_CONFIGURACAO_SERVICO.ID_USUARIO_OPERACAO is
'Identifica o usuário que efetuou qualquer alteração no registro. 
Origem: Chave primária da tabela ssi.tb_usuario_sistema. Carregado automaticamente pela trigger de auditoria da tabela.';

comment on column CORP.TB_CONFIGURACAO_SERVICO.DT_OPERACAO is
'Data na qual foi realizada a última operação no registro. 
Origem: Carregado automaticamente pela trigger de auditoria da tabela.';

comment on column CORP.TB_CONFIGURACAO_SERVICO.NU_OPERACAO is
'Número de operações realizadas no registro. Na inserção é iniciado com 0 e incrementado de 1 em 1 toda vez que houver modificação. Coluna preenchida por trigger de auditoria.';

comment on column CORP.TB_CONFIGURACAO_SERVICO.NU_IP_OPERACAO is
'Número do endereço IP da interface de rede utilizada pelo usuário do sistema que realizou a última operação no registro. Coluna preenchida por trigger de auditoria.';

GRANT SELECT, INSERT, UPDATE, DELETE ON CORP.TB_CONFIGURACAO_SERVICO  TO RL_CORP_APP;

CREATE TABLE CORP.TB_FILTRO_SERVICO 
(
  ID_FILTRO_SERVICO NUMBER(14, 0) NOT NULL 
, CD_CONDICAO VARCHAR2(4000 BYTE) NOT NULL 
, ID_CONFIGURACAO_SERVICO NUMBER(14, 0) NOT NULL 
, ID_USUARIO_OPERACAO  NUMBER(14)           not null,
, DT_OPERACAO          DATE                 not null,
, NU_OPERACAO          NUMBER(5)            not null,
, NU_IP_OPERACAO       VARCHAR2(16)         not null,
, CONSTRAINT PK_FILTRO_SERVICO PRIMARY KEY  (ID_FILTRO )ENABLE 
) 
TABLESPACE TBS_CORP_D;

ALTER TABLE CORP.TB_FILTRO_SERVICO
ADD CONSTRAINT FK_FILTRO_SERVICO_CONFIGURACAO FOREIGN KEY
(
  ID_CONFIGURACAO 
)
REFERENCES CORP.TB_CONFIGURACAO_SERVICO
(
  ID_CONFIGURACAO 
)
ENABLE;

COMMENT ON TABLE CORP.TB_FILTRO_SERVICO IS 'Cada configuração poderá utilizar mais de uma condição de filtro juntamente com um objecto específico do banco. ';

comment on column CORP.TB_FILTRO_SERVICO.ID_FILTRO_SERVICO IS 'Identificador automatico gerado pela sequence seq_filtro_servico. Identificador que auxilia na identificação de um filtro que poderá ser utilizado dentro de uma configuração do serviço REST';

comment on column CORP.TB_FILTRO_SERVICO.CD_CONDICAO IS 'Condição que será utilizada pela configuração do serviço REST. Os valores que serão subistituidos deverão seguir o padrão de binds de aplicação, isto é, utilizando dois pontos e um número que representa a ordem da troca do bind.
Ex: co_ies = :1 and id_ies = :2. No caso de instruções que utilizam a condição IN o valor deste campo deve utilizar a função corp.fnc_string_virgula_tabela. Ex: CO_IES in ( select * from (TABLE(corp.fnc_string_virgula_tabela(:1))));';

comment on column CORP.TB_FILTRO_SERVICO.ID_CONFIGURACAO IS 'Identificador gerado automaticamente pela sequence seq_configuracao_servico. Auxilia na identificação da configuração do serviço REST.';

comment on column CORP.TB_FILTRO_SERVICO.ID_USUARIO_OPERACAO is
'Identifica o usuário que efetuou qualquer alteração no registro. 
Origem: Chave primária da tabela ssi.tb_usuario_sistema. Carregado automaticamente pela trigger de auditoria da tabela.';

comment on column CORP.TB_FILTRO_SERVICO.DT_OPERACAO is
'Data na qual foi realizada a última operação no registro. 
Origem: Carregado automaticamente pela trigger de auditoria da tabela.';

comment on column CORP.TB_FILTRO_SERVICO.NU_OPERACAO is
'Número de operações realizadas no registro. Na inserção é iniciado com 0 e incrementado de 1 em 1 toda vez que houver modificação. Coluna preenchida por trigger de auditoria.';

comment on column CORP.TB_FILTRO_SERVICO.NU_IP_OPERACAO is
'Número do endereço IP da interface de rede utilizada pelo usuário do sistema que realizou a última operação no registro. Coluna preenchida por trigger de auditoria.';

GRANT SELECT, INSERT, UPDATE, DELETE ON CORP.TB_FILTRO_SERVICO  TO RL_CORP_APP;


create or replace trigger CORP.TG_CONFIGURACAO_SERVICO before insert or update or delete on CORP.TB_CONFIGURACAO_SERVICO
referencing new as new old as old
  for each row 
begin
   if inserting then 
      :NEW.id_usuario_operacao := corp.pkg_utilitario.vg_id_usuario_sistema;
      :NEW.nu_ip_operacao      := corp.pkg_utilitario.vg_nu_ip_alteracao;
      :NEW.dt_operacao := sysdate;
      :NEW.nu_operacao := 0;
   elsif updating then 
      :NEW.id_usuario_operacao := corp.pkg_utilitario.vg_id_usuario_sistema;
      :NEW.nu_ip_operacao      := corp.pkg_utilitario.vg_nu_ip_alteracao;
      :NEW.dt_operacao := sysdate;
      :NEW.nu_operacao := :OLD.nu_operacao + 1;
   
  
	    
insert into CORP.TH_CONFIGURACAO_SERVICO
(
  ID_CONFIGURACAO,
  CO_PROJETO,
  NO_MODULO,
  DS_MODULO,
  NO_OBJETO_BANCO,
  NO_PROPRIETARIO_BANCO,
  ID_USUARIO_OPERACAO,
  DT_OPERACAO,
  NU_OPERACAO,
  NU_IP_OPERACAO,
  TP_OPERACAO
)
values
(
  :OLD.ID_CONFIGURACAO,
  :OLD.CO_PROJETO,
  :OLD.NO_MODULO,
  :OLD.DS_MODULO,
  :OLD.NO_OBJETO_BANCO,
  :OLD.NO_PROPRIETARIO_BANCO,
  :OLD.ID_USUARIO_OPERACAO,
  :OLD.DT_OPERACAO,
  :OLD.NU_OPERACAO,
  :OLD.NU_IP_OPERACAO,
  'U'
);

elsif deleting then 

insert into CORP.TH_CONFIGURACAO_SERVICO
(
  ID_CONFIGURACAO,
  CO_PROJETO,
  NO_MODULO,
  DS_MODULO,
  NO_OBJETO_BANCO,
  NO_PROPRIETARIO_BANCO,
  ID_USUARIO_OPERACAO,
  DT_OPERACAO,
  NU_OPERACAO,
  NU_IP_OPERACAO,
  TP_OPERACAO
)
values
(
  :OLD.ID_CONFIGURACAO,
  :OLD.CO_PROJETO,
  :OLD.NO_MODULO,
  :OLD.DS_MODULO,
  :OLD.NO_OBJETO_BANCO,
  :OLD.NO_PROPRIETARIO_BANCO,
  :OLD.ID_USUARIO_OPERACAO,
  :OLD.DT_OPERACAO,
  :OLD.NU_OPERACAO,
  :OLD.NU_IP_OPERACAO,
  'D'
);

insert into CORP.TH_CONFIGURACAO_SERVICO
(
  ID_CONFIGURACAO,
  CO_PROJETO,
  NO_MODULO,
  DS_MODULO,
  NO_OBJETO_BANCO,
  NO_PROPRIETARIO_BANCO,
  ID_USUARIO_OPERACAO,
  DT_OPERACAO,
  NU_OPERACAO,
  NU_IP_OPERACAO,
  TP_OPERACAO
)
values
(  :OLD.ID_CONFIGURACAO,
  :OLD.CO_PROJETO,
  :OLD.NO_MODULO,
  :OLD.DS_MODULO,
  :OLD.NO_OBJETO_BANCO,
  :OLD.NO_PROPRIETARIO_BANCO,
  :OLD.ID_USUARIO_OPERACAO,
  SYSDATE,
  :OLD.NU_OPERACAO,
  :OLD.NU_IP_OPERACAO,  'U'
);

end if;
end;
/

create or replace trigger CORP.TG_FILTRO_SERVICO before insert or update or delete on CORP.TB_FILTRO_SERVICO
referencing new as new old as old
  for each row 
begin
   if inserting then 
      :NEW.id_usuario_operacao := corp.pkg_utilitario.vg_id_usuario_sistema;
      :NEW.nu_ip_operacao      := corp.pkg_utilitario.vg_nu_ip_alteracao;
      :NEW.dt_operacao := sysdate;
      :NEW.nu_operacao := 0;
   elsif updating then 
      :NEW.id_usuario_operacao := corp.pkg_utilitario.vg_id_usuario_sistema;
      :NEW.nu_ip_operacao      := corp.pkg_utilitario.vg_nu_ip_alteracao;
      :NEW.dt_operacao := sysdate;
      :NEW.nu_operacao := :OLD.nu_operacao + 1;
 
	    
insert into CORP.TH_FILTRO_SERVICO
(
  ID_FILTRO_SERVICO,
  CD_CONDICAO,
  ID_CONFIGURACAO,
  ID_USUARIO_OPERACAO,
  DT_OPERACAO,
  NU_OPERACAO,
  NU_IP_OPERACAO,
  TP_OPERACAO
)
values
(
  :OLD.ID_FILTRO_SERVICO,
  :OLD.CD_CONDICAO,
  :OLD.ID_CONFIGURACAO,
  :OLD.ID_USUARIO_OPERACAO,
  :OLD.DT_OPERACAO,
  :OLD.NU_OPERACAO,
  :OLD.NU_IP_OPERACAO,
  'U'
);

elsif deleting then 

insert into CORP.TH_FILTRO_SERVICO
(
  ID_FILTRO_SERVICO,
  CD_CONDICAO,
  ID_CONFIGURACAO,
  ID_USUARIO_OPERACAO,
  DT_OPERACAO,
  NU_OPERACAO,
  NU_IP_OPERACAO,
  TP_OPERACAO
)
values
(
  :OLD.ID_FILTRO_SERVICO,
  :OLD.CD_CONDICAO,
  :OLD.ID_CONFIGURACAO,
  :OLD.ID_USUARIO_OPERACAO,
  :OLD.DT_OPERACAO,
  :OLD.NU_OPERACAO,
  :OLD.NU_IP_OPERACAO,
  'D'
);

insert into CORP.TH_FILTRO_SERVICO
(
  ID_FILTRO_SERVICO,
  CD_CONDICAO,
  ID_CONFIGURACAO,
  ID_USUARIO_OPERACAO,
  DT_OPERACAO,
  NU_OPERACAO,
  NU_IP_OPERACAO,
  TP_OPERACAO
)
values
(  :OLD.ID_FILTRO_SERVICO,
  :OLD.CD_CONDICAO,
  :OLD.ID_CONFIGURACAO,
  :OLD.ID_USUARIO_OPERACAO,
  SYSDATE,
  :OLD.NU_OPERACAO,
  :OLD.NU_IP_OPERACAO,  'U'
);

end if;
end;
/
