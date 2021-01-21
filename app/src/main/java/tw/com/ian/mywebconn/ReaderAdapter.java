package tw.com.ian.mywebconn;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.transition.Transition;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.text.HtmlCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.List;

public class ReaderAdapter extends RecyclerView.Adapter<ReaderAdapter.ViewHolder> {
    List<ReadClass> rs;
    Context context ;
    public ReaderAdapter(List<ReadClass> rs){
        this.rs = rs;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,content;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(context).inflate(R.layout.reader_items,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(HtmlCompat.fromHtml(rs.get(position).title,HtmlCompat.FROM_HTML_MODE_COMPACT));
        holder.content.setText(HtmlCompat.fromHtml(rs.get(position).content,HtmlCompat.FROM_HTML_MODE_COMPACT));
    }

    @Override
    public int getItemCount() {
        return rs.size();
    }






}
