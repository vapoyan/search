package app.coinfo.test

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

object Helpers {

    @Throws(IOException::class)
    fun readFile(fileName: String) =
        javaClass.classLoader?.getResourceAsStream(fileName).use {
            val builder = StringBuilder()
            val reader = BufferedReader(InputStreamReader(it))
            var str: String? = reader.readLine()
            while (str != null) {
                builder.append(str)
                str = reader.readLine()
            }
            builder.toString()
        }
}