CREATE TABLE forma_pgto (
	fpg_id			serial primary key,
	fpg_descricao		varchar(20),
	fpg_num_max_parc	integer,
	fpg_num_padrao_parc	integer,
	fpg_intervalo_dias	integer,
	fpg_percentual_acres	float
);