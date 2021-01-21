package tw.com.ian.mywebconn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView viewer;
    ReaderAdapter adapter;
    private MyHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewer = findViewById(R.id.reader_show);

        handler = new MyHandler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                getUrlByGet();
            }
        }).start();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        viewer.setLayoutManager(manager);
        viewer.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

    }

    private void getUrlByGet() {

        try {
            URL url = new URL("http://120.110.115.82:8000/api/reading/");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            InputStream in = conn.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(reader);
            StringBuffer lines = new StringBuffer();
            String line = null;
            while ((line=br.readLine())!=null){
                lines.append(line);
            }
            Gson gson = new Gson();
            List<ReadClass> rs = gson.fromJson(lines.toString(), new TypeToken<List<ReadClass>>(){}.getType());
            handler.setData(rs);
            handler.sendEmptyMessage(0);

        } catch (Exception e) {
            for (StackTraceElement se :e.getStackTrace())
                log(se.toString());
        }


    }

    class MyHandler extends Handler{
        private List<ReadClass> rs;

        public void setData(List<ReadClass> rs)
        {
            this.rs = rs;
        }
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            adapter = new ReaderAdapter(rs);
            viewer.setAdapter(adapter);

        }
    }


    public void log(String msg)
    {
        Log.v("Jacky",msg);
    }


}

