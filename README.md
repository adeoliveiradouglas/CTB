# SGC - Sistema de Gestao de Contratos
Sistema para gerir os contratos de pagamentos de uma empresa
	
* Log versões
	* 1.1
		* Suporte para planilhas .xlsx
		* Identifica quem pagou o processo
		* Usuários podem ter 2 cargos. Exemplo: um gestor pode ser tesoureiro ao mesmo tempo
		* Novos cargos para visualização de dados: presidente e diretor
		* Avisa aos responsáveis sobre vencimento dos contratos por email
		* Tesoureiro agora também vê os dados dos processos em aberto 
		
* Bugs encontrados:
	- planilhas devem estar no formato .xls 97- 2003 (Resolvido)
	- planilhas protegidas por senha não são lidas (BiffException) (não será resolvido)
		