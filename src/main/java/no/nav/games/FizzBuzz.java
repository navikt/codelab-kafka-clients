package no.nav.games;

public class FizzBuzz {
    private FizzBuzz() {
    }

    public static String calculate(final Integer candidate) {

        if (isMultipleByThree(candidate) && isMultipleByFive(candidate)) {
            return "FizzBuzz";
        } else if (isMultipleByThree(candidate)) {
            return "Fizz";
        } else if (isMultipleByFive(candidate)) {
            return "Buzz";
        }

        return String.valueOf(candidate);
    }

    private static boolean isMultipleByFive(Integer candidate) {
        return candidate % 5 == 0;
    }

    private static boolean isMultipleByThree(Integer candidate) {
        return candidate % 3 == 0;
    }
}
