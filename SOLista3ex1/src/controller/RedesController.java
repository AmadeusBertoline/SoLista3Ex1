package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RedesController {

	// Retorna o nome do sistema operacional
	private String Os() {

		return System.getProperty("os.name");

	}

	// Retorna informações de ipv4

	public void Ip(String osName) throws IOException {

		try {

			Process process = null;

			if (osName.toLowerCase().contains("windows")) {

				process = Runtime.getRuntime().exec("ipconfig");

			} else if (osName.toLowerCase().contains("linux") || osName.toLowerCase().contains("mac")) {

				process = Runtime.getRuntime().exec("ifconfig");

			} else {

				System.out.println("Sistema operacional não suportado");

			}

			// Lê a saída do comando
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String linha;
			String nomeAdaptador = "";
			String ipv4 = "";

			// Enquanto a linha que estiver sendo lida não for nula, o laço continua
			while ((linha = reader.readLine()) != null) {

				linha = linha.trim();// Remove espaços em branco no começo e no fim da string

				// Identifica o nome do adaptador, se a linha possuir um

				if (linha.contains("adaptador") || linha.contains("eth") || linha.contains("enp")
						|| linha.contains("wlan")) {

					nomeAdaptador = linha;

				}

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

	}
}
