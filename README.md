# SGC - Sistema de Gestao de Contratos
Sistema para gerir os contratos de pagamentos de uma empresa

Cargos:
* Administrador
	- Dá permissão de acesso à novos usuários
	- Edita dados de usuário, exceto a senha
	- Deleta um usuário do sistema
* Gestor geral
	- Inicia novos contratos
	- Visualiza dados dos contratos e processos
* Gestor/Fiscal
	- Gestor: insere processos de pagamento em contratos sob sua gestão
	- Gestor: atualiza data de vencimento de um contrato
	- Fiscal: somente visualiza informações do contrato
	- Visualiza dados dos contratos e processos
* Tesoureiro
	- Faz o pagamento de processos em aberto
	- Visualiza dados dos contratos e processos que ainda não foram pagos
* Diretor
	- Visualiza dados dos contratos e processos
* Presidente
	- Visualiza dados dos contratos e processos
	
Classes de dados:
* Contrato
	Dados no java:
	- Número
	- Nome da empresa contratada
	- CNPJ da empresa contratada
	- Objeto, resumo do contrato
	- Número da portaria
	- id do registro no BD
	- Recurso: objeto Outro
	- Uso: objeto Outro
	- Fonte pagante: objeto Outro 
	- Gestor: objeto Usuario
	- Fiscal: objeto Usuario
	- Data de assinatura: DateTime
	- Data da ordem de serviço: DateTime
	- Data da garantia: DateTime
	- Data de vencimento do contrato: DateTime
	- Data de vencimento da garantia: DateTime
	- valor inicial: BigDecimal
	- valor aditivos: BigDecimal
		- não consta explicitamente no BD, é calculado dinamicamente quando os processos são adicionados ao contratos
	- valor total: BigDecimal
		- é a soma do valor inicial com o valor dos aditivos
		- não consta explicitamente no BD, é calculado dinamicamente quando os processos são adicionados ao contratos
	- lista com os processos: ArrayList<Processo>
	- marcação de aviso faltando 90 dias para vencimento do contrato
	- marcação de aviso faltando 60 dias para vencimento do contrato
	- marcação de aviso faltando 45 dias para vencimento do contrato
	
Classes DAO:
* ContratoDAO
	- insere um novo contrato
	- recupera os contratos de um gestor pela sua id. Ordem pode ser ser escolhida via parâmetro
	- recupera os contratos de um fiscal pela sua id. Ordem pode ser ser escolhida via parâmetro
	- recupera os n contratos mais recentes. N é a quantidade de contratos que a função vai retornar
	- recupera todos os contratos. Ordem pode ser ser escolhida via parâmetro
	- recupera um contrato com processos sem pagamento através da id
	- atualiza data de vencimento 
	- atualiza informações de aviso por email