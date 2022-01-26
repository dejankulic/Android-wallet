package rs.raf.prviprojekat.view.recycler.differ;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import rs.raf.prviprojekat.models.Prihod;
import rs.raf.prviprojekat.models.Rashod;

public class RashodDiffer extends DiffUtil.ItemCallback<Rashod>{

    @Override
    public boolean areItemsTheSame(@NonNull Rashod oldItem, @NonNull Rashod newItem) {
        return oldItem.getId()== newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull Rashod oldItem, @NonNull Rashod newItem) {
        return (oldItem.getNaslov().equals(newItem.getNaslov()) &&
                oldItem.getKolicina() == newItem.getKolicina()
                );
    //oldItem.getOpis().equals(newItem.getOpis())
    }
}
