package com.example.fakeapi;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


getPosts();
    }

    private void getPosts() {

        JSONPlaceHolderAPI jsonPlaceHolderAPI;
        final TextView textViewResult = (TextView) findViewById((R.id.text_view_result));
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();



        jsonPlaceHolderAPI = retrofit.create(JSONPlaceHolderAPI.class);

        Call<List<Post>> call = jsonPlaceHolderAPI.getPosts(4);

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {

                    textViewResult.setText("Code: "+response.code());
                    return;


                }

                List<Post> posts = response.body();

                if (response.isSuccessful()) {

                    for (Post post:posts){
                        String content = "";

                        content += "ID: "+post.getId()+"\n";
                        content += "User ID: "+post.getUserId()+"\n";
                        content += "Title: "+post.getTitle()+"\n";
                        content += "URL: "+post.getUrl()+"\n";
                        content += "Thumbnail URL: "+post.getThumbnailUrl()+"\n";
                        content += "Body: "+post.getText()+"\n\n\n";

                        textViewResult.append(content);
                    }


                }
            }
            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }



        });


    }
}