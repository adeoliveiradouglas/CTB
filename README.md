# SGC - Sistema de Gestao de Contratos
Sistema para gerir os contratos de pagamentos de uma empresa
	
* Log mudanças
	* 1.1
		* Suporte para planilhas .xlsx
		* Identifica quem pagou o processo
		* Usuários podem ter 2 cargos. Exemplo: um gestor pode ser tesoureiro ao mesmo tempo
		* Novos cargos para visualização de dados: presidente e diretor
		* Avisa aos responsáveis sobre vencimento dos contratos por email
		* Tesoureiro agora também vê os dados dos processos em aberto 
	* 1.1.2
		* Mês na planilha estava case-sensitivo (Resolvido)
		* Aviso de vencimento náo chega ao gestor geral (Resolvido)
	* 1.2
		* Corrigido bug no cadastro de usuário
		* Corrigido bug na tela de Administrador do sistema 
		* Corrigido bug no pagamento de processos
		* Calcula saldo do contrato
		* Avisa sobre vencimento de contrato na tela de visualização		
		* Tesoureiro pode colocar manualmente data de pagamento
		* Gestor geral de contratos vê os contratos com vencimento em 90 dias logo apás o login
		* Novo rodapá
		* Se um usuário não tem dois cargos, não aparece a opção de troca no cabeçalho
	* 1.3
		* Mudanças visuais
	* 1.3.2
		* Corrigido bug de saldo ao inserir dados fora de ordem
		
	* bugs:
			* importação de planilhas parou de funcionar por causa do cálculo de saldo
			* depois que Administrador edita um usuário, ao tentar novamente o sistema diz que foi deslogado, porÃ©m ao ir para tela inicial, o erro some.
		