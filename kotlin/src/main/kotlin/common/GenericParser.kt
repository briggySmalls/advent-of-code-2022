package common

object GenericParser {
    fun <T>parseRows(input: String, fn: (row: String) -> T, splitOn: String = "\n"): List<T> =
        input.split(splitOn).map { fn(it) }
}