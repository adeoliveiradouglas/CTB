truncate table cargo;

/*Povoar tabela de cargos*/
insert into cargo values (1, "Administrador", "Tem total domínio sobre todo o sistema.");
insert into cargo values (2, "Gestor geral", 'Enxerga todos os processos da empresa e inicia novos contratos.');
insert into cargo values (3, "Gestor/Fiscal", 'Adiciona processos aos contratos já existentes ou é um fiscal de processo.');
insert into cargo values (4, "Tesoureiro", 'Libera o pagamento de processos.');
insert into cargo values (5, "Diretor", 'Visualiza contratos e processos.');
insert into cargo values (6, "Presidente", 'Visualiza contratos e processos.');

select * from cargo;