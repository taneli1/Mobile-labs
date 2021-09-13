package com.example.audiolab.data.file.`class`

import android.util.Log
import com.example.audiolab.data.file.`interface`.FileHandler
import java.io.*
import java.lang.Exception


abstract class ByteArrayHandler(
    override val fileName: String,
    override val fileDir: String
) : FileHandler<ByteArray> {
    private val tag = "BaseFileHandler"

    private var outputStream: FileOutputStream? = null
    private var bufferedOutputStream: BufferedOutputStream? = null
    private var dataOutputStream: DataOutputStream? = null

    private var inputStream: FileInputStream? = null
    private var bufferedInputStream: BufferedInputStream? = null
    private var dataInputStream: DataInputStream? = null


    final override fun openOutputStream(): Boolean {
        val file = File("$fileDir/$fileName")
        return try {
            outputStream = FileOutputStream(file)
            bufferedOutputStream = BufferedOutputStream(outputStream)
            dataOutputStream = DataOutputStream(bufferedOutputStream)
            true
        } catch (e: FileNotFoundException) {
            return caseError(e)
        }
    }

    final override fun closeOutputStream(): Boolean {
        return try {
            dataOutputStream?.close()
            true
        } catch (e: IOException) {
            return caseError(e)
        }
    }

    final override fun writeToFileStream(data: ByteArray): Boolean {
        return try {
            dataOutputStream?.write(data)
            true
        } catch (e: IOException) {
            return caseError(e)
        }
    }

    final override fun openInputStream(): FileInputStream? {
        val file = File("$fileDir/$fileName")
        try {
            inputStream = FileInputStream(file)
            bufferedInputStream = BufferedInputStream(inputStream)
            dataInputStream = DataInputStream(bufferedInputStream)
        } catch (e: FileNotFoundException) {
            Log.e(tag, e.message.toString())
        }
        return inputStream
    }

    final override fun closeInputStream(): Boolean {
        TODO("Not yet implemented")
    }

    private fun caseError(e: Exception): Boolean {
        Log.e(tag, e.message.toString())
        return false
    }
}

