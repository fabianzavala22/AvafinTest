package com.example.avafintestfields.data.source.service

import android.content.Context
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody
import java.io.IOException

class AssetsInterceptor(val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val uri = chain.request().url.toUri().toString()
        val fileName = uri.substringAfterLast("/")

        return try {
            val inputStream = context.assets.open(fileName)
            val json = inputStream.bufferedReader().use { it.readText() }

            Response.Builder()
                .code(200)
                .protocol(Protocol.HTTP_1_0)
                .message(json)
                .body(ResponseBody.create("application/json".toMediaTypeOrNull(), json))
                .request(chain.request())
                .build()
        } catch (ex: IOException) {
            ex.printStackTrace()
            Response.Builder()
                .code(404)
                .protocol(Protocol.HTTP_1_0)
                .message("File not found")
                .body(ResponseBody.create("text/plain".toMediaTypeOrNull(), "File not found"))
                .request(chain.request())
                .build()
        }
    }
}