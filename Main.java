package br.com.maisunifacisa;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Dados dados = new Dados(); // Classe auxiliar para armazenar e manipular as atividades e usuários

        boolean rodando = true;
        while (rodando) {

            // Menu principal
            System.out.println("\n----- Sistema de Gerenciamento de Atividades -----");
            System.out.println("1. (Adicionar) ✅ Nova Tarefa ");
            System.out.println("2. (Pesquisar) 🔍 Tarefa");
            System.out.println("3. (Excluir) ❌ Tarefa");
            System.out.println("4. (Atualizar) ✏️ Tarefa");
            System.out.println("5. (Iniciar) ▶️ Tarefa");
            System.out.println("6. (Finalizar) ✅ Tarefa");
            System.out.println("7. (Listar) 📄 Tarefas");
            System.out.println("8. (Sair) 🚪 ");
            System.out.print("Escolha uma opção: ");

            int opcao = sc.nextInt();
            sc.nextLine(); // Limpa o buffer

            switch (opcao) {

                case 1: {
                    // Adição de nova tarefa
                    System.out.println("Digite o título da atividade: ");
                    String titulo = sc.nextLine();

                    System.out.println("Digite a descrição da atividade: ");
                    String descricao = sc.nextLine();

                    System.out.println("Digite o nome do responsável pela atividade: ");
                    String nome = sc.nextLine();

                    System.out.println("Digite o e-mail do responsável pela atividade: ");
                    String email = sc.nextLine();

                    Usuario usuario = new Usuario(nome, email);
                    dados.adicionarUsuario(usuario);

                    String temPrazo;
                    while (true) {
                        System.out.println("A atividade possui prazo? (s/n): ");
                        temPrazo = sc.nextLine().trim().toLowerCase();
                        if (temPrazo.equals("s") || temPrazo.equals("n")) break;
                        else System.out.println("Entrada inválida. Digite 's' ou 'n'.");
                    }

                    Tarefa novaAtividade;

                    // Criação da tarefa com ou sem prazo
                    if (temPrazo.equals("s")) {
                        System.out.print("Digite o prazo (formato YYYY-MM-DD): ");
                        String data = sc.nextLine();
                        try {
                            LocalDate prazo = LocalDate.parse(data);
                            novaAtividade = new TarefaComPrazo(titulo, descricao, usuario, prazo);
                        } catch (DateTimeParseException e) {
                            System.out.println("Formato de data inválido. Atividade não cadastrada.");
                            break;
                        }
                    } else {
                        novaAtividade = new TarefaSimples(titulo, descricao, usuario);
                    }

                    dados.adicionarAtividade(novaAtividade);
                    System.out.println("Atividade adicionada com sucesso!");
                }
                break;

                case 2: {
                    // Pesquisa por título
                    System.out.println("Digite o título da atividade que deseja pesquisar: ");
                    String tituloPesquisa = sc.nextLine();
                    boolean encontrado = false;
                    for (Tarefa atividade : dados.getAtividades()) {
                        if (atividade.getTitulo().trim().equalsIgnoreCase(tituloPesquisa.trim())) {
                            System.out.println("-------------------------------");
                            System.out.println("Atividade encontrada:");
                            atividade.exibirDetalhes();
                            encontrado = true;
                            break;
                        }
                    }
                    if (!encontrado) {
                        System.out.println("Nenhuma atividade encontrada com o título: " + tituloPesquisa);
                    }
                    break;
                }

                case 3: {
                    // Exclusão de tarefa
                    System.out.println("Digite o título da atividade que deseja excluir: ");
                    String tituloExclusao = sc.nextLine();
                    if (dados.getAtividades().isEmpty()) {
                        System.out.println("Lista de tarefas vazia.");
                    } else {
                        if (dados.buscarPeloTitulo(tituloExclusao)) {
                            System.out.println("Tarefa encontrada. Excluindo...");
                            dados.excluirTarefa(tituloExclusao);
                        }
                    }
                    break;
                }

                case 4: {
                    // Atualização de tarefa
                    System.out.println("Digite o título da atividade que deseja atualizar: ");
                    String tituloAtualizacao = sc.nextLine();
                    boolean encontrado = false;
                    for (Tarefa atividade : dados.getAtividades()) {
                        if (atividade.getTitulo().equalsIgnoreCase(tituloAtualizacao)) {
                            System.out.println("Atividade encontrada:");
                            System.out.println("Digite o novo título da atividade: ");
                            String novoTitulo = sc.nextLine();
                            if (!novoTitulo.isEmpty()) atividade.setTitulo(novoTitulo);

                            System.out.println("Digite a nova descrição da atividade: ");
                            String novaDescricao = sc.nextLine();
                            if (!novaDescricao.isEmpty()) atividade.setDescricao(novaDescricao);

                            System.out.print("Deseja trocar o usuário responsável? (s/n): ");
                            String trocarUsuario = sc.nextLine();
                            if (trocarUsuario.equalsIgnoreCase("s")) {
                                System.out.print("Novo nome do usuário: ");
                                String novoNome = sc.nextLine();
                                System.out.print("Novo e-mail do usuário: ");
                                String novoEmail = sc.nextLine();
                                Usuario novoUsuario = new Usuario(novoNome, novoEmail);
                                atividade.setUsuarioResponsavel(novoUsuario);
                            }

                            System.out.println("Atividade atualizada com sucesso!");
                            encontrado = true;
                            break;
                        }
                    }
                    if (!encontrado) System.out.println("Tarefa não encontrada.");
                    break;
                }

                case 5: {
                    // Iniciar tarefa
                    System.out.println("Digite o título da tarefa para iniciá-la: ");
                    String tituloIniciar = sc.nextLine();
                    if (dados.getAtividades().isEmpty()) {
                        System.out.println("Lista sem tarefas.");
                    } else if (dados.buscarPeloTitulo(tituloIniciar)) {
                        dados.iniciarTarefa(tituloIniciar);
                    }
                    break;
                }

                case 6: {
                    // Finalizar tarefa
                    System.out.println("Digite o título da tarefa para concluí-la: ");
                    String tituloFinalizar = sc.nextLine();
                    if (dados.getAtividades().isEmpty()) {
                        System.out.println("Lista sem tarefas.");
                    } else if (dados.buscarPeloTitulo(tituloFinalizar)) {
                        dados.finalizarTarefa(tituloFinalizar);
                    }
                    break;
                }

                case 7: {
                    // Listagem de tarefas com filtro
                    while (true) {
                        System.out.println("\n--- Submenu de Listagem ---");
                        System.out.println("1 - Listar Todas");
                        System.out.println("2 - Listar Apenas Pendentes");
                        System.out.println("3 - Listar Apenas Em Andamento");
                        System.out.println("4 - Listar Apenas Concluídas");
                        System.out.println("5 - Voltar ao Menu Principal");
                        System.out.print("Escolha uma opção: ");
                        int opcaoListar = sc.nextInt();
                        sc.nextLine();
                        if (opcaoListar == 5) break;

                        boolean encontrou = false;

                        for (Tarefa atividade : dados.getAtividades()) {
                            boolean exibir = false;
                            StatusTarefa status = atividade.getStatus();

                            switch (opcaoListar) {
                                case 1: exibir = true; break;
                                case 2: exibir = status == StatusTarefa.PENDENTE; break;
                                case 3: exibir = status == StatusTarefa.EM_ANDAMENTO; break;
                                case 4: exibir = status == StatusTarefa.CONCLUIDA; break;
                                default: System.out.println("Opção inválida."); continue;
                            }

                            if (exibir) {
                                encontrou = true;
                                System.out.println("\n-------------------------------");
                                atividade.exibirDetalhes();
                            }
                        }

                        if (!encontrou) {
                            System.out.println("Nenhuma atividade encontrada.");
                        }
                    }
                    break;
                }

                case 8: {
                    System.out.println("Saindo do sistema...");
                    sc.close();
                    rodando = false;
                    break;
                }

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
