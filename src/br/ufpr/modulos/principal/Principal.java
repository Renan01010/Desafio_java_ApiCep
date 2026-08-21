package br.ufpr.modulos.principal;

import br.ufpr.modulos.calculo.ConsultaCep;
import br.ufpr.modulos.calculo.Endereco;
import br.ufpr.modulos.calculo.GeradorArquivo;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Principal {
    static void main() throws IOException {
        Scanner scn = new Scanner(System.in);

        ConsultaCep cep = new ConsultaCep();
        while (true) {
            System.out.println("Digite um CEP ou 0 para encerrar o programa: ");
            try {
                String leitura = scn.nextLine();

                if(leitura.equalsIgnoreCase("0")){
                    System.out.println("Finalizando aplicação!...");
                    break;
                }

                Endereco novoEndereco = cep.consultaCep(leitura);
                System.out.println(novoEndereco);
                GeradorArquivo gerador = new GeradorArquivo();
                gerador.salvaJson(novoEndereco);

            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
