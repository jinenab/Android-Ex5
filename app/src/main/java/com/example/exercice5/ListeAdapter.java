package com.example.exercice5;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ListeAdapter extends RecyclerView.Adapter<ListeAdapter.ViewHolder> {
    public static final String KEY_TITLE = "title";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_BODY = "body";
    public static final String KEY_KIND = "kind";
    public static final String KEY_URL = "url";
    public static final String KEY_AUTHOR = "author";


    // we define a list from the Etudiant java class

    private List<Listenouveaute> etudiants;
    private Context context;
    public ListeAdapter(List<Listenouveaute> etudiants, Context context) {

        // generate constructors to initialise the List and Context objects

        this.etudiants = etudiants;
        this.context = context;

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // this method will be called whenever our ViewHolder is created
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.clientinfo, parent, false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        // this method will bind the data to the ViewHolder from whence it'll be shown to other Views

        final Listenouveaute etudiants1 = etudiants.get(position);
        holder.title.setText(etudiants1.getTitle ());
        holder.author.setText(etudiants1.getAuthor ());
        holder.body.setText(etudiants1.getBody ());
        holder.kind.setText(etudiants1.getKind());


        Picasso.with(context)
                .load(etudiants1.getImage ())
                .into(holder.image);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Listenouveaute etudiants1 = etudiants.get(position);
                Intent skipIntent = new Intent(v.getContext(), MainActivity.class);
                skipIntent.putExtra(KEY_KIND, etudiants1.getKind());
                skipIntent.putExtra(KEY_TITLE, etudiants1.getTitle ());
                skipIntent.putExtra(KEY_BODY, etudiants1.getBody ());
                skipIntent.putExtra(KEY_IMAGE, etudiants1.getImage ());
                skipIntent.putExtra(KEY_AUTHOR, etudiants1.getAuthor ());


                v.getContext().startActivity(skipIntent);
            }
        });

    }
    @Override

    //return the size of the listItems (developersList)

    public int getItemCount() {
        return etudiants.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder  {

        // define the View objects

        public TextView title;
        public TextView body;
        public TextView author;
        public TextView kind;
        public ImageView image;

        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            // initialize the View objects
            kind= (TextView) itemView.findViewById(R.id.kind);
            title= (TextView) itemView.findViewById(R.id.title);
            body = (TextView) itemView.findViewById(R.id.body);
            author= (TextView) itemView.findViewById(R.id.author);
            image = (ImageView) itemView.findViewById(R.id.imgPost);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
        }

    }





}








