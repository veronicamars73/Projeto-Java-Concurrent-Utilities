import java.util.concurrent.Callable;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Classe para cálculo de termo do número de euler a interface
 * <code>Callable</code> foi implementada pela presente classe
 */
public class TermCalculator implements Callable<BigDecimal> {
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
	public BigDecimal call() {
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
    private BigDecimal term(int number) {
        return new BigDecimal(1).divide(new BigDecimal(factorial(number)), 50, RoundingMode.HALF_UP);
    }
}
