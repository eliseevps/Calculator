import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner console = new Scanner(System.in);
        String operation = console.nextLine();
        System.out.println(calc(operation));
    }

    public static String calc (String input) throws IOException {
        int result = 0;
        StringBuilder output = new StringBuilder();
        String[] numRim = new String[] {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        String[] inputArray = input.split(" ");

        if (inputArray.length < 3)
            throw new IOException("throws Exception //т.к. строка не является математической операцией");
        else if (inputArray.length > 3)
            throw new IOException("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");

        int numberOne = 0, numberTwo = 0;
        boolean numberOneRim = false;
        boolean numberTwoRim = false;
        for (int i = 0; i < numRim.length; i++) {
            if (numRim[i].equals(inputArray[0])) {
                numberOneRim = true;
                numberOne = i + 1;
            }
            if (numRim[i].equals(inputArray[2])){
                numberTwoRim = true;
                numberTwo = i + 1;
            }
        }

        if (numberOneRim ^ numberTwoRim)
            throw new IOException("throws Exception //т.к. используются одновременно разные системы счисления");

        if (!numberOneRim & !numberTwoRim) {
            numberOne = Integer.parseInt(inputArray[0]);
            numberTwo = Integer.parseInt(inputArray[2]);
        }

        if (numberOne > 10 || numberTwo > 10)
            throw new IOException("throws Exception //т.к. одно из чисел больше 10. В рамках ТЗ числа от 1 до 10");

        if (numberOne < 0 || numberTwo < 0)
            throw new IOException("throws Exception //т.к. одно из чисел меньше 1. В рамках ТЗ числа от 0 до 10");

        String operation = inputArray[1];
        switch(operation){
            case ("+"):
                result = numberOne + numberTwo;
                break;
            case ("-"):
                if (numberOneRim & numberTwoRim & (numberOne < numberTwo))
                    throw new IOException("throws Exception //т.к. в римской системе нет отрицательных чисел");
                else
                    result = numberOne - numberTwo;
                break;
            case ("/"):
                if (numberTwo < 1)
                    throw new IOException("throws Exception //т.к. деление на ноль");
                result = numberOne / numberTwo;
                break;
            case ("*"):
                result = numberOne * numberTwo;
                break;
        }

        if (numberOneRim & numberTwoRim) {
            if (result == 100) {
                output = new StringBuilder("C");
            } else if (result == 90) {
                output = new StringBuilder("XC");
            } else if (result > 89 & result < 100) {
                result -= 90;
                output.append("XC");
                output.append(numRim[result - 1]);
            } else if (result == 50) {
                output = new StringBuilder("L");
            } else if (result > 50 & result < 90) {
                result %= 50;
                output.append("L");
                while (result > 10) {
                    output.append("X");
                    result -= 10;
                }
                output.append(numRim[result - 1]);
            } else if (result == 40) {
                output = new StringBuilder("XL");
            } else if (result < 50 & result > 39) {
                result -= 40;
                output.append("XL");
                output.append(numRim[result - 1]);
            } else if (result > 10 & result < 40) {
                while (result > 10) {
                    output.append("X");
                    result -= 10;
                }
                output.append(numRim[result - 1]);
            } else if (result == 10) {
                output = new StringBuilder("X");
            } else if (result < 10) {
                output.append(numRim[result - 1]);
            }
            return output.toString();
        } else return String.valueOf(result);
    }
}
