package view;

import java.util.concurrent.Semaphore;

import controller.ThreadBanco;

public class Main {

	public static void main(String[] args) {
		int qtdThread = 20;
		Semaphore sacar_farol = new Semaphore(1);
		Semaphore depos_farol = new Semaphore(1);
		for (int i = 0; i < qtdThread; i++) new ThreadBanco(sacar_farol, depos_farol).start();
	}

}
