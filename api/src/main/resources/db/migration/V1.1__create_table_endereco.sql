CREATE TABLE estado (
       id SERIAL NOT NULL,
       nome CHARACTER VARYING(200) NOT NULL,
       sigla CHARACTER VARYING(100) NOT NULL,
       CONSTRAINT estado_pkey PRIMARY KEY (id)
);

CREATE TABLE municipio (
      id SERIAL NOT NULL,
      estado_id INTEGER NOT NULL,
      nome CHARACTER VARYING(200) NOT NULL,
      CONSTRAINT municipio_pkey PRIMARY KEY (id),
      CONSTRAINT estado_municipio_fk FOREIGN KEY (estado_id)
      REFERENCES estado (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE endereco (
      id SERIAL NOT NULL,
      pessoa_id INTEGER NOT NULL,
      municipio_id INTEGER NOT NULL,
      nome_logradouro CHARACTER VARYING(200) NOT NULL,
      complemento CHARACTER VARYING(200),
      caixa_postal CHARACTER VARYING(200),
      bairro CHARACTER VARYING(200) NOT NULL,
      cep INTEGER NOT NULL,
      numero CHARACTER VARYING(10)NOT NULL,
      create_date_time timestamp,
      update_date_time timestamp,
      CONSTRAINT endereco_pkey PRIMARY KEY (id),
      CONSTRAINT pessoa_endereco_fk FOREIGN KEY (pessoa_id)
          REFERENCES pessoa (id) MATCH SIMPLE
          ON UPDATE NO ACTION ON DELETE NO ACTION,
      CONSTRAINT municipio_endereco_fk FOREIGN KEY (municipio_id)
          REFERENCES municipio (id) MATCH SIMPLE
          ON UPDATE NO ACTION ON DELETE NO ACTION
);