# SGC - Sistema de Gestao de Contratos
Sistema para gerir os contratos de pagamentos de uma empresa

Cargos:
* Administrador
	- D� permiss�o de acesso � novos usu�rios
	- Edita dados de usu�rio, exceto a senha
	- Deleta um usu�rio do sistema
* Gestor geral
	- Inicia novos contratos
	- Visualiza dados dos contratos e processos
* Gestor/Fiscal
	- Gestor: insere processos de pagamento em contratos sob sua gest�o
	- Gestor: atualiza data de vencimento de um contrato
	- Fiscal: somente visualiza informa��es do contrato
	- Visualiza dados dos contratos e processos
* Tesoureiro
	- Faz o pagamento de processos em aberto
	- Visualiza dados dos contratos e processos que ainda n�o foram pagos
* Diretor
	- Visualiza dados dos contratos e processos
* Presidente
	- Visualiza dados dos contratos e processos
	
Classes de dados:
* Contrato
	Dados no java:
	- N�mero
	- Nome da empresa contratada
	- CNPJ da empresa contratada
	- Objeto, resumo do contrato
	- N�mero da portaria
	- id do registro no BD
	- Recurso: objeto Outro
	- Uso: objeto Outro
	- Fonte pagante: objeto Outro 
	- Gestor: objeto Usuario
	- Fiscal: objeto Usuario
	- Data de assinatura: DateTime
	- Data da ordem de servi�o: DateTime
	- Data da garantia: DateTime
	- Data de vencimento do contrato: DateTime
	- Data de vencimento da garantia: DateTime
	- valor inicial: BigDecimal
	- valor aditivos: BigDecimal
		- n�o consta explicitamente no BD, � calculado dinamicamente quando os processos s�o adicionados ao contratos
	- valor total: BigDecimal
		- � a soma do valor inicial com o valor dos aditivos
		- n�o consta explicitamente no BD, � calculado dinamicamente quando os processos s�o adicionados ao contratos
	- lista com os processos: ArrayList<Processo>
	- marca��o de aviso faltando 90 dias para vencimento do contrato
	- marca��o de aviso faltando 60 dias para vencimento do contrato
	- marca��o de aviso faltando 45 dias para vencimento do contrato
	
Classes DAO:
* ContratoDAO
	- insere um novo contrato
	- recupera os contratos de um gestor pela sua id. Ordem pode ser ser escolhida via par�metro
	- recupera os contratos de um fiscal pela sua id. Ordem pode ser ser escolhida via par�metro
	- recupera os n contratos mais recentes. N � a quantidade de contratos que a fun��o vai retornar
	- recupera todos os contratos. Ordem pode ser ser escolhida via par�metro
	- recupera um contrato com processos sem pagamento atrav�s da id
	- atualiza data de vencimento 
	- atualiza informa��es de aviso por email