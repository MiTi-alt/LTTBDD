package com.example.myapplication.Admin.Fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.myapplication.Admin.API.MyAsyncTask;
import com.example.myapplication.Admin.Adapter.Adapter;
import com.example.myapplication.Model.News;
import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AdminNews extends Fragment {
    private ProgressBar progressBar;
    private ListView listView;
    private ArrayList<News> list;
    private Adapter adapter;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public AdminNews() {
        // Required empty public constructor
    }

    public static AdminNews newInstance(String param1, String param2) {
        AdminNews fragment = new AdminNews();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_news, container, false);
        AnhXa(view);
        return view;
    }

    private void AnhXa(View view) {
        listView = view.findViewById(R.id.listview);
        list = new ArrayList<>();
        progressBar = view.findViewById(R.id.progressBar);
        String apiKey = "9lE4P7b3BrtQdSCGJUt7kQOE9lKjC0Rs";
        String apiUrl = "https://api.nytimes.com/svc/topstories/v2/home.json?api-key=" + apiKey;

        MyAsyncTask myAsyncTask = new MyAsyncTask(new MyAsyncTask.AsyncTaskListener() {
            @Override
            public void onTaskComplete(String result) {
                try {

                    progressBar.setVisibility(View.VISIBLE);
                    JSONObject jsonResult = new JSONObject(result);
                    if (jsonResult.optString("status").equals("OK")) {
                        // Get the array of results
                        JSONArray resultsArray = jsonResult.getJSONArray("results");
                        for (int i = 0; i < resultsArray.length(); i++) {
                            JSONObject articleObject = resultsArray.getJSONObject(i);
                            int id = i + 1; // You can set your own logic for generating IDs
                            String title = articleObject.optString("title");
                            String abstracts = articleObject.optString("abstract");
                            String author = getAuthor(articleObject);
                            String image = getImageUrl(articleObject);
                            String dateTime = articleObject.optString("published_date");
                            String url = articleObject.optString("url");
                            News article = new News(id, title, abstracts, author, image, dateTime, url, dateTime);
                            list.add(article);

                        }

                        // Update the ListView here after data is fetched
                        adapter = new Adapter(getActivity(), list);
                        listView.setAdapter(adapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }finally {
                    // Hide ProgressBar when data loading is complete (regardless of success or failure)
                    progressBar.setVisibility(View.GONE);
                }
            }

            private String getAuthor(JSONObject articleObject) {
                // Lấy giá trị của trường "byline" từ đối tượng JSON
                String byline = articleObject.optString("byline");

                // Kiểm tra xem giá trị "byline" có chứa nhiều tác giả hay không
                if (byline != null && byline.contains(",")) {
                    // Nếu có nhiều tác giả, chỉ lấy tên của tác giả đầu tiên
                    return byline.split(",")[0].trim();
                } else {
                    // Nếu chỉ có một tác giả hoặc không có tác giả nào, trả về giá trị "byline" nguyên thủy
                    return byline;
                }
            }




            private String getImageUrl(JSONObject articleObject) {
                JSONArray multimediaArray = articleObject.optJSONArray("multimedia");
                if (multimediaArray != null && multimediaArray.length() > 0) {
                    JSONObject multimediaObject = multimediaArray.optJSONObject(0);
                    if (multimediaObject != null) {

                        return multimediaObject.optString("url");
                    }
                }
                return null;
            }

        });

        myAsyncTask.execute(apiUrl);
    }

}
