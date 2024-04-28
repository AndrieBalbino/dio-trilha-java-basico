package edu.dio.andrie;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

/**
 * 
 * @author Andrie
 * @since 28/04/2024
 *
 */
public class ContaTerminal {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Olá, vamos iniciar o seu cadastro. Por favor, informe os dados a seguir!!!");

		int numeroAgencia = solicitarNumeroAgencia(scanner);
		String agencia = solicitarAgencia(scanner);
		String nomeCliente = solicitarNome(scanner);
		BigDecimal saldo = solicitarSaldoCliente(scanner);

		mensagemCadastroRealizadoComSucesso(numeroAgencia, agencia, nomeCliente, saldo);

		scanner.close();
	}

	private static int solicitarNumeroAgencia(Scanner scanner) {
		while (true) {
			System.out.println("Digite o número da agência:");
			if (scanner.hasNextInt()) {
				return scanner.nextInt();
			} else {
				System.out.println("Por favor, digite um número válido.");
				scanner.next(); // Limpa o buffer
			}
		}
	}

	private static String solicitarAgencia(Scanner scanner) {
		System.out.println("Digite o nome da agência:");
		return scanner.next();
	}

	private static String solicitarNome(Scanner scanner) {
		String nomeCliente;
		while (true) {
			System.out.println("Digite o nome do cliente:");
			nomeCliente = scanner.next();
			if (!nomeCliente.isEmpty()) {
				break;
			} else {
				System.out.println("Por favor, digite um nome válido.");
			}
		}

		return nomeCliente;
	}

	private static BigDecimal solicitarSaldoCliente(Scanner scanner) {
		BigDecimal saldo = BigDecimal.ZERO;
		while (true) {
			System.out.println("Digite o saldo:");
			try {
				saldo = new BigDecimal(scanner.next());
				if (saldo.compareTo(BigDecimal.ZERO) >= 0) {
					break;
				} else {
					System.out.println("Por favor, digite um valor não negativo para o saldo.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Por favor, digite um valor numérico válido para o saldo.");
			}
		}

		return saldo;
	}

	private static void mensagemCadastroRealizadoComSucesso(int numeroAgencia, String agencia, String nomeCliente,
			BigDecimal saldo) {
		String mensagem = "Olá " + nomeCliente.concat(", obrigado por criar uma conta em nosso banco, sua agência é "
				+ agencia.concat(", conta " + numeroAgencia) + " e seu saldo " + getSaldoFormatado(saldo)
				+ " já está disponível para saque.");

		System.out.println(mensagem);
	}

	private static String getSaldoFormatado(BigDecimal saldo) {
		// Formatando o saldo para o formato "R$ 99,99"
		NumberFormat formatoSaldo = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		return formatoSaldo.format(saldo);
	}

}
