package utils

object ResourceReader {
    fun read(path: String) = this::class.java.getResource(path).readText()
}