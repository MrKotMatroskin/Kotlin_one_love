import java.io.File

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
            Pair(sentence, words.size)
        }

// Подсчет общего количества предложений и общего количества слов в тексте
    val sentenceCount = sentences.size
    val wordCount = sentences.sumBy { it.second }

// Подсчет частоты появления каждого слова в тексте
    val wordFrequency = text.split(" ")
        .groupingBy { it.toLowerCase() }
        .eachCount()

// Вывод результатов в консоль
    sentences.forEach { (sentence, wordCount) ->
        println("Предложение: \"$sentence\". Количество слов: $wordCount")
    }
    println("Общее количество предложений: $sentenceCount")
    println("Общее количество слов: $wordCount")
    println("Частота появления каждого слова в тексте:")
    wordFrequency.forEach { (word, frequency) ->
        println("$word: $frequency")
    }

// Запись результатов в файл
    val outputFile = File("output.txt")
    outputFile.writeText("")
    sentences.forEach { (sentence, wordCount) ->
        outputFile.appendText("Предложение: \"$sentence\". Количество слов: $wordCount\n")
    }
    outputFile.appendText("Общее количество предложений: $sentenceCount\n")
    outputFile.appendText("Общее количество слов: $wordCount\n")
    outputFile.appendText("Частота появления каждого слова в тексте:\n")
    wordFrequency.forEach { (word, frequency) ->
        outputFile.appendText("$word: $frequency\n")
    }
}


