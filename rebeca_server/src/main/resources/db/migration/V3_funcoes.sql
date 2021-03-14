create or replace procedure REBECA.prc_recupera_configuracao_serv(p_sistema in varchar2, p_modulo in varchar2, p_id_filtro in numeric, p_criterio_pesquisa in varchar2, p_json_string out clob) is       
/*
Procedimento armazenado que recupera uma configuração feita para um serviço rest. 
Parâmetros de entrada:
			p_sistema = Nome do sistema que consta na tabela REBECA.tb_sistema. O código deste sistema deverá está na configuração da tabela REBECA.tb_configuracao_servico
			p_modulo = Nome do modulo que identifica o objeto do banco através da URI. Este nome deverá está na configuração da tabela REBECA.tb_configuracao_servico
			p_id_filtro = Número do fitro que está mapeado na tabela REBECA.tb_filtro_servico
			p_criterio_pesquisa = Valores que servirão de bind para o critério configurado na tabela REBECA.tb_filtro_servico. Devem seguir a ordem que consta no filtro. Ex: para o bind das variáveis :1 e :2 deve existir dois valores correspondentes a ordem das variáveis. 
Parâmetros de saida:
			p_json_string = retorno do objeto da configuração em formato Json.
			Layout:
			{
				"metadados": [
					{
						"nomeatributo": "ID_IES",
						"tipoatributo": "numerico"
					}
				],
				"dados": [
					{
						"CAMPO1": valor,
						"CAMPO2": valor,
					}
				]
			}
objetivo: Manter os atributos dos dados censitários.
	Autor: Michel Moura Silva
	Criado em: 23/05/2018
	
Exemplo de chamada da procedure:
declare 
		v_output clob;
	begin
		rebeca.prc_recupera_configuracao_serv('CENSO_SUPERIOR', 'IES', 1, 'ESCOLA1,ESCOLA2', v_output);
		dbms_output.put_line(v_output);
	end;

Changelog:
--------------------------------------------------------------------
Demanda 	Data     		Responsável	    Descricao
--------------------------------------------------------------------
xxxx		20/08/2018		Michel Moura	Criação da procedure 
--------------------------------------------------------------------
*/
        v_sistema                    varchar2(30):= p_sistema;                         -- Variável que receberá o parâmetro p_sistema
        v_modulo                     varchar2(30):= p_modulo;                          -- Variável que receberá o parâmetro p_modulo
        v_id_filtro                  numeric(14)   := p_id_filtro;                     -- Indicador de qual filtro deverá ser utilizado juntamente com a consulta principal
        v_criterio_pesquisa	         varchar2(4000):= p_criterio_pesquisa;	           -- Conteúdo que juntamente com o filtro irá restringir o resultado da consulta principal
        v_nome_ref_cursor            varchar(300);                                     -- String que será utilizada para compor o refcursor principal
        v_ref_cursor_principal       sys_refcursor;                                    -- Cursor principal contendo os dados do objeto escolhido.        
        v_cabecalho_metadados        clob;                                             -- Variavél que irá receber as strings para formar o cabecalho - metadados da consulta         
        v_numero_cursor              number;                                           -- Variável que irá armzenar o número do cursor para que seja utilizado na função dbms_sql.describe_columns2
        v_numero_colunas             number;                                           -- Variável que armazena o número de colunas que o ref_cursor possui. 
        v_tabela_descricao_colunas   dbms_sql.desc_tab2;                               -- Tabela com a descrição/tipo de todos os campos do ref_cursor
        v_coluna_conteudo			 VARCHAR2(100);		                      		   -- Variável que será preenchida com o conteúdo dos tipos de cada atributo/coluna identificado no ref_cursor
        v_dados_conteudo			 CLOB;				                          	   -- Variável que será preenchida com a combinação chave valor do conteudo do objeto que está sendo lido
        v_valor_campo			     varchar2(4000);		                      	   -- Valor da coluna que está sendo definida ao ler o ref_cursor
        v_id_configuracao			 number;				                      	   -- Variável que irá receber da consulta principal o número da configuracao selecionada. Essa informação será enviada para recuperar o filtro da configuração
        l_criterio_pesquisa_tamanho  BINARY_INTEGER;								   -- Quantidade de itens identificados na string
        l_tb_criterio_pesquisa       DBMS_UTILITY.uncl_array; 						   -- Tabela criada a partir dos itens identificados como critério de pesquisa       
        ignore        				 INTEGER;
        v_cursor_abertura   		 INTEGER;										   -- Abertura do cursor principal
        v_cursor_criterios sys_refcursor;
        v_registros_criterios varchar2(4000);
        v_elemento_criterio integer :=0;
