📌 Sistema de Gerenciamento de Atividades


Este é um projeto de estudo em Programação Orientada a Objetos (POO) em Java. O sistema permite gerenciar tarefas atribuídas a usuários, com funcionalidades como adicionar, atualizar, iniciar, finalizar, pesquisar e listar atividades com ou sem prazo.

💡 Funcionalidades
✅ Adicionar nova atividade

🔍 Pesquisar atividade pelo título

❌ Excluir atividade

🔄 Atualizar atividade (título, descrição, usuário)

▶️ Iniciar atividade

⏹ Finalizar atividade

📋 Listar atividades com filtros de status

Todas

Pendentes

Em andamento

Concluídas

🧱 Estrutura do Projeto
O projeto segue os princípios da Programação Orientada a Objetos, com as seguintes classes principais:

Classe	Descrição
Main	Classe principal com o menu de interação.
Atividade	Classe abstrata que define os atributos e comportamentos básicos de uma tarefa.
TarefaSimples	Subclasse de Atividade para tarefas sem prazo.
TarefaComPrazo	Subclasse de Atividade com data limite e verificação de atraso.
Usuario	Representa o responsável por uma atividade.
StatusAtividade	Enum com os status possíveis: PENDENTE, EM_ANDAMENTO, CONCLUIDA.

🛠️ Tecnologias Utilizadas
Linguagem: Java

IDE recomendada: Eclipse, IntelliJ IDEA ou VS Code com Extensões Java

Java versão: Java 8 ou superior

▶️ Como Executar
Clone o repositório:

bash
Copiar
Editar
git clone https://github.com/seuusuario/nome-do-repo.git
Navegue até a pasta do projeto e abra com sua IDE Java.

Compile e execute a classe Main.

Utilize o menu interativo para gerenciar atividades.

📌 Exemplo de Uso
bash
Copiar
Editar
--- Sistema de Gerenciamento de Atividades ---
1. Adicionar Nova Atividade
2. Pesquisar Tarefa
3. Excluir Tarefa
...
Escolha uma opção: 1
Digite o título da atividade: Estudar POO
...
Atividade adicionada com sucesso!
📚 Conceitos Aplicados
Abstração

Herança

Polimorfismo

Encapsulamento

Manipulação de listas (ArrayList)

Uso de enums

Tratamento de exceções (try-catch)

Interação com o usuário via console

👨‍💻 Autor
Desenvolvido pelo grupo [Gabriel Lacerda,Miguel Vianna,Samires do Carmo,Erick Monteiro]

Projeto acadêmico para fins de aprendizado em Java/POO
