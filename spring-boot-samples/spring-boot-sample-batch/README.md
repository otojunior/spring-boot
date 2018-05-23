Função para criação de string aleatória:
----------------------------------------

CREATE OR REPLACE FUNCTION rndstr_lower(in length INTEGER) returns text AS $$
	SELECT array_to_string(ARRAY(
		SELECT chr((97 + round(random() * 25)) :: integer) 
		FROM generate_series(1, length)), '');
$$ LANGUAGE SQL;


Criação de dados na tabela Item:
--------------------------------

insert into item
select generate_series id,
	rndstr_lower(30)::varchar(30) nome,
	false valido,
	0 versao
from generate_series(1,100);