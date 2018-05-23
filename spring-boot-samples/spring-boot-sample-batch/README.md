
select * from batch_job_instance;
select * from item;

-- *********************************************

CREATE OR REPLACE FUNCTION rndstr_lower(in length INTEGER) returns text AS $$
	SELECT array_to_string(ARRAY(
		SELECT chr((97 + round(random() * 25)) :: integer) 
		FROM generate_series(1, length)), '');
$$ LANGUAGE SQL;

-- *********************************************

truncate table batch_job_instance cascade;
truncate table batch_job_execution cascade;
truncate table batch_job_execution_params cascade;
truncate table batch_job_execution_context cascade;
truncate table batch_step_execution cascade;
truncate table batch_step_execution_context cascade;

truncate table item;

-- **********************************************

insert into item
select generate_series id,
	rndstr_lower(30)::varchar(30) nome,
	false valido,
	0 versao
from generate_series(1,100);