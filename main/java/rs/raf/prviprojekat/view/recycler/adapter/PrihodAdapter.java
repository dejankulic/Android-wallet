package rs.raf.prviprojekat.view.recycler.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.function.Function;

import rs.raf.prviprojekat.R;
import rs.raf.prviprojekat.application.Timberr;
import rs.raf.prviprojekat.models.Prihod;
import rs.raf.prviprojekat.view.activities.MainActivity;
import rs.raf.prviprojekat.view.fragments.PrihodFragment;
import rs.raf.prviprojekat.viewmodels.RecyclerViewModel;
import timber.log.Timber;

public class PrihodAdapter extends ListAdapter<Prihod,PrihodAdapter.ViewHolder> {

    //private ImageButton editPr ;
    private Function<Prihod, Void> onPrihodClicked;
    private Function<Prihod, Void> onEditPrClicked;
    private Function<Prihod, Void> onDeletePrClicked;

    public PrihodAdapter(@NonNull DiffUtil.ItemCallback<Prihod> diffCallback, Function<Prihod, Void> onPrihodClicked, Function<Prihod,Void> onEditPrClicked, Function<Prihod,Void> onDeletePrClicked) {
        super(diffCallback);
        this.onPrihodClicked = onPrihodClicked;
        this.onEditPrClicked = onEditPrClicked;
        this.onDeletePrClicked = onDeletePrClicked;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

      //  editPr = parent.findViewById(R.id.editPrButton);
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.fragment_prihod_recycler, parent, false);
        return new ViewHolder(view, position ->{
            Prihod pr = getItem(position);
            onPrihodClicked.apply(pr);
            return null;
        },editPr->{
            Prihod pr = getItem(editPr);
            onEditPrClicked.apply(pr);
            return null;
        },deletePr ->{
            Prihod pr = getItem(deletePr);

            onDeletePrClicked.apply(pr);
            return null;
        });
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Prihod pr = getItem(position);
        holder.bind(pr);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView, Function<Integer,Void> onItemClicked, Function<Integer,Void> onEditPrClicked, Function<Integer,Void> onDeletePrClicked) {
            super(itemView);

            itemView.setOnClickListener(v -> {
                if(getAdapterPosition() != RecyclerView.NO_POSITION) {
                    onItemClicked.apply(getAdapterPosition());

                }
            });
            itemView.findViewById(R.id.editPrButton).setOnClickListener(v -> {
                if(getAdapterPosition() != RecyclerView.NO_POSITION) {
                    onEditPrClicked.apply(getAdapterPosition());

                }
            });
            itemView.findViewById(R.id.deletePrButton).setOnClickListener(v->{
                if(getAdapterPosition() != RecyclerView.NO_POSITION) {
                    onDeletePrClicked.apply(getAdapterPosition());

                }
            });

        }
        public void bind(Prihod prihod){
            ImageView imageView = itemView.findViewById(R.id.image);
            ((TextView)itemView.findViewById(R.id.naslovTv)).setText(prihod.getNaslov());
            ((TextView)itemView.findViewById(R.id.iznosPrTv)).setText(prihod.getKolicina() + "");
            ImageButton editPr = itemView.findViewById(R.id.editPrButton);

        }
    }
}
