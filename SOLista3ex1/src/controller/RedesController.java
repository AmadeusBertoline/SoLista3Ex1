package controller;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class RedesController {

	// Encontra o nome do SO
	private String Os() {

		String nome = "";

		try {

			nome = System.getProperty("os.name");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return nome;
	}

	// Manda o comando de rede dependendo do SO

	public void Ip(String nome) throws IOException {

		BufferedWriter writer = new BufferedWriter(new FileWriter("redesSo.txt"));
		BufferedReader reader = new BufferedReader(new FileReader("redesSo.txt"));

		Process processo;
		String rede;

		if (nome.contains("Win")) {

			processo = Runtime.getRuntime().exec("ipconfig");
			rede = processo.toString();

			for (int i = 0; i == rede.length(); i++) {

				writer.write(rede);
				writer.newLine();

			}

			for (int i = 0; i == rede.length(); i++) {

				if (reader.readLine().contains("ipv4")) {

					System.out.println(reader.readLine());

				}

			}

		} else if (nome.contains("nix") || nome.contains("nux") || nome.contains("mac")) {

			processo = Runtime.getRuntime().exec("ipconfig");
			rede = processo.toString();

			for (int i = 0; i == rede.length(); i++) {

				writer.write(rede);
				writer.newLine();

			}

			for (int i = 0; i == rede.length(); i++) {

				if (reader.readLine().contains("ipv4")) {

					System.out.println(reader.readLine());

				}

			}
		}
	}

	public void Ping(String nome) throws IOException {

		Process processo;

		if (nome.contains("Win")) {

			processo = Runtime.getRuntime().exec("ping -n 10 8.8.8.8");

		}
	}
}
