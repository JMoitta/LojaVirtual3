cREATE TABLE public.disciplina
(
  id serial,
  nome character varying(50),
  professor character varying(80),
  id_turma integer NOT NULL,
  CONSTRAINT disciplina_pkey PRIMARY KEY (id),
  CONSTRAINT disciplina_id_turma_fkey FOREIGN KEY (id_turma)
      REFERENCES public.turma (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);