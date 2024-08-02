package `in`.blackant.betaapril

import android.content.Context
import android.util.Log
import com.android.volley.NetworkResponse
import com.android.volley.ParseError
import com.android.volley.Request
import com.android.volley.Request.Method
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.HttpHeaderParser
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import java.io.UnsupportedEncodingException
import java.nio.charset.Charset

class Api {
    companion object {
        private const val BASEURL = "https://betaapril.vercel.app/api"
        private var _queue: RequestQueue? = null
        private fun queue(context: Context): RequestQueue {
            if (_queue === null) _queue = Volley.newRequestQueue(context)
            return _queue as RequestQueue
        }

        fun <T> put(
            context: Context,
            table: String,
            body: String,
            clazz: Class<T>,
            listener: Response.Listener<T>,
        ) {
            val request = GsonRequest(
                Method.PUT,
                "$BASEURL/$table",
                clazz,
                listener,
                { r -> Log.d("PUT", r.toString()) },
            )
            queue(context).add(request)
        }

        fun <T> get(
            context: Context,
            table: String,
            query: Map<String, String>?,
            body: String?,
            clazz: Class<T>,
            listener: Response.Listener<T>,
        ) {
            val queryString = if (query === null) "" else query.asSequence()
                .joinToString("&", transform = { item -> "${item.key}=${item.value}" })
            val url = "$BASEURL/$table?$queryString"
            val request = GsonRequest(
                Method.GET,
                url,
                clazz,
                listener,
                { r -> Log.d("PUT", r.toString()) },
            )
            queue(context).add(request)
        }
    }

    class GsonRequest<T>(
        method: Int,
        private val url: String,
        private val clazz: Class<T>,
        private val listener: Response.Listener<T>,
        errorListener: Response.ErrorListener,
    ) : Request<T>(method, url, errorListener) {
        private val gson: Gson = Gson()

        override fun deliverResponse(response: T) = listener.onResponse(response)

        override fun parseNetworkResponse(response: NetworkResponse?): Response<T> {
            return try {
                val json = String(
                    response?.data ?: ByteArray(0),
                    Charset.forName(HttpHeaderParser.parseCharset(response?.headers))
                )
                Log.d(Api::class.simpleName, "$url\n$json")
                Response.success(
                    gson.fromJson(json, clazz),
                    HttpHeaderParser.parseCacheHeaders(response)
                )
            } catch (e: UnsupportedEncodingException) {
                Response.error(ParseError(e))
            } catch (e: JsonSyntaxException) {
                Response.error(ParseError(e))
            }
        }

    }
}