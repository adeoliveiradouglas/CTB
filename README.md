# SGC - Sistema de Gestao de Contratos
Sistema para gerir os contratos de pagamentos de uma empresa
	
* Log vers�es
	* 1.1
		* Suporte para planilhas .xlsx
		* Identifica quem pagou o processo
		* Usu�rios podem ter 2 cargos. Exemplo: um gestor pode ser tesoureiro ao mesmo tempo
		* Novos cargos para visualiza��o de dados: presidente e diretor
		* Avisa aos respons�veis sobre vencimento dos contratos por email
		* Tesoureiro agora tamb�m v� os dados dos processos em aberto 
		
* Bugs encontrados:
	- planilhas devem estar no formato .xls 97- 2003 (Resolvido)
	- planilhas protegidas por senha n�o s�o lidas (BiffException) (n�o ser� resolvido)
		