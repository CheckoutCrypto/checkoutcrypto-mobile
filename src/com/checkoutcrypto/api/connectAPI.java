package com.checkoutcrypto.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.NameValuePair;
import android.annotation.SuppressLint;
import android.util.Log;
import com.checkoutcrypto.data.sync.addressReply;
import com.checkoutcrypto.data.sync.statusReply;
import com.google.gson.Gson;


public class connectAPI {
	
	ArrayList<NameValuePair> nameValuePairs;
 private static final String TAG = null;
	public static String charset = "UTF-8";
	
	public connectAPI() {
		
	}
	
	/*
	 *  Headers for our http connections between mobile client and server through json
	 */
	public static HttpURLConnection setHeader(URL url) throws IOException{
				
		HttpURLConnection urlConnection = null;
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("POST");
        urlConnection.setDoOutput(true);
		return urlConnection;
	}
	
	public String begin(String query) throws UnsupportedEncodingException{
		
		HttpURLConnection urlConnection = null;
		OutputStream outStream = null;
		String response = null;
		String statCode = "";  // api call
		int statusCode = 0; // this connection
		
		try {
			URL url = new URL("https://api.checkoutcrypto.com" + "?" + query);
			
			urlConnection = setHeader(url);
			outStream = urlConnection.getOutputStream();
			outStream.write(query.getBytes(charset));
			statusCode = urlConnection.getResponseCode();
			Log.v("RESPONSECODE", "code = "+statusCode);
			if (statusCode == 200) {

				InputStream responseStream = urlConnection.getInputStream();
				
				byte[] responseBytes = HttpUtils.getContent(responseStream).toByteArray();
				response = new String(responseBytes, "UTF-8");
				System.out.println(response);
				
				statusReply stat;
				stat = new Gson().fromJson(response, statusReply.class);
				System.out.println("parsed var = "+stat.response.status);
				statCode = stat.response.queue_id;
					
			} else { 
				response = HttpUtils.getErrorResponse(urlConnection);
				System.out.println(response);

			} 
		} catch (MalformedURLException e) {
			Log.e(TAG, e.getMessage(), e);
		} catch (IOException e) {
			Log.e(TAG, e.getMessage(), e);
		} finally {
			if (urlConnection != null) {
				urlConnection.disconnect();
			
			if (outStream != null) {
				try {
					outStream.close();
				} catch (IOException e) {
					Log.e(TAG, e.getMessage(), e);
				}
			}
		}
		}
		return statCode; 
	}
	
public String finishCall(String query) throws UnsupportedEncodingException{
		
		HttpURLConnection urlConnection = null;
		OutputStream outStream = null;
		String response = null;
		int statusCode = 0; // this connection
		String address = "";
		
		try {
			URL url = new URL("https://api.checkoutcrypto.com" + "?" + query);
			
			urlConnection = setHeader(url);
			outStream = urlConnection.getOutputStream();
			outStream.write(query.getBytes(charset));
			statusCode = urlConnection.getResponseCode();
			Log.v("RESPONSECODE", "code = "+statusCode);
			if (statusCode == 200) {

				InputStream responseStream = urlConnection.getInputStream();
				
				byte[] responseBytes = HttpUtils.getContent(responseStream).toByteArray();
				response = new String(responseBytes, "UTF-8");
				System.out.println(response);
					
					addressReply stat;
					stat = new Gson().fromJson(response, addressReply.class);
					System.out.println("parsed var = "+stat.response.status);
					address = stat.response.address; 
			} else { 
				response = HttpUtils.getErrorResponse(urlConnection);
				System.out.println(response);

			} 
		} catch (MalformedURLException e) {
			Log.e(TAG, e.getMessage(), e);
		} catch (IOException e) {
			Log.e(TAG, e.getMessage(), e);
		} finally {
			if (urlConnection != null) {
				urlConnection.disconnect();
			
			if (outStream != null) {
				try {
					outStream.close();
				} catch (IOException e) {
					Log.e(TAG, e.getMessage(), e);
				}
			}
		}
		}
		return address; 
	}
	
	   @SuppressLint("TrulyRandom")
	public static void disableCertificateValidation() {
	        // Create a trust manager that does not validate certificate chains
	        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {

				@Override
				public void checkClientTrusted(
						java.security.cert.X509Certificate[] chain,
						String authType) throws CertificateException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void checkServerTrusted(
						java.security.cert.X509Certificate[] chain,
						String authType) throws CertificateException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public X509Certificate[] getAcceptedIssuers() {
		                return new X509Certificate[0]; 
				}
	        }};

	        // Ignore differences between given hostname and certificate hostname
	        HostnameVerifier hv = new HostnameVerifier() {
	            @Override
	            public boolean verify(String hostname, SSLSession session) {
	                return true;
	            }
	        };

	        // Install the all-trusting trust manager
	        try {
	            SSLContext sc = SSLContext.getInstance("SSL");
	            sc.init(null, trustAllCerts, new SecureRandom());
	            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	            HttpsURLConnection.setDefaultHostnameVerifier(hv);
	        } catch (Exception e) {
	            // Do nothing
	        }
	    }
	   
	}
