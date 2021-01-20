package tw.com.ian.mywebconn;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReaderAdapter extends RecyclerView.Adapter<ReaderAdapter.ViewHolder> {
    private List<ReadClass> data;
    public ReaderAdapter(List<ReadClass> data){
        this.data = data;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public TextView title,content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            content = (TextView)itemView.findViewById(R.id.content);
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_reader_items,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(Html.fromHtml(data.get(position).title));
        holder.content.setText(Html.fromHtml(data.get(position).content));
    }


    @Override
    public int getItemCount() {

        return data.size();
    }
}
