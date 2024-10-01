package com.example.baith1.downloadpage;

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadWebPageTask extends AsyncTask<String,Void,String> {

    private TextView textView;
    @Override
    protected String doInBackground(String... urls) {
        StringBuilder response= new StringBuilder();
        for (String urlStr:urls){
            try {
                // Tạo URL từ chuỗi URL
                URL url = new URL(urlStr);
                // Mở kết nối HTTP
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                // Thiết lập phương thức yêu cầu là GET
                urlConnection.setRequestMethod("GET");

                // Kiểm tra mã phản hồi từ máy chủ
                int responseCode = urlConnection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) { // 200 OK
                    // Đọc phản hồi từ máy chủ
                    BufferedReader bufferIn = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    String inputLine;

                    while ((inputLine = bufferIn.readLine()) != null) {
                        response.append(inputLine);
                    }

                    bufferIn.close();
                } else {
                    response.append("Error: ").append(responseCode);
                }

                // Đóng kết nối
                urlConnection.disconnect();

            } catch (IOException e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            }
        }
        return response.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        textView.setText(s);
    }
}
