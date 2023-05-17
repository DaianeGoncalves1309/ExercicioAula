package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Cadastro;

public class Controle {
	private static final String endereco = "C:\\TEMP\\cadastro.csv.csv";
	
	public Controle() {
		// TODO Auto-generated constructor stub
	}
	 private List<Cadastro> lerClientesDoArquivo() throws IOException {
	        List<Cadastro> clientes = new ArrayList<>();
	        Scanner scanner = new Scanner(new File(endereco));
	        
	while (scanner.hasNextLine()) {
	          String linha = scanner.nextLine();
	          String[] campos = linha.split(";");
	          String cpf = campos[0];
	          String nome = campos[1];
	          int idade = Integer.parseInt(campos[2]);
	          Double limiteCredito = Double.parseDouble(campos[3].replace(",", "."));
	          clientes.add(new Cadastro(cpf, nome, idade, limiteCredito));
	        }
	        scanner.close();
	        return clientes;
	    }
	 private void escreverClientesEmNovoArquivo(List<Cadastro> clientes, String nomeArquivo) throws IOException {
	        FileWriter writer = new FileWriter(new File("C:\\TEMP\\" + nomeArquivo));
	        StringBuffer buffer = new StringBuffer();
	        int contador = 0;
	        for (Cadastro cliente : clientes) {
	            String linha = cliente.getcpf() + ";" + cliente.getnome() + ";" + cliente.getidade() + ";" + cliente.getLcredito() + "\n";
	            buffer.append(linha);
	            contador++;
	        }
	        
	        writer.write(buffer.toString());
	        writer.close();
	    }

	    public void novoCadastro(int idadeMin, int idadeMax, Double limiteCredito) throws IOException {
	        List<Cadastro> clientes = lerClientesDoArquivo();
	        List<Cadastro> clientesFiltrados = new ArrayList<>();
	        for (Cadastro cliente : clientes) {
	            if (cliente.getidade() >= idadeMin && cliente.getidade() <= idadeMax && cliente.getLcredito() >= limiteCredito) {
	                clientesFiltrados.add(cliente);
	            }
	        }
	        escreverClientesEmNovoArquivo(clientesFiltrados, "Idade " + idadeMin + "-" + idadeMax + " limite " + limiteCredito + ".csv");
	    }
	}

