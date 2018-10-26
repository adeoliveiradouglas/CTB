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
	* 1.1.2
		* M�s na planilha estava case-sensitivo (Resolvido)
		* Aviso de vencimento n�o chega ao gestor geral (Resolvido)
	* 1.2
		* Corrigido bug no cadastro de usu�rio
		* Corrigido bug na tela de Administrador do sistema 
		* Corrigido bug no pagamento de processos
		* Calcula saldo do contrato
		* Avisa sobre vencimento de contrato na tela de visualiza��o		
		* Tesoureiro pode colocar manualmente data de pagamento
		* Gestor geral de contratos v� os contratos com vencimento em 90 dias logo ap�s o login
		* Novo rodap�
		* Se um usu�rio n�o tem dois cargos, n�o aparece a op��o de troca no cabe�alho
		
		*bugs:
			* importa��o de planilhas parou de funcionar por causa do c�lculo de saldo
			* depois que Administrador edita um usuário, ao tentar novamente o sistema diz que foi deslogado, porém ao ir para tela inicial, o erro some.
		