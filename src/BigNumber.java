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
            if (digit1 >= digit2 + resta) {
                sumaDigits = digit1 - (digit2 + resta);
                resta = 0;
            }
            else{
                sumaDigits = digit1 + 10 - (digit2 + resta);
                resta = 1;
            }
            result = sumaDigits + result;
        }

        return new BigNumber(result);
    }

    // Multiplica
    BigNumber mult(BigNumber other) {
        String[] b1 = this.valor.split("");
        String[] b2 = other.valor.split("");
        String resultTotal = "0";
        String resultParcial = "";
        int sumaDigits;
        int resta = 0;
        int multiplicador = 0;
        for (int i = 0; i < b1.length; i++) {
            //multipliquem tots els digits de b2 per cada digit de b1
            for (int j = 0; j < b2.length; j++) {
                sumaDigits = Integer.parseInt(b1[b1.length - i - 1] ) * Integer.parseInt(b2[b2.length - j - 1]) + resta;
                resta = 0;
                while (sumaDigits > 9 && j < b2.length-1){
                    sumaDigits -= 10;
                    resta += 1;
                }
                resultParcial = sumaDigits + resultParcial;
            }
            for (int k = 0; k < multiplicador; k++) {
                resultParcial = resultParcial + 0;
            }
            multiplicador++;
            resultTotal = String.valueOf(new BigNumber(resultTotal).add(new BigNumber(resultParcial)));
            resultParcial = "";
        }
        return new BigNumber(resultTotal);
    }

    // Divideix
    BigNumber div(BigNumber other) {
        String b1 = this.valor;
        String b2 = other.valor;
        BigNumber dividendActual = new BigNumber(trobarDividend(b1, b2));

        //Bucle principal, mentre el divident sigui més gran que el divisor.
        int quocient;
        String result="";
        while (dividendActual.compareTo(other) != 1){
            quocient = trobarQuocient(dividendActual, other);
            result += quocient;
            dividendActual = new BigNumber(String.valueOf(quocient)).sub(dividendActual);
        }

        return new BigNumber(result);
    }

    //Multipliquem el divisor fins trobar el resultat més gran posible que sigui més petit que el dividend
    private int trobarQuocient(BigNumber dividendActual, BigNumber other) {
        for (int i = 1; i < 10; i++) {
            int num = 10-i;
            if (other.mult(new BigNumber(String.valueOf(num))).compareTo(dividendActual) == -1){
                return num;
            }
        }
        return 1;
    }

    private String trobarDividend(String b1, String b2) {
        int answer = -1;
        int incremental = 0;

        while (answer == -1){
            incremental++;
            answer = new BigNumber(b2).compareTo(new BigNumber(b1.substring(0,incremental)));
            //answer = b2.compareTo(b1.substring(0,incremental));
        }
        return b1.substring(0,incremental);
    }

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