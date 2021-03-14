create or replace type cmi.ob_filtro authid definer as object(
 id_filtro numeric(14),
 no_atributo varchar2(30), 
 tp_condicao varchar2(2)
 )
/
create or replace type cmi.nt_filtro as table of cmi.ob_filtro
/
create table cmi.tb_configuracao (
  id_configuracao number(14)
  ,co_projeto varchar2(20)
  ,no_modulo varchar2(20)
  ,ds_modulo varchar2(300)
  ,no_objeto_banco varchar2(30)
  ,no_proprietario_banco varchar2(30)
  ,nt_filtro cmi.nt_filtro
)
nested table nt_filtro store as to_filtro;


/*
alter table cmi.tb_configuracao drop column nt_filtro;

drop type cmi.nt_filtro;

alter table cmi.tb_configuracao 
add (nt_filtro cmi.nt_filtro ) 
nested table nt_filtro store as to_filtro;
*/



update cmi.tb_configuracao set nt_filtro = cmi.nt_filtro(cmi.ob_filtro(1,'ID_IES','?','=')) where id_configuracao = 1;
update cmi.tb_configuracao set nt_filtro = cmi.nt_filtro(cmi.ob_filtro(2,'','?','=')) where id_configuracao = 1;
update cmi.tb_configuracao set nt_filtro = cmi.nt_filtro(cmi.ob_filtro(1,'ID_IES','?','=')) where id_configuracao = 1;


String msg = e.getSQLException().toString();		
		
		Pattern pattern = Pattern.compile("ORA-[0-9]{5}");
		Matcher m = pattern.matcher(msg);
		
		String erro = new String();
		 if (m.find()) {
			 	erro = "0: " + m.group(0) + 
			 			"-1:" + m.group(1) +
			 			"-2:" + m.group(2) +
			 			"-3:" + m.group(3);
/*
		        System.out.println(m.group(1)); // first expression from round brackets (Testing)
		        System.out.println(m.group(2)); // second one (123)
		        System.out.println(m.group(3)); // third one (Testing)
		        */
		    }
		