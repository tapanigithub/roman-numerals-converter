import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        RomanConverter converter = new RomanConverter();

        while(true) {
            try {
                System.out.print("Please enter the roman numeral you wish to convert(q to Quit): ");
                String romanString = scanner.nextLine();
                if (romanString.equals("q")) break;

                int result = converter.convert(romanString);
                System.out.println(romanString.toUpperCase() + " as integer: " + result);
            }
            catch (IllegalArgumentException e) {
                System.out.println(e);
            }

        }
    }
}