BEGIN
        -- consulta que recupera qual o nome do objecto reponsável por trazer qual objeto que contem as informações do módulo escolhido          
        select 'select * from ' || configuracao.no_proprietario_banco || '.' || configuracao.no_objeto_banco, id_configuracao_servico into v_nome_ref_cursor, v_id_configuracao
        from REBECA.tb_configuracao_servico configuracao 
        inner join REBECA.vw_projeto projeto on configuracao.co_projeto = projeto.co_projeto
        where projeto.no_projeto = v_sistema and configuracao.no_modulo = v_modulo;    
		-- Verifica se foi passado algum filtro por parâmetro
        if p_id_filtro is not null then      
          -- recupera qual são os filtros que serão usados
          select v_nome_ref_cursor || ' WHERE ' || cd_condicao into v_nome_ref_cursor from REBECA.tb_filtro_servico filtro where filtro.id_configuracao_servico = v_id_configuracao and filtro.id_filtro_servico = v_id_filtro and filtro.cd_condicao <> '@COMPLETO';        
        end if;
		-- Abre um cursor
		v_cursor_abertura := dbms_sql.open_cursor; 
		-- Parse no sql criado na junção da configuração e dos filtros caso existam
		dbms_sql.parse(v_cursor_abertura, v_nome_ref_cursor, dbms_sql.native);   
		-- Inicia a implementação do bind dos filtros
		if p_id_filtro is not null then
		  if length(v_id_filtro) >= 1 and instr(v_nome_ref_cursor,'REBECA.fnc_string_virgula_tabela') = 0  then            
			 for i in ( select column_value from (TABLE(REBECA.fnc_string_virgula_tabela(v_criterio_pesquisa))))
			 loop
			 v_elemento_criterio := v_elemento_criterio + 1;
			 dbms_sql.bind_variable(v_cursor_abertura, ':'|| v_elemento_criterio, i.column_value);    
			 end loop;      
		  else
			dbms_sql.bind_variable(v_cursor_abertura, ':1', v_criterio_pesquisa);    
		  end if;
		end if;
		ignore := DBMS_SQL.EXECUTE(v_cursor_abertura); 
		-- Atribui a uma variável o cursor com todos as binds aplicadas
        v_ref_cursor_principal := DBMS_SQL.TO_REFCURSOR(v_cursor_abertura);
        -- Como não é necessario percorrer todos os dados, já que em um primeiro momento o cursor seria somente para recuperar os tipos de dados de cada campo
        -- a consulta será limitada ao primeiro registro        
        -- Inicio da criação do cabeçalho
        v_cabecalho_metadados := '{"metadados":[';
        -- Transformar o ref_cursor em um dbms_sql.number para que seja utilizado na função dmbs_sql.describr_columns2
        v_numero_cursor      := dbms_sql.to_cursor_number(v_ref_cursor_principal);
        -- Função que irá descrever o conteúdo do ref_cursor, retornando o número de colunas e quais os tipos de cada coluna.
        dbms_sql.describe_columns2(v_numero_cursor                -- Número do ID do cursor para as colunas que estão sendo descritas
                                  ,v_numero_colunas               -- Retorno da procedure com o número de colunas na lista
                                  ,v_tabela_descricao_colunas);   -- Tabela retornada com a descrição de cada uma das colunas da consulta, indexada de 1 ao número de elementos na lista de seleção da consulta
        -- Laço que irá percorrer todos os campos do ref_cursor para que seja montado a variável v_cabecalho_metadados contendo a descrição de todos os campos 
        for i in 1 .. v_numero_colunas
        loop
                case
                        when v_tabela_descricao_colunas(i).col_type in (2,8) then -- se a coluna for do tipo numerica
                                v_coluna_conteudo := '{"nomeatributo":"' || v_tabela_descricao_colunas(i).col_name || '","tipoatributo":"numerico"},';
                        when v_tabela_descricao_colunas(i).col_type = 12 then --  -- se a coluna for do tipo date
                                v_coluna_conteudo := '{"nomeatributo":"' || v_tabela_descricao_colunas(i).col_name || '","tipoatributo":"data"},';
                        else   -- se a coluna for do tipo texto
                                v_coluna_conteudo := '{"nomeatributo":"' || v_tabela_descricao_colunas(i).col_name || '","tipoatributo":"texto"},';
                end case;				
				-- procedimento que irá gravar uma quantidade espcificada de dados no final e um lob.
                dbms_lob.writeappend(v_cabecalho_metadados 		-- localizador do lob interno a ser gravado.
                                    ,length(v_coluna_conteudo)	-- número de caracteres que será gravado
                                    ,v_coluna_conteudo);		-- buffer de entrada para a gravação
      -- define uma coluna a ser selecionada no cursor fornecido
        dbms_sql.define_column(
				v_numero_cursor, -- número do id do cursor para as colunas que estão sendo descritas
				i, 				 -- posição relativa da coluna na linha que está sendo definida, onde a primeira coluna em uma instrução tem posição 1
				v_valor_campo, 		 -- valor da coluna que está sendo definida
				4000);			 -- o tamanho máximo esperado do valor de coluna,                                    
        end loop;
        -- substituição necessária para que a string Json esteja correta e inicialize o próximo nó que são os de dados.
		v_cabecalho_metadados := rtrim(v_cabecalho_metadados ,',') || '],"dados":';    
		-- Abrir novamente o ref cursor principal com todo o conteúdo do objeto selecionado
		--open v_ref_cursor_principal for v_nome_ref_cursor;                 
        -- Montage do conteúdo com os dados em formato Json
        v_dados_conteudo := v_dados_conteudo || '[';    
        WHILE DBMS_SQL.FETCH_ROWS(v_numero_cursor) > 0 LOOP                        
            if(DBMS_SQL.LAST_ROW_COUNT<>1)then              
              v_dados_conteudo := v_dados_conteudo|| ',';
            end if;                                    
            v_dados_conteudo := v_dados_conteudo ||'{';
            FOR i IN 1..v_numero_colunas LOOP
                DBMS_SQL.COLUMN_VALUE(v_numero_cursor, i, v_valor_campo);
                IF(v_tabela_descricao_colunas(i).col_type not in (2,8))then
                    v_valor_campo := '"' || nvl(replace(replace(v_valor_campo,'"',''),':',''),null) || '"';
                end if;               
                v_dados_conteudo := v_dados_conteudo || '"' || v_tabela_descricao_colunas(i).col_name || '": ' || v_valor_campo;
                if(i < v_numero_colunas)then                   
                    v_dados_conteudo := v_dados_conteudo || ',';
                end if;
            END LOOP;           
            v_dados_conteudo := v_dados_conteudo || '}';
        END LOOP;   
        v_dados_conteudo := v_dados_conteudo || ']}';             
       DBMS_SQL.CLOSE_CURSOR(v_numero_cursor);       
       p_json_string := v_cabecalho_metadados || v_dados_conteudo;              	  
	END;
	/
	CREATE OR REPLACE TYPE REBECA.tp_string_virgula_tabela  AS  TABLE OF VARCHAR2(100);
	/	
	CREATE OR REPLACE    FUNCTION REBECA.fnc_string_virgula_tabela( p_lista IN VARCHAR2)
      RETURN REBECA.tp_string_virgula_tabela
	  /*
	  Função que irá transformar uma string com virgula em uma type
	  Ex: SELECT * FROM TABLE(REBECA.fnc_string_virgula_tabela('valor1, valor2, valor3, valor4, valor5, valor6'))
	  */
    AS
      v_lista VARCHAR2(32767) := p_lista || ',';
      v_virgula_indice PLS_INTEGER;
      v_indice PLS_INTEGER := 1;
      v_tabela_resultado REBECA.tp_string_virgula_tabela := REBECA.tp_string_virgula_tabela();
    BEGIN
      LOOP
        v_virgula_indice := INSTR(v_lista, ',', v_indice);
        EXIT
      WHEN v_virgula_indice = 0;
        v_tabela_resultado.EXTEND;
        v_tabela_resultado(v_tabela_resultado.COUNT) := TRIM(SUBSTR(v_lista, 
                                          v_indice, 
                                          v_virgula_indice - v_indice
                                          )
                                   );
        v_indice            := v_virgula_indice + 1;
      END LOOP;
      RETURN v_tabela_resultado;
    END fnc_string_virgula_tabela;
  /
	
	grant execute on REBECA.prc_recupera_configuracao_serv to REBECA;
	grant execute on REBECA.fnc_string_virgula_tabela to REBECA;
	grant execute on REBECA.tp_string_virgula_tabela to REBECA;