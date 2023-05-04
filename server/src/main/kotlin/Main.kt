import java.net.Socket
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
// адрес и порт сервера
    val serverAddress = "npm.mipt.ru"
    val serverPort = 9048
    Socket(serverAddress, serverPort).use { socket ->
        val inputStream = socket.getInputStream()
        val outputStream = socket.getOutputStream()

        // отправляем команду HELLO
        println("Отправляем HELLOW")
        outputStream.write("HELLO\n".toByteArray())
        outputStream.flush()

        //ищем RES, точнее просто проматываем на на 9 символов строку из потока, потому что я не смогу написать поиск подстроки RES
        println("Ищем RES")
        for (i in 0 .. 8){
            inputStream.read().toChar()
        }

        //следом за RES считываем длину строки
        val length = inputStream.read()

        val message = ByteArray(length);
        inputStream.read(message, 0, length)

        //считаем сумму
        print("Сумма строки: ")
        val sum = message.toUByteArray().sum()
        println(sum)

        // отправляем команду SUM с суммой байтов
        println("Отправляем сумму серверу")
        outputStream.write("SUM$sum\n".toByteArray())
        outputStream.flush()

        //Выводим ОК, аналогично поиску RES
        for (i in 0 .. 1){
            inputStream.read()
        }
        for (i in 0 .. 1){
            print(inputStream.read().toChar())
        }
    }
}