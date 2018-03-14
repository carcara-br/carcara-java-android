package br.com.stant.stant_android_fiscal.selectconstructionsite;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.stant.stant_android_fiscal.R;
import br.com.stant.stant_android_fiscal.databinding.SelectConstructionSiteItemBinding;
import br.com.stant.stant_android_fiscal.selectconstructionsite.domain.dto.ConstructionSiteDto;

/**
 * Created by denisvieira on 10/05/17.
 */

public class SelectConstructionSiteAdapter extends RecyclerView.Adapter<SelectConstructionSiteAdapter.ItemViewHolder> implements SelectConstructionSiteContract.Adapter{

    private final SelectConstructionSiteContract.View mSelectConstructionSiteView;
    private  List<ConstructionSiteDto> mConstructionSites;
    private Context mContext;
    private ItemViewHolder mViewHolder;

    public SelectConstructionSiteAdapter(Context context,
                                         List<ConstructionSiteDto> constructionSites,
                                         SelectConstructionSiteContract.View selectConstructionSiteView) {
        this.mContext                    = context;
        this.mConstructionSites          = constructionSites;
        this.mSelectConstructionSiteView = selectConstructionSiteView;
    }

    @Override
    public void replaceData(List list) {
        mConstructionSites = list;
        notifyDataSetChanged();
    }


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SelectConstructionSiteItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.select_construction_site_item,
                parent,
                false);

        final ItemViewHolder vh = new ItemViewHolder(binding);
        return vh;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        mViewHolder = holder;

        ConstructionSiteDto constructionSiteDto = mConstructionSites.get(position);
        mViewHolder.itemBinding.setConstructionsite(constructionSiteDto);
        mViewHolder.itemBinding.selectConstructionSiteItemGeneralContainerLayout.setOnClickListener(
                v -> mSelectConstructionSiteView.goToContractProgressEvaluation(constructionSiteDto.getId(),constructionSiteDto.getTitle()));

        mViewHolder.itemBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mConstructionSites.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{

        private SelectConstructionSiteItemBinding itemBinding;

        public ItemViewHolder(SelectConstructionSiteItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }
    }

}
