package view;

import java.util.concurrent.Semaphore;

import controller.Servidor;

public class Main {

	public static void main(String[] args) {

		Semaphore semaforo = new Semaphore(1);

		for (int id = 1; id < 22; id++) {
			Thread tProcess = new Servidor(semaforo, id);
			tProcess.start();
		}

	}
}