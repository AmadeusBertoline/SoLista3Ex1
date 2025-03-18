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

	public void Ip() throws IOException {

		String osName = Os();

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

				// Identifica o ipv4 na linha

				if (linha.contains("IPv4") || linha.contains("inet")) {

					if (linha.contains("Endereço IPv4") || linha.contains("inet")) {

						ipv4 = linha.split(":")[1].trim();

					} else {

						if (linha.contains("inet")) {

							ipv4 = linha.split(" ")[1].trim();

						}

					}

				}

				// Se encontrou tanto o nome do adaptador quanto o IPv4, imprime e reinicia para
				// o próximo adaptador

				if (!nomeAdaptador.isEmpty() && !ipv4.isEmpty()) {

					System.out.println("Adapatador: " + nomeAdaptador);
					System.out.println("IPv4: " + ipv4);
					System.out.println();
					nomeAdaptador = "";
					ipv4 = "";

				}

			}

			process.waitFor(); // espera o processo terminar
			reader.close();

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	// O terceiro, chamado ping, que verifica o Sistema Operacional e, de acordo com
	// o S.O. e, faz a chamada
	// de ping em IPv4 com 10 iterações

	public void Ping() throws IOException {

		String osName = Os();

		try {

			String enderecoIp = "www.google.com.br";
			Process process = null;

			if (osName.toLowerCase().contains("windows")) {

				process = Runtime.getRuntime().exec("ping -n 10 " + enderecoIp);

			} else if (osName.toLowerCase().contains("nux") || osName.toLowerCase().contains("nix")
					|| osName.toLowerCase().contains("mac")) {

				process = Runtime.getRuntime().exec("ping -c 10 " + enderecoIp);

			}

			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String linha;
			String[] partes;

			while ((linha = reader.readLine()) != null) {

				// Exibe o tempo médio de cada teste de ping

				if (linha.contains("Average")) {

					partes = linha.split(" ");

					for (String parte : partes) {

						if (parte.contains("ms")) {

							System.out.println("Tempo médio: " + parte);

						}

					}

					if (linha.contains("rtt min/avg/max/mdev")) {

						partes = linha.split(" ");

						String tempoMedio = partes[3].split("/")[1];
						System.out.println("Tempo médio de ping: " + tempoMedio + " ms");

					}

				}

			}

			process.waitFor();
			reader.close();

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}
