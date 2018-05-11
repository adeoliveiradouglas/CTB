create table grupo(
	idGrupo int primary key not null auto_increment,
    nome varchar(50) not null,
    descricao varchar(200) not null
);

create table permissao(
	idPermissao int primary key not null auto_increment,
    permissao varchar(50) not null,
    descricao varchar(100) not null
);

create table usuario(
	idUsuario int primary key not null auto_increment,
    nome varchar(60) not null,
    login varchar(60) not null,
    senha varchar(400) not null,
    ativo int(1) not null
);

CREATE TABLE usuarioXgrupo
(
  idUsuario INT NOT NULL,  
  idGrupo   INT NOT NULL,
  CONSTRAINT PK_USU_GRUP   PRIMARY KEY(idUsuario,idGrupo),
  FOREIGN KEY(idUsuario) REFERENCES usuario(idUsuario), 
  FOREIGN KEY(idGrupo)  REFERENCES grupo(idGrupo)
);

CREATE TABLE permissaoXgrupo
(
  idPermissao INT NOT NULL,  
  idGrupo     INT NOT NULL,
  CONSTRAINT PK_PER_GRUP   PRIMARY KEY(idPermissao,idGrupo),
  CONSTRAINT FK_PERM_1 FOREIGN KEY(idPermissao) REFERENCES permissao(idPermissao), 
  CONSTRAINT FK_GRUP_1 FOREIGN KEY(idGrupo)  REFERENCES grupo(idGrupo)
);


/*Grupos*/
insert into grupo (nome, descricao) values ('ADMINISTRADORES', 'Administrador');
insert into grupo (nome, descricao) values ('USUARIOS', 'Usuários comuns');
insert into grupo (nome, descricao) values ('BACKOFFICE', 'Cadastro');

/*Usuarios*/
insert into usuario (nome, login, senha, ativo) values ('Douglas Almeida', 'douglas', '$2a$10$rQaIkdsWCvF2.LEhPm1UWu4Zy9tU3XhFqB4ascKWxRCgTGbjRBtV2' ,1);

/*Permissões*/
insert into permissao (permissao, descricao) values ('ROLE_CADASTROUSUARIO', 'Cadastro de novos usuários');
insert into permissao (permissao, descricao) values ('ROLE_CONSULTAUSUARIO', 'Consulta de usuários');
insert into permissao (permissao, descricao) values ('ROLE_ADMIN', 'Administração');

/*Usuario em grupo*/
set @idU := (select idUsuario from usuario where login = 'douglas');
set @idG := (select idGrupo from grupo where nome = 'ADMINISTRADORES');

insert into usuarioxgrupo (idUsuario, idGrupo) values (@idU, @idG);

/*Permissoes para grupos*/
set @idP := (select idPermissao from permissao where permissao = 'ROLE_ADMIN');
set @idG := (select idGrupo from grupo where nome = 'ADMINISTRADORES');
insert into permissaoxgrupo (idPermissao, idGrupo) values (@idP, @idG);
set @idG := (select idGrupo from grupo where nome = 'USUARIOS');
set @idP := (select idPermissao from permissao where permissao = 'ROLE_CONSULTAUSUARIO');
insert into permissaoxgrupo (idPermissao, idGrupo) values (@idP, @idG);
set @idG := (select idGrupo from grupo where nome = 'BACKOFFICE');
set @idP := (select idPermissao from permissao where permissao = 'ROLE_CADASTROUSUARIO');
insert into permissaoxgrupo (idPermissao, idGrupo) values (@idP, @idG);

/*
insert into  () values ();
select * from grupo;
delete from grupo where idGrupo = 6;
*/