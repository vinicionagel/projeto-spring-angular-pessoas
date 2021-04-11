CREATE TABLE pessoa (
                         id SERIAL NOT NULL,
                         nome CHARACTER VARYING(255) NOT NULL,
                         email CHARACTER VARYING(255),
                         naturalidade CHARACTER VARYING(255),
                         nacionalidade CHARACTER VARYING(255),
                         sexo CHARACTER VARYING(45),
                         cpf CHARACTER VARYING(45) NOT NULL,
                         data_nascimento DATE NOT NULL,
                         create_date_time timestamp,
                         update_date_time timestamp,
                         CONSTRAINT "usuario_pkey" PRIMARY KEY (id),
                         CONSTRAINT unicos UNIQUE (cpf)
);
