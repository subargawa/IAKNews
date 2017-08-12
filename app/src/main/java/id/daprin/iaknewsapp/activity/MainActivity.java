package id.daprin.iaknewsapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.daprin.iaknewsapp.BuildConfig;
import id.daprin.iaknewsapp.R;
import id.daprin.iaknewsapp.adapter.NewsAdapter;
import id.daprin.iaknewsapp.model.ApiResponse;
import id.daprin.iaknewsapp.model.ArticlesItem;
import id.daprin.iaknewsapp.rest.ApiClient;
import id.daprin.iaknewsapp.rest.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    /*Recycler view perlu: VIewHolder, Adapter, LayoutManager */
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    LinearLayoutManager mLinearLayoutManager;
    NewsAdapter mAdapterDummy;
    NewsAdapter mAdapterApi;
    private List<ArticlesItem> mListArticle = new ArrayList<>();

    private static final String NEWS_SOURCE = "techcrunch";
    private static final String API_KEY = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //SETUP ADAPTER
        mAdapterDummy = new NewsAdapter(GetDummyArticlesItem());
        mAdapterApi = new NewsAdapter(mListArticle);

        //SETUP RECYCLERVIEW
        mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mAdapterApi);

        getData();
    }

    private List<ArticlesItem> GetDummyArticlesItem(){
        List<ArticlesItem> result = new ArrayList<>();
        for(int i=0; i<10; i++){
            ArticlesItem item = new ArticlesItem();
            item.setTitle("Ini Merupakakan Title untuk menampilkan max line dari textview");
            item.setDescription("Ini merupakan deskripsi yang merupakan data random, yang bisa di copas. Ini merupakan deskripsi yang merupakan data random, yang bisa di copas.Ini merupakan deskripsi yang merupakan data random, yang bisa di copas.Ini merupakan deskripsi yang merupakan data random, yang bisa di copas.Ini merupakan deskripsi yang merupakan data random, yang bisa di copas.");
            item.setUrlToImage("https://tctechcrunch2011.files.wordpress.com/2017/08/aug_chart_1.png?w=764&h=400&crop=1");
            result.add(item);
        }
        return result;
    }

    private void getData(){
        ApiService apiService = ApiClient.getmRetrofitClient().create(ApiService.class);
        Call<ApiResponse> apiResponseCall = apiService.getArticle(
            NEWS_SOURCE,
                // memanggil API_KEY
                BuildConfig.APi_KEY
        );
        Log.d(TAG, "getData: API_KEY" + BuildConfig.APi_KEY);
        apiResponseCall.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                ApiResponse apiResponse = response.body();
                if (apiResponse != null){
                    mListArticle = apiResponse.getArticles();
                    mAdapterApi.setData(mListArticle);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "call failed" +t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onFailure:", t);
            }
        });
    }
}
