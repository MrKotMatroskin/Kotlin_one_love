import java.io.File
import java.lang.Appendable

data class SentenceData(
    val sentence: String,
    val wordCount: Int
)

data class WordData(
    val word: String,
    val numberOfUses: Float
)

fun Appendable.printStatistic(data: List<SentenceData>, wordData: List<WordData>){
    append("")
    data.forEach {
        append("Предложение: \"${it.sentence}\". Количество слов: ${it.wordCount}\n")
    }
    append("Общее количество предложений: ${data.size}\n")
    append("Общее количество слов: ${data.sumOf {it.wordCount}}\n")
    append("Частота появления каждого слова в тексте:\n")
    wordData.forEach {
        append("${it.word}: ${it.numberOfUses}\n")
    }
}

fun main() {
    // Открытие файла для чтения
    val inputFile = File("input.txt")
    val text = inputFile.readText()

    // Определение списка знаков препинания для обработки текста
    val punctuationMarks = listOf(".", "!", "?")

// Разделение текста на предложения и подсчет слов в каждом предложении
    val sentences = text.split(*punctuationMarks.toTypedArray())
        .map { it.trim() }
        .filter { it.isNotEmpty() }
        .map { sentence ->
            val words = sentence.split(" ")
            SentenceData(sentence, words.size)
        }

// Подсчет частоты появления каждого слова в тексте
    val wordFrequency = text.split(" ")
        .groupingBy { it.lowercase() }
        .eachCount()
        .map{ (word, frequency) ->
            WordData(word, frequency.toFloat())
        }

// Вывод результатов в консоль
    System.out.use{it.printStatistic(sentences, wordFrequency)}

// Запись результатов в файл
    val outputFile = File("output.txt")
    outputFile.writer().use{ it.printStatistic(sentences, wordFrequency)}
}
