drop database gestaodecontratos;
create database gestaodecontratos;

create table gestaodecontratos.grupo(
	idGrupo int primary key not null auto_increment,
    nome varchar(50) not null,
    descricao varchar(200) not null
);

create table gestaodecontratos.permissao(
	idPermissao int primary key not null auto_increment,
    permissao varchar(50) not null,
    descricao varchar(100) not null
);

create table gestaodecontratos.usuario(
	idUsuario int primary key not null auto_increment,
    nome varchar(60) not null,
    login varchar(60) not null,
    senha varchar(400) not null,
    ativo boolean not null
);

CREATE TABLE gestaodecontratos.usuarioXgrupo
(
  idUsuario INT NOT NULL,  
  idGrupo   INT NOT NULL,
  CONSTRAINT PK_USU_GRUP   PRIMARY KEY(idUsuario,idGrupo),
  FOREIGN KEY(idUsuario) REFERENCES usuario(idUsuario), 
  FOREIGN KEY(idGrupo)  REFERENCES grupo(idGrupo)
);

CREATE TABLE gestaodecontratos.permissaoXgrupo
(
  idPermissao INT NOT NULL,  
  idGrupo     INT NOT NULL,
  CONSTRAINT PK_PER_GRUP   PRIMARY KEY(idPermissao,idGrupo),
  CONSTRAINT FK_PERM_1 FOREIGN KEY(idPermissao) REFERENCES permissao(idPermissao), 
  CONSTRAINT FK_GRUP_1 FOREIGN KEY(idGrupo)  REFERENCES grupo(idGrupo)
);


/*Grupos*/
insert into gestaodecontratos.grupo (nome, descricao) values ('ADMINISTRADORES', 'Administrador');
insert into gestaodecontratos.grupo (nome, descricao) values ('USUARIOS', 'Usuários comuns');
insert into gestaodecontratos.grupo (nome, descricao) values ('BACKOFFICE', 'Cadastro');

/*Usuarios*/
insert into gestaodecontratos.usuario (nome, login, senha, ativo) values ('Douglas Almeida', 'douglas', '$2a$10$rQaIkdsWCvF2.LEhPm1UWu4Zy9tU3XhFqB4ascKWxRCgTGbjRBtV2' , true);

/*Permissões*/
insert into gestaodecontratos.permissao (permissao, descricao) values ('ROLE_CADASTROUSUARIO', 'Cadastro de novos usuários');
insert into gestaodecontratos.permissao (permissao, descricao) values ('ROLE_CONSULTAUSUARIO', 'Consulta de usuários');
insert into gestaodecontratos.permissao (permissao, descricao) values ('ROLE_ADMIN', 'Administração');

/*Usuario em grupo*/
set @idU := (select idUsuario from gestaodecontratos.usuario where login = 'douglas');
set @idG := (select idGrupo from gestaodecontratos.grupo where nome = 'ADMINISTRADORES');
insert into gestaodecontratos.usuarioXgrupo (idUsuario, idGrupo) values (@idU, @idG);

/*Permissoes para grupos*/
set @idP := (select idPermissao from gestaodecontratos.permissao where permissao = 'ROLE_ADMIN');
set @idG := (select idGrupo from gestaodecontratos.grupo where nome = 'ADMINISTRADORES');
insert into gestaodecontratos.permissaoXgrupo (idPermissao, idGrupo) values (@idP, @idG);
set @idG := (select idGrupo from gestaodecontratos.grupo where nome = 'USUARIOS');
set @idP := (select idPermissao from gestaodecontratos.permissao where permissao = 'ROLE_CONSULTAUSUARIO');
insert into gestaodecontratos.permissaoXgrupo (idPermissao, idGrupo) values (@idP, @idG);
set @idG := (select idGrupo from gestaodecontratos.grupo where nome = 'BACKOFFICE');
set @idP := (select idPermissao from gestaodecontratos.permissao where permissao = 'ROLE_CADASTROUSUARIO');
insert into gestaodecontratos.permissaoXgrupo (idPermissao, idGrupo) values (@idP, @idG);

/*
insert into  () values ();
select * from grupo;
delete from grupo where idGrupo = 6;
*/