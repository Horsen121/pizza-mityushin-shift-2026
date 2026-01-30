package com.example.network

import android.annotation.SuppressLint
import okhttp3.OkHttpClient
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

@SuppressLint("TrustAllX509TrustManager")
fun OkHttpClient.Builder.setSslSocketFactory(): OkHttpClient.Builder = apply {
	val x509Manager = object : X509TrustManager {

		override fun checkClientTrusted(chain: Array<X509Certificate?>?, authType: String?) {
		}

		override fun checkServerTrusted(chain: Array<X509Certificate?>?, authType: String?) {
		}

		override fun getAcceptedIssuers(): Array<X509Certificate?> {
			return arrayOf()
		}
	}
	val trustAllCerts: Array<TrustManager> = arrayOf(x509Manager)
	val sslContext = SSLContext.getInstance("SSL")
	sslContext.init(null, trustAllCerts, SecureRandom())

	sslSocketFactory(sslContext.socketFactory, x509Manager)
}