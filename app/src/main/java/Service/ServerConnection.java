package Service;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import java.io.IOException;

import DTO.RoomDTO;
import func_classes.Sala;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ServerConnection {
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private final static String ROOM_URL = "http://10.0.2.2:8080/room/";

    private final static OkHttpClient client = new OkHttpClient();

    private final static Gson gson = new Gson();

    private static void post(String json, String url, Callback callback){
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }



    private static void getResponse(String url, Callback callback) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public static void getRoom(int roomID, GenericCallback genericCallback) throws IOException{
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
    }

    public static void createRoom(String nome, int adm, GenericCallback genericCallback) throws IOException{
        RoomDTO dto = new RoomDTO(nome, adm);
        String json = gson.toJson(dto);

        Log.i("json: ", json);

        post(json, ROOM_URL, new Callback() {
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
                }
            }
        });
    }
}
