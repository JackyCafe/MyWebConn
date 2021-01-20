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

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {
   RecyclerView viewer;
   ReaderAdapter adapter;
   MyHandler handher = new MyHandler() ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        log("onCreate");
        setContentView(R.layout.activity_main);
        viewer = findViewById(R.id.reader_show);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        viewer.setLayoutManager(layoutManager);

        viewer.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

    }

    @Override
    protected void onStart() {
        super.onStart();
        log("onStart");
        new Thread(new Runnable() {
            @Override
            public void run() {
                getUrlByGet();
            }
        }).start();
    }

    private void getUrlByGet() {
        try {
           /*連線設定*/

            URL url = new URL("http://120.110.115.82:8000/api/reading/");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(3000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            InputStream in = conn.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(reader);
            String line = new String();
            StringBuffer lines = new StringBuffer();
            while ((line = br.readLine())!=null)
            {
                lines.append(line);
            }
            Gson gson = new Gson();
            List<ReadClass> rs = gson.fromJson(lines.toString()
                    ,new TypeToken<List<ReadClass>>(){}.getType());
            handher.setData(rs);
            Message msg = new Message();
            Bundle data = new Bundle();
            msg.setData(data);
            handher.sendEmptyMessage(0);

        } catch (Exception e) {
            for (StackTraceElement se :e.getStackTrace()){
                Log.v("Jacky",se.toString());
            }

        }
    }

    public void log(String msg)
    {
        Log.v("Jacky",msg);
    }

    class MyHandler extends Handler {
        List<ReadClass> data;
        public void setData(List<ReadClass> data){
            this.data = data;
        }
        @Override
        public void handleMessage(@NonNull Message msg) {
            adapter = new ReaderAdapter(data);
            handher.setData(data);

            viewer.setAdapter(adapter);
            super.handleMessage(msg);
        }
    }
}

