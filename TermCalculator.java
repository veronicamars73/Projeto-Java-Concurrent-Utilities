import java.util.concurrent.Callable;

/**
 * Classe para cálculo de termo do número de euler a interface
 * <code>Callable</code> foi implementada pela presente classe
 */
public class TermCalculator implements Callable<Double> {
	/** Parâmetro que corresponde ao número que é usado no cálculo do termo*/
	private int number;

	/**
     * Construtor da classe
	 * @param number Número do termo
	 */
	public TermCalculator(int number) {
		this.number = number;
	}

	/**
	 * Método sobrescrito de Callable
	 * @return termo do número de euler
	 */
	@Override
	public Double call() {
		return term(this.number);
	}

	/**
	 * Fatorial recursivo
	 * @param number número cujo o fatorial deve ser calculado
	 * @return fatorial do parâmetro
	 */
	private long factorial(int number) {
		if (number <= 1) {
			return 1;
		} 
        return number * factorial(number-1);
	}

    /**
	 * Cálculo do termo
	 * @param number número cujo o fatorial deve ser divisor do número 1
	 * @return termo do número de euler
	 */
    private Double term(int number) {
        return 1/(double)(factorial(number));
    }
}
