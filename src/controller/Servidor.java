package controller;

import java.util.concurrent.Semaphore;

public class Servidor extends Thread {

	private int id;
	private Semaphore semaforo;

	public Servidor(Semaphore semaforo, int id) {
		this.id = id;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		long id = getId();
		if (id % 3 == 1) {
			processos(id);
		} else if (id % 3 == 2) {
			processos2(id);
		} else {
			processos3(id);
		}
	}

	public void processos(long id) {

		for (int i = 0; i < 2; i++) {
			System.out.println("Thread#" + id + " fazendo cálculos.");
			int tempo = (int) ((Math.random() * 801) + 200);
			try {
				sleep(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			try {
				semaforo.acquire();
				System.out.println("Thread#" + id + " - Transação de BD iniciada");
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforo.release();
			}
			System.out.println("Thread#" + id + " - Transação de BD finalizada");
		}
	}

	public void processos2(long id) {

		for (int i = 0; i < 3; i++) {
			System.out.println("Thread#" + id + " fazendo cálculos.");
			int tempo = (int) ((Math.random() * 1001) + 500);
			try {
				sleep(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println("Thread#" + id + " - Transação de BD");
			try {
				semaforo.acquire();
				System.out.println("Thread#" + id + " - Transação de BD iniciada");
				sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforo.release();
			}
			System.out.println("Thread#" + id + " - Transação de BD finalizada");

		}
	}

	private void processos3(long id) {

		for (int i = 0; i < 3; i++) {
			System.out.println("Thread#" + id + " fazendo cálculos.");
			int tempo = (int) ((Math.random() * 1001) + 1000);
			try {
				sleep(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println("Thread#" + id + " - Transação de BD");
			try {
				semaforo.acquire();
				System.out.println("Thread#" + id + " - Transação de BD iniciada");
				sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforo.release();
			}
			System.out.println("Thread#" + id + " - Transação de BD finalizada");
		}
	}

}
