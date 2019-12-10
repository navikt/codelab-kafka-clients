package no.nav.games

object FizzBuzz {
    fun calculate(candidate: Int): String {
        if (isMultipleByThree(candidate) && isMultipleByFive(candidate)) {
            return "FizzBuzz"
        } else if (isMultipleByThree(candidate)) {
            return "Fizz"
        } else if (isMultipleByFive(candidate)) {
            return "Buzz"
        }
        return candidate.toString()
    }

    private fun isMultipleByFive(candidate: Int): Boolean {
        return candidate % 5 == 0
    }

    private fun isMultipleByThree(candidate: Int): Boolean {
        return candidate % 3 == 0
    }
}