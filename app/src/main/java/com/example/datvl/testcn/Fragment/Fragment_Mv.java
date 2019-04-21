package com.example.datvl.testcn.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.datvl.testcn.Activity.PlayMvActivity;
import com.example.datvl.testcn.Adapter.MvAdapter;
import com.example.datvl.testcn.Model.VideoYouTub;
import com.example.datvl.testcn.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Fragment_Mv extends Fragment{
    public static String API_KEY = "AIzaSyBMb_cFNhipqJ9ty3fkurGI1AedAWgwXHA";
    String ID_PLAYLIST ="PLCnwZJuKjaRIQOkcC4UwF2KgmhB0mFYA_";
    String urlGetJson = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId="+ ID_PLAYLIST +"&key="+ API_KEY +"&maxResults=50";
    View view;
    ListView listMV;
    ArrayList<VideoYouTub> arrVideoYouTub;
    MvAdapter mvAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mv,container,false);
        listMV = view.findViewById(R.id.lvmv);
        arrVideoYouTub = new ArrayList<>();
        mvAdapter = new MvAdapter(getActivity(),R.layout.dong_video,arrVideoYouTub);
        listMV.setAdapter(mvAdapter);

        GetJsonYoutub(urlGetJson);

        listMV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), PlayMvActivity.class);
                intent.putExtra("idVideo", arrVideoYouTub.get(i).getIdVideo());
                startActivity(intent);

            }
        });

        return view;

    }
    private void GetJsonYoutub(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonItems = response.getJSONArray("items");
                            String title = "";
                            String url = "";
                            String channelTitle = "";
                            String idVideo = "";
                            for(int i = 0; i < jsonItems.length(); i++){
                                JSONObject jsonItem = jsonItems.getJSONObject(i);

                                JSONObject jsonSnippet = jsonItem.getJSONObject("snippet");
                                title = jsonSnippet.getString("title");

                                JSONObject jsonThumbnails = jsonSnippet.getJSONObject("thumbnails");
                                JSONObject jsonMedium = jsonThumbnails.getJSONObject("medium");
                                url = jsonMedium.getString("url");

                                channelTitle = jsonSnippet.getString("channelTitle");
                                JSONObject jsonResourceId = jsonSnippet.getJSONObject("resourceId");
                                idVideo = jsonResourceId.getString("videoId");

                                arrVideoYouTub.add(new VideoYouTub(title, url, channelTitle, idVideo));
                            }

                            mvAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(),"loi!",Toast.LENGTH_LONG).show();
                    }
                }
        );

        requestQueue.add(jsonObjectRequest);
    }
}
