package br.com.maisunifacisa;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Dados dados = new Dados(); // gerencia atividades e usuários

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

            int opcao = -1;
            try {
                opcao = sc.nextInt();
                sc.nextLine(); // limpa buffer
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                sc.nextLine();
                continue;
            }

            switch (opcao) {
                case 1: {
                    // Adição de nova tarefa
                    System.out.print("Digite o título da atividade: ");
                    String titulo = sc.nextLine().trim();
                    if (titulo.isEmpty()) {
                        System.out.println("Título não pode estar vazio.");
                        break;
                    }

                    System.out.print("Digite a descrição da atividade: ");
                    String descricao = sc.nextLine().trim();
                    if (descricao.isEmpty()) {
                        System.out.println("Descrição não pode estar vazia.");
                        break;
                    }

                    System.out.print("Digite o nome do responsável pela atividade: ");
                    String nome = sc.nextLine().trim();

                    System.out.print("Digite o e-mail do responsável pela atividade: ");
                    String email = sc.nextLine().trim();
                    if (email.isEmpty()) {
                        System.out.println("E-mail não pode estar vazio.");
                        break;
                    }

                    Usuario usuarioResponsavel = new Usuario(nome, email);
                   
                    String temPrazo;
                    while (true) {
                        System.out.print("A atividade possui prazo? (s/n): ");
                        temPrazo = sc.nextLine().trim().toLowerCase();
                        if (temPrazo.equals("s") || temPrazo.equals("n")) {
                            break;
                        }
                        System.out.println("Entrada inválida. Digite 's' ou 'n'.");
                    }

                    Tarefa novaAtividade;
                    if (temPrazo.equals("s")) {
                        System.out.print("Digite o prazo (YYYY-MM-DD): ");
                        String data = sc.nextLine();
                        try {
                            LocalDate prazo = LocalDate.parse(data);
                            novaAtividade = new TarefaComPrazo(titulo, descricao, usuarioResponsavel, prazo);
                        } catch (DateTimeParseException e) {
                            System.out.println("Formato de data inválido. Atividade não cadastrada.");
                            break;
                        }
                    } else {
                        novaAtividade = new TarefaSimples(titulo, descricao, usuarioResponsavel);
                    }

                    dados.adicionarAtividade(novaAtividade);
                    System.out.println("Atividade adicionada com sucesso!");
                } break;

                case 2: {
                    // Pesquisa por título
                	while (true) {
                	
					System.out.println("\n--- Submenu de Pesquisa ---");
					System.out.println("1 - Pesquisar por Título");
					System.out.println("2 - Pesquisar por Usuário");
					System.out.println("3 - Voltar ao Menu Principal");
					System.out.print("Escolha uma opção: ");

					int opcaoPesquisa = -1;
					try {
						opcaoPesquisa = sc.nextInt();
						sc.nextLine();
					} catch (InputMismatchException e) {
						System.out.println("Entrada inválida. Digite um número.");
						sc.nextLine();
						continue;
					}
					if (opcaoPesquisa == 3) {
					    break;
					}
					switch (opcaoPesquisa) {
                		
					case 1: {	
                    System.out.print("Digite o título da atividade que deseja pesquisar: ");
                    String tituloPesquisa = sc.nextLine().trim();
                    boolean encontrado = false;
                    for (Tarefa atividade : dados.getAtividades()) {
                        if (atividade.getTitulo().trim().equalsIgnoreCase(tituloPesquisa)) {
                            System.out.println("-------------------------------");
                            System.out.println("Atividade encontrada.");
                            atividade.exibirDetalhes();
                            encontrado = true;
                            break;
                        }
                    }
                    if (!encontrado) {
                        System.out.println("Nenhuma atividade encontrada com o título: " + tituloPesquisa);
                    }
                } break;
				  case 2: {
					  System.out.print("Digite o nome ou e-mail do usuário: ");
		                String chave = sc.nextLine().trim().toLowerCase();
		                boolean encontrou = false;

		                for (Tarefa atividade : dados.getAtividades()) {
		                    Usuario u = atividade.getUsuarioResponsavel();
		                    if (u.getNome().toLowerCase().contains(chave) || u.getEmail().toLowerCase().contains(chave)) {
		                        if (!encontrou) {
		                            System.out.println("\nTarefas encontradas para o usuário:");
		                            encontrou = true;
		                        }
		                        System.out.println("\n-------------------------------");
		                        atividade.exibirDetalhes();
		                    }
		                }

		                if (!encontrou) {
		                    System.out.println("Nenhuma tarefa encontrada para o usuário informado.");
		                }
		            } break;
				  case 3:
					  return;

		            default:
		                System.out.println("Opção inválida. Tente novamente.");
					}
						
					}

				} break;

                case 3: {
                    // Exclusão de tarefa
                    System.out.print("Digite o título da atividade que deseja excluir: ");
                    String tituloExclusao = sc.nextLine().trim();
                    if (dados.getAtividades().isEmpty()) {
                        System.out.println("Lista de tarefas vazia.");
                    } else if (dados.buscarPeloTitulo(tituloExclusao)) {
                        System.out.println("Tarefa encontrada. Excluindo...");
                        dados.excluirTarefa(tituloExclusao);
                    } else {
                        System.out.println("Tarefa não encontrada. Nenhuma exclusão realizada.");
                    }
                } break;

                case 4: {
                    // Atualização de tarefa
                    System.out.print("Digite o título da atividade que deseja atualizar: ");
                    String tituloAtualizacao = sc.nextLine().trim();
                    boolean encontrado = false;
                    for (Tarefa atividade : dados.getAtividades()) {
                        if (atividade.getTitulo().trim().equalsIgnoreCase(tituloAtualizacao)) {
                            System.out.println("Atividade encontrada.");

                            System.out.print("Digite o novo título da atividade: ");
                            String novoTitulo = sc.nextLine().trim();
                            if (!novoTitulo.isEmpty()) {
                                atividade.setTitulo(novoTitulo);
                            }

                            System.out.print("Digite a nova descrição da atividade: ");
                            String novaDescricao = sc.nextLine().trim();
                            if (!novaDescricao.isEmpty()) {
                                atividade.setDescricao(novaDescricao);
                            }

                            String trocarUsuario;
                            do {
                                System.out.print("Deseja trocar as informações do usuário responsável? (s/n): ");
                                trocarUsuario = sc.nextLine().trim().toLowerCase();
                                if (!trocarUsuario.equals("s") && !trocarUsuario.equals("n")) {
                                    System.out.println("Resposta inválida! Digite 's' ou 'n'.");
                                }
                            } while (!trocarUsuario.equals("s") && !trocarUsuario.equals("n"));

                            if (trocarUsuario.equals("s")) {
                                System.out.print("Novo nome do usuário: ");
                                String novoNome = sc.nextLine().trim();
                                System.out.print("Novo e-mail do usuário: ");
                                String novoEmail = sc.nextLine().trim();
                                Usuario novoUsuario = new Usuario(novoNome, novoEmail);
                                atividade.setUsuarioResponsavel(novoUsuario);
                            }

                            System.out.println("Atividade atualizada com sucesso!");
                            encontrado = true;
                            break;
                        }
                    }
                    if (!encontrado) {
                        System.out.println("Tarefa não encontrada.");
                    }
                } break;

                case 5: {
                    // Iniciar tarefa
                    System.out.print("Digite o título da tarefa para iniciá-la: ");
                    String tituloIniciar = sc.nextLine().trim();
                    if (dados.getAtividades().isEmpty()) {
                        System.out.println("Lista sem tarefas.");
                    } else if (dados.buscarPeloTitulo(tituloIniciar)) {
                        dados.iniciarTarefa(tituloIniciar);
                        System.out.println("Atividade iniciada! Status: Em andamento");
                    } else {
                        System.out.println("Tarefa não encontrada.");
                    }
                } break;

                case 6: {
                    // Finalizar tarefa
                    System.out.print("Digite o título da tarefa para concluí-la: ");
                    String tituloFinalizar = sc.nextLine().trim();
                    if (dados.getAtividades().isEmpty()) {
                        System.out.println("Lista sem tarefas.");
                    } else if (dados.buscarPeloTitulo(tituloFinalizar)) {
                        dados.finalizarTarefa(tituloFinalizar);
                        System.out.println("Atividade concluída com sucesso! Status: Concluída.");
                    } else {
                        System.out.println("Tarefa não encontrada.");
                    }
                } break;

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

                        int opcaoListar = -1;
                        try {
                            opcaoListar = sc.nextInt();
                            sc.nextLine();
                        } catch (InputMismatchException e) {
                            System.out.println("Entrada inválida. Digite um número.");
                            sc.nextLine();
                            continue;
                        }

                        if (opcaoListar == 5) {
                            break;
                        }

                        boolean encontrou = false;
                        for (Tarefa atividade : dados.getAtividades()) {
                            boolean exibir = switch (opcaoListar) {
                                case 1 -> true;
                                case 2 -> atividade.getStatus() == StatusTarefa.PENDENTE;
                                case 3 -> atividade.getStatus() == StatusTarefa.EM_ANDAMENTO;
                                case 4 -> atividade.getStatus() == StatusTarefa.CONCLUIDA;
                                default -> {
                                    System.out.println("Opção inválida.");
                                    yield false;
                                }
                            };
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
                } break;

                case 8: {
                    System.out.println("Saindo do sistema...");
                    sc.close();
                    rodando = false;
                } break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

