package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RedesController {

	// Retorna o nome do sistema operacional
	private String Os() {

		return System.getProperty("os.name");

	}

	// Retorna informa��es de ipv4

	public void Ip(String osName) throws IOException {

		try {

			Process process = null;

			if (osName.toLowerCase().contains("windows")) {

				process = Runtime.getRuntime().exec("ipconfig");

			} else if (osName.toLowerCase().contains("linux") || osName.toLowerCase().contains("mac")) {

				process = Runtime.getRuntime().exec("ifconfig");

			} else {

				System.out.println("Sistema operacional n�o suportado");

			}

			// L� a sa�da do comando
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String linha;
			String nomeAdaptador = "";
			String ipv4 = "";

			// Enquanto a linha que estiver sendo lida n�o for nula, o la�o continua
			while ((linha = reader.readLine()) != null) {

				linha = linha.trim();// Remove espa�os em branco no come�o e no fim da string

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
