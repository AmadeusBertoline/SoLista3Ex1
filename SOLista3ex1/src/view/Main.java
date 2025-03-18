package view;

import controller.*;

public class Main {

	public static void main(String[] args) {

		RedesController redes = new RedesController();

		try {

			redes.Ip();
			redes.Ping();

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}
