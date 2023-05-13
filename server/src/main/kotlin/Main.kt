import java.net.Socket

fun findSubstring(targetSubstring: String, inputChar: Char, currentIndex: Int): Int {
    val targetLength = targetSubstring.length

    if (inputChar == targetSubstring[currentIndex]) {
        val updatedIndex = currentIndex + 1
        if (updatedIndex == targetLength) {
            return -1 // Используем -1 в качестве индикатора, что подстрока найдена
        }
        return updatedIndex
    } else {
        return 0
    }
}




fun main() {
// адрес и порт сервера
    val serverAddress = "npm.mipt.ru"
    val serverPort = 9048
    Socket(serverAddress, serverPort).use { socket ->
        val inputStream = socket.getInputStream()
        val outputStream = socket.getOutputStream()

        // отправляем команду HELLO
        println("Отправляем HELLO")
        outputStream.write("HELLO\n".toByteArray())
        outputStream.flush()

        //ищем RES
        var currentIndex = 0
        var flag = false

        while (!flag) {
            val a = inputStream.read().toChar()
            currentIndex = findSubstring("RES", a, currentIndex)
            flag = currentIndex == -1
        }


        //следом за RES считываем длину строки
        val length = inputStream.read()

        val message = ByteArray(length);
        inputStream.read(message, 0, length)

        //считаем сумму
        print("Сумма строки в ответе: ")
        val sum = message.toUByteArray().sum()
        println(sum)

        // отправляем команду SUM с суммой байтов
        println("Отправляем сумму серверу")
        outputStream.write("SUM$sum\n".toByteArray())
        outputStream.flush()

        var currentIndex2 = 0
        var flag2 = false
        var OK = arrayOf<Char>() // Создание пустого массива строк, чтобы записать туда все подряд, а потом вывести два последних элемента "OK"

        while (!flag2) {
            val a = inputStream.read().toChar()
            OK += a
            currentIndex2 = findSubstring("OK", a, currentIndex2)
            flag2 = currentIndex2 == -1
        }
        print("Ответ от сервера: ")
        println(OK.takeLast(2).joinToString(""))
    }
}