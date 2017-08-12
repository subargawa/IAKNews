package id.daprin.iaknewsapp.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import id.daprin.iaknewsapp.R;
import id.daprin.iaknewsapp.model.ArticlesItem;

public class DetailActivityy extends AppCompatActivity {

    private  static final String KEY_EXTRA_NEWS = "news";
    private ArticlesItem mArticlesItem;

    // methode untuk memulai activity Detail Activity
    public static void start(Context context, String newsJson){
        Intent intent = new Intent(context, DetailActivityy.class);
        intent.putExtra(KEY_EXTRA_NEWS, newsJson);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_activityy);
        if (getIntent().hasExtra(KEY_EXTRA_NEWS)){
            String newsJson = getIntent().getStringExtra(KEY_EXTRA_NEWS);
            mArticlesItem = new ArticlesItem().fromJson(newsJson);
            Toast.makeText(this, "Show News", Toast.LENGTH_SHORT).show();
        }
        else {
            finish();
        }
    }
}
