package Service;

import android.util.Log;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ServerConnection {
    private final static String ROOM_URL = "http://10.0.2.2:8080/room/";
    private final static OkHttpClient client = new OkHttpClient();

    private static Call getResponse(String url, Callback callback) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
        return call;
    }

    public static String getRoom(int roomID, GenericCallback genericCallback) throws IOException{
        getResponse(ROOM_URL + roomID, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e("Response:", e.toString());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    //Se a requisição foi feita com sucesso e retornou (quando ela retornar)
                    String responseStr = response.body().string();
                    genericCallback.execute(responseStr);
                }else{
                    //A requisição falhou
                    String responseStr = response.body().string();
                    genericCallback.execute(responseStr);
                }
            }});

        return "a";
    }
}
