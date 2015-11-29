-- Script para criar as tabelas no postgres

CREATE TYPE tipoitemdeextrato AS ENUM (
    'despesa',
    'receita'
);

CREATE TABLE "Categoria" (
    nome character varying(50) NOT NULL,
    "idUsuario" character varying(50) NOT NULL
);

CREATE TABLE "Conta" (
    banco character varying(50) NOT NULL,
    agencia character varying(50) NOT NULL,
    numero character varying(50) NOT NULL,    
    "idConta" numeric(30,0) NOT NULL,
    "idUsuario" character varying(50) NOT NULL,
    saldo real NOT NULL
);

CREATE TABLE "Extrato" (
    "idExtrato" numeric(30,0) NOT NULL,
    mes smallint NOT NULL,
    ano smallint NOT NULL,
    "idConta" numeric(30,0) NOT NULL,
    "valorInicial" real NOT NULL,
    "valorFinal" real NOT NULL
);

CREATE TABLE "ItemDeExtrato" (
    "idItemDeExtrato" numeric(30,0) NOT NULL,
    titulo character varying(50) NOT NULL,
    valor real NOT NULL,
    observacao character varying(50),
    dia smallint NOT NULL,
    ocorrencia smallint NOT NULL,
    "idExtrato" numeric(30,0) NOT NULL,
    "idCategoria" character varying(50),
    tipo tipoitemdeextrato NOT NULL
);

CREATE TABLE "Usuario" (
    nome character varying(50) NOT NULL,
    senha character varying(50) NOT NULL,
    email character varying(50) NOT NULL
);

ALTER TABLE ONLY "Categoria"
    ADD CONSTRAINT pk_categoria PRIMARY KEY (nome);
	
ALTER TABLE ONLY "Conta"
    ADD CONSTRAINT pk_conta PRIMARY KEY ("idConta");
	
ALTER TABLE ONLY "Extrato"
    ADD CONSTRAINT pk_extrato PRIMARY KEY ("idExtrato");

ALTER TABLE ONLY "ItemDeExtrato"
    ADD CONSTRAINT "pk_itemDeExtrato" PRIMARY KEY ("idItemDeExtrato");
	
ALTER TABLE ONLY "Usuario"
    ADD CONSTRAINT pk_usuario PRIMARY KEY (nome);

ALTER TABLE ONLY "ItemDeExtrato"
    ADD CONSTRAINT fk_categoria FOREIGN KEY ("idCategoria") REFERENCES "Categoria"(nome) ON UPDATE CASCADE ON DELETE SET NULL;	

ALTER TABLE ONLY "Extrato"
    ADD CONSTRAINT fk_conta FOREIGN KEY ("idConta") REFERENCES "Conta"("idConta") ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE ONLY "ItemDeExtrato"
    ADD CONSTRAINT fk_extrato FOREIGN KEY ("idExtrato") REFERENCES "Extrato"("idExtrato") ON DELETE CASCADE;
	
ALTER TABLE ONLY "Conta"
    ADD CONSTRAINT fk_usuario FOREIGN KEY ("idUsuario") REFERENCES "Usuario"(nome);
    
ALTER TABLE ONLY "Categoria"
    ADD CONSTRAINT fk_usuario FOREIGN KEY ("idUsuario") REFERENCES "Usuario"(nome);    