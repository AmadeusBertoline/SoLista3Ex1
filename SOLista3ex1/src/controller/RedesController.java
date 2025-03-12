package controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RedesController {

	// identifica e retorna o nome do Sistema Operacional

	private String Os() {

		return System.getProperty("os.name");

	}

	// verifica o Sistema Operacional e, de acordo com o S.O., faz a chamada de
	// configuração de IP

	public void Ip(String osName) {

		try {

			Process process = null;

			if (osName.toLowerCase().contains("win")) {

				process = Runtime.getRuntime().exec("ipconfig");

			} else if (osName.toLowerCase().contains("linux") || osName.toLowerCase().contains("mac")) {
				process = Runtime.getRuntime().exec("ifconfig");
			} else {
				System.out.println("Sistema operacional não suportado.");
				return;
			}

			// Lê a saída do comando

			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

		} catch (Exception e) {

		}

	}

}
