package br.com.maisunifacisa;

public class Usuario {
	protected String nome;
	protected String email;
	
	public Usuario(String nome, String email) {
		this.nome = nome;
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Usuario [nome=" + nome + ", email=" + email + "]";
	}
	

}
