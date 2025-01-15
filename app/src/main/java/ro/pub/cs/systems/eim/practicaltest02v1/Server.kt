package ro.pub.cs.systems.eim.practicaltest02v1

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

object Server {
    private val localData = mutableMapOf<String, String>()
    private val mutex = Mutex()

    suspend fun handleGet(key: String): String {
        return mutex.withLock {
            localData[key]?.let { value ->
                """{"key":"$key","value":"$value"}"""
            } ?: """{"error":"Cheia nu există!"}"""
        }
    }

    suspend fun handlePost(key: String, value: String): String {
        return mutex.withLock {
            localData[key] = value
            """{"status":"Date salvate cu succes!"}"""
        }
    }

    suspend fun handleDelete(key: String): String {
        return mutex.withLock {
            if (localData.containsKey(key)) {
                localData.remove(key)
                """{"status":"Cheia a fost ștearsă cu succes!"}"""
            } else {
                """{"error":"Cheia nu există!"}"""
            }
        }
    }
}
