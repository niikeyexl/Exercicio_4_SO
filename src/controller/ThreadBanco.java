package controller;

import java.util.concurrent.Semaphore;

public class ThreadBanco extends Thread{
	private Semaphore sem_sacar;
	private Semaphore sem_depos;
	public ThreadBanco(Semaphore sem_sacar, Semaphore sem_depos) {
		this.sem_sacar = sem_sacar;
		this.sem_depos = sem_depos;
	}
	
	@Override
	public void run() {
		long codigo = getId();
		double saldo = (8000 + Math.random() * 2000);
		double valor = (1800 + Math.random() * 200);
		if (Math.random() < 0.5) valor = -valor;
		transacoes(codigo, saldo, valor);
	}
	public void transacoes(long codigo, double saldo, double valor) {
		if (valor < 0) { //saque
			try {
				System.out.println("Aguardando saque.--->Codigo da conta: " + codigo);
				this.sem_sacar.acquire();
				System.out.println("Sacando dinheiro---> Codigo da conta: " + codigo);
				saldo = saldo + valor;
				sleep(2000);
				System.out.println("Saque efetuado--->Codigo da conta: " + codigo);
			} catch (Exception e) {
			} finally {
				this.sem_sacar.release();
			}
		}else if (valor > 0){ //deposito
			try {
				System.out.println("Aguardando Deposito --->Codigo da conta: " + codigo);
				this.sem_depos.acquire();
				System.out.println("Depositando dinheiro---> Codigo da conta: " + codigo);
				saldo = saldo + valor;
				sleep(2000);
				System.out.println("Deposito efetuado--->Codigo da conta: " + codigo);
			} catch (Exception e) {
			} finally {
				this.sem_depos.release();
			}
			
		}else {
			System.out.println("invalido");
		}
	}
	
}
