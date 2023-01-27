class BigNumber {
    String valor;

    // Constructor 1
    public BigNumber(String s) {
        this.valor = s;
    }

    // Constructor 2
    public BigNumber(BigNumber b) {

    }

    // Suma
    BigNumber add(BigNumber other) {
        return other;
    }

    // Resta
    BigNumber sub(BigNumber other) {
        return other;
    }

    // Multiplica
    BigNumber mult(BigNumber other) {
        return other;
    }

    // Divideix
    BigNumber div(BigNumber other) {
        return other; }

    // Arrel quadrada
    BigNumber sqrt() {
        return null;
    }

    // Potència
    BigNumber power(int n) {
        return null;
    }

    // Factorial
    BigNumber factorial() {
        return null;
    }

    // MCD. Torna el Màxim comú divisor
    BigNumber mcd(BigNumber other) {
        return other;
    }

    // Compara dos BigNumber. Torna 0 si són iguals, -1
// si el primer és menor i torna 1 si el segon és menor
    public int compareTo(BigNumber other) {
        return 0;
    }

    // Torna un String representant el número
    public String toString() {
        return null;
    }

    // Mira si dos objectes BigNumber són iguals
    @Override
    public boolean equals(Object other) {
        BigNumber b = (BigNumber) other;
        if (b.valor.equals(this.valor)) return true;
        return false;
    }
}