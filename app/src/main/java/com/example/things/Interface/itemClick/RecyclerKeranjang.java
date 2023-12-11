package com.example.things.Interface.itemClick;

import com.example.things.Model.KeranjangModel;

import java.util.List;

public interface RecyclerKeranjang {


    void onItemClick(int position, List<KeranjangModel> models);

    void onDeleteItemClick(int position, List<KeranjangModel> models);

    void onCHeckOutItemClick(int position, List<KeranjangModel> models);

    void onVhatItemClick(int position, List<KeranjangModel> models);
}
