package br.ufpr.modulos.principal;

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
    static void main() throws IOException, InterruptedException {
        Scanner scn = new Scanner(System.in);
            System.out.println("Entre com um CEP: ");
            int cep = scn.nextInt();

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        String url = "https://viacep.com.br/ws/"+cep+"/json/";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body();
        System.out.println(json);

        FileWriter escrita = new FileWriter("Ceps.json");
        escrita.write(gson.toJson(json));
        escrita.close();
    }
}
