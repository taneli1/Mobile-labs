package com.example.audiolab.data.file.`interface`

import java.io.InputStream

/**
 * Interface for File Handlers.
 */
interface FileHandler<T> : FileProperties {
    fun openOutputStream(): Boolean

    fun closeOutputStream(): Boolean

    fun writeToFileStream(data: T): Boolean

    fun openInputStream(): InputStream?

    fun closeInputStream(): Boolean
}