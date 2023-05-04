import java.net.Socket
import java.io.InputStream
import java.io.OutputStream

fun main() {
    // адрес и порт сервера
    val serverAddress = "npm.mipt.ru"
    val serverPort = 9048

    // создаем сокет для подключения к серверу
    val socket = Socket(serverAddress, serverPort)
    val inputStream: InputStream = socket.getInputStream()
    val outputStream: OutputStream = socket.getOutputStream()

    // отправляем команду HELLO
    print("отправляем команду халлоу")
    outputStream.write("HELLO\n".toByteArray())

    // получаем ответ с бинарной строкой
    println("Получаем строку от сервера")
    val binaryString = waitForBinaryString(inputStream)
    println(binaryString)

    // вычисляем сумму байтов ответа
    println("Вычисляем сумму")
    val sum = binaryString.sumOf { it.toInt() }
    println(sum)

    // отправляем команду SUM с суммой байтов
    println("отправляем сумму")
    outputStream.write("SUM${sum.toString()}\n".toByteArray())

    // получаем ответ OK
    println("Получаем ответ")
    val ok = waitForSubstring(inputStream, "OK")
    println(ok)

    // закрываем соединение
    socket.close()
}

// функция для чтения бинарной строки из потока ввода
fun waitForBinaryString(inputStream: InputStream): ByteArray {
    val lengthByte = inputStream.read().toByte()
    val byteArray = ByteArray(lengthByte.toInt())
    inputStream.read(byteArray)
    waitForSubstring(inputStream, "\n") // пропускаем символ перевода строки
    return byteArray
}

// функция для чтения строки, содержащей заданную подстроку, из потока ввода
fun waitForSubstring(inputStream: InputStream, substring: String): String {
    var buffer = ""
    while (buffer.endsWith(substring) == true) {
        buffer += inputStream.read().toChar()
    }
    return buffer
}
