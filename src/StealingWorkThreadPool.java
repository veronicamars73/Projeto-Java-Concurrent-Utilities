import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.lang.Thread;

/**
 * Classe que realiza o cálculo do número de euler de modo
 * concorrente, calculando cada termo paralelamente. Nesta classe,
 * usaremos a thread pool em sua possibilidade work stealing, isto é,
 * o WorkStealingThreadPool.
 *
 */
public class StealingWorkThreadPool {
    /**
	 * Método main
	 * @param args argumentos da linha de comando que devem ser passados
     * como: numero_de_termos.
     * Exemplo (30 termos): <code>java program.java 30</code>
	 */
	public static void main(String[] args) {
		if(args.length != 1){
            System.out.println("É necessário que sejam fornecidos os argumentos requiridos para a execução do programa da forma indicada.");
        }

		int num_termos = Integer.parseInt(args[0]);


		ExecutorService executor =
			Executors.newWorkStealingPool();
		
		List<Future<BigDecimal>> results = new ArrayList<>();
        BigDecimal euler_numero = new BigDecimal(0.0);

		for (int x = 0; x < num_termos; x++) {
			Callable<BigDecimal> calculator = new TermCalculator(x);
			Future<BigDecimal> factorial = executor.submit(calculator);
			results.add(factorial);
		}
		System.out.println("Número de Threads ativas: "+ Thread.activeCount());
		try {
			for (Future<BigDecimal> result : results) {
				euler_numero = euler_numero.add(result.get());
			}
		} catch (ExecutionException | InterruptedException e) {
			e.printStackTrace();
		} finally {
			executor.shutdown();
		}
        System.out.println("A aproximação do número de euler é: "+ euler_numero);
	}
}
