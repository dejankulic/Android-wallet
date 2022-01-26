package rs.raf.prviprojekat.view.recycler.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.function.Function;

import rs.raf.prviprojekat.R;
import rs.raf.prviprojekat.application.Timberr;
import rs.raf.prviprojekat.models.Prihod;
import rs.raf.prviprojekat.models.Rashod;
import timber.log.Timber;

public class RashodAdapter extends ListAdapter<Rashod,RashodAdapter.ViewHolder> {


    private Function<Rashod, Void> onRashodClicked;
    private Function<Rashod, Void> onEditRasClicked;
    private Function<Rashod, Void> onDeleteRasClicked;

    public RashodAdapter(@NonNull DiffUtil.ItemCallback<Rashod> diffCallback, Function<Rashod, Void> onRashodClicked, Function<Rashod,Void> onEditRasClicked, Function<Rashod,Void> onDeleteRasClicked) {
        super(diffCallback);
        this.onRashodClicked = onRashodClicked;
        this.onEditRasClicked = onEditRasClicked;
        this.onDeleteRasClicked = onDeleteRasClicked;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.fragment_rashod_recycler, parent, false);
        return new ViewHolder(view, position ->{
            Rashod ras = getItem(position);
            onRashodClicked.apply(ras);
            return null;
        },editRas->{
            Rashod ras = getItem(editRas);
            onEditRasClicked.apply(ras);
            return null;
        },deleteRas ->{
            Rashod ras = getItem(deleteRas);

            onDeleteRasClicked.apply(ras);
            return null;
        });
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Rashod ras = getItem(position);
        holder.bind(ras);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView, Function<Integer,Void> onItemClicked, Function<Integer,Void> onEditRasClicked, Function<Integer,Void> onDeleteRasClicked) {
            super(itemView);

            itemView.setOnClickListener(v -> {
                if(getAdapterPosition() != RecyclerView.NO_POSITION) {
                    onItemClicked.apply(getAdapterPosition());

                }
            });
            itemView.findViewById(R.id.editRasButton).setOnClickListener(v -> {
                if(getAdapterPosition() != RecyclerView.NO_POSITION) {
                    onEditRasClicked.apply(getAdapterPosition());

                }
            });
            itemView.findViewById(R.id.deleteRasButton).setOnClickListener(v->{
                if(getAdapterPosition() != RecyclerView.NO_POSITION) {
                    onDeleteRasClicked.apply(getAdapterPosition());

                }
            });

        }
        public void bind(Rashod prihod){
            ImageView imageView = itemView.findViewById(R.id.image);
            ((TextView)itemView.findViewById(R.id.naslovTv)).setText(prihod.getNaslov());
            ((TextView)itemView.findViewById(R.id.iznosTv)).setText(prihod.getKolicina() + "");
            ImageButton editRas = itemView.findViewById(R.id.editRasButton);

        }
    }
}
