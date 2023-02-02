import java.util.Arrays;

class BigNumber {
    String valor;

    // Constructor 1
    public BigNumber(String s) {
        this.valor = removeZeros(s);
    }

    private String removeZeros(String s) {
        s = s.replaceFirst("^0+(?!$)", "");
        return s;
    }

    // Constructor 2
    public BigNumber(BigNumber b) {

    }

    // Suma
    BigNumber add(BigNumber other) {
        String[] b1 = this.valor.split("");
        String[] b2 = other.valor.split("");
        int maxLength = b1.length;

        //Si b1 és més llarg, s'allarga b2
        if (b1.length > b2.length){
            b2 = igualarArray(b2, maxLength);
        }
        //Si b2 és més llarg, s'allarga b1
        else if (b2.length > b1.length){
            maxLength = b2.length;
            b1 = igualarArray(b1, maxLength);
        }

        //Bucle per anar sumant els digits
        String result = "";
        int sumaDigits;
        int resta = 0;
        for (int i = 0; i < maxLength; i++) {
            sumaDigits = Integer.parseInt(b1[maxLength - i - 1]) + Integer.parseInt(b2[maxLength - i - 1]) + resta;
            resta = 0;
            while (sumaDigits > 9 && i < maxLength-1){
                sumaDigits -= 10;
                resta += 1;
            }
            result = sumaDigits + result;
        }

        return new BigNumber(result);
    }

    private String[] igualarArray(String[] b, int maxLength) {
        String[] newArr = new String[maxLength];
        Arrays.fill(newArr, "0");
        for(int i = 0; i < b.length; i++) {
            newArr[maxLength - 1 - i] = b[b.length - 1 - i];
        }
        return newArr;
    }

    // Resta
    BigNumber sub(BigNumber other) {
        String[] b1 = this.valor.split("");
        String[] b2 = other.valor.split("");
        int maxLength = b1.length;

        //Si b1 és més llarg, s'allarga b2
        if (b1.length > b2.length){
            b2 = igualarArray(b2, maxLength);
        }
        //Si b2 és més llarg, s'allarga b1
        else if (b2.length > b1.length){
            maxLength = b2.length;
            b1 = igualarArray(b1, maxLength);
        }

        //Bucle per anar restant els digits
        String result = "";
        int sumaDigits;
        int resta = 0;

        for (int i = 0; i < maxLength; i++) {
            int digit1 = Integer.parseInt(b1[maxLength - i - 1]);
            int digit2 = Integer.parseInt(b2[maxLength - i - 1]);
            if ((digit1 - resta) >= digit2) {
                sumaDigits = digit1 - resta - digit2;
                resta = 0;
            }
            else{
                sumaDigits = digit1 + 10 - digit2;
                resta = 1;
            }
            result = sumaDigits + result;
        }

        return new BigNumber(result);
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

    // Compara dos BigNumber. Torna 0 si són iguals, -1 si el primer és menor
    // i torna 1 si el segon és menor
    public int compareTo(BigNumber other) {
        //Ràpida comparació de equals per estalviar temps
        if (other.valor.equals(this.valor)) return 0;

        //Descartem que un valor sigui més gran que l'altre
        if (other.valor.length() < this.valor.length()) return 1;
        if (other.valor.length() > this.valor.length()) return -1;

        //Comparem el primer valor diferent a partir de l'esquerra
        for (int i = 0; i < this.valor.length(); i++) {
            if (other.valor.charAt(i) < this.valor.charAt(i)) return 1;
            if (other.valor.charAt(i) > this.valor.charAt(i)) return -1;
        }
        return 0;
    }

    // Torna un String representant el número
    public String toString() {
        return this.valor;
    }

    // Mira si dos objectes BigNumber són iguals
    @Override
    public boolean equals(Object other) {

        BigNumber b = (BigNumber) other;
        if (b.valor.equals(this.valor)) return true;
        return false;
    }
}