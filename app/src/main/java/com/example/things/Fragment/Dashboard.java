package com.example.things.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.things.Activity.KategoriActivity;
import com.example.things.Activity.StartActivity;
import com.example.things.Adapter.ImageSliderAdapter;
import com.example.things.Adapter.KategoriAdapter;
import com.example.things.Interface.ImageSliderInterface;
import com.example.things.Model.ImageSliderModel;
import com.example.things.Model.KategoriModel;
import com.example.things.Presenter.ImageSliderPresenter;
import com.example.things.R;
import com.example.things.Utils.SessionManager;
import com.example.things.databinding.FragmentDashboardBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dashboard extends Fragment implements ImageSliderInterface {

    private FragmentDashboardBinding binding;
    private SessionManager sessionManager;
    private ImageSliderPresenter imageSliderPresenter;
    private ImageSliderAdapter adapter;

    KategoriModel kategoriModel;

    private Handler sliderHandler = new Handler(Looper.getMainLooper());

    String nama;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View view = binding.getRoot();


        sessionManager = new SessionManager(getActivity());



        //letakan data yang ada di hasmap ke textview
        nama = sessionManager.getUserDetail().get(SessionManager.USERNAME);

        imageSliderPresenter = new ImageSliderPresenter(this);

        AllButton();
        imageSliderPresenter.getIklan();
        // Setiap 3 detik, otomatis pindah ke item berikutnya
        sliderHandler.postDelayed(sliderRunnable, 5000);

        inisialisasiDaftarKategori();

        return view;
    }

    private void inisialisasiDaftarKategori() {
        // Misalkan, definisikan array kategori Anda
        KategoriModel[] daftarKategori = new KategoriModel[] {
                new KategoriModel("Atasan", "Wanita", R.drawable.atasancewe),
                new KategoriModel("Bawahan", "Wanita", R.drawable.bawahancw),
                new KategoriModel("Sepatu/Sandal", "Wanita", R.drawable.sepatucw),
                new KategoriModel("Tas", "Wanita", R.drawable.tascw),
                new KategoriModel("Aksesoris", "Wanita", R.drawable.aksesoriscw),
                new KategoriModel("Atasan", "Pria", R.drawable.atasancowo),
                new KategoriModel("Bawahan", "Pria", R.drawable.bawahancowo),
                new KategoriModel("Sepatu/Sandal", "Pria", R.drawable.sepatucowo),
                new KategoriModel("Tas", "Pria", R.drawable.tascowo),
                new KategoriModel("Aksesoris", "Pria", R.drawable.jamcowo)
                // tambahkan kategori lainnya
        };

        // Inisialisasi adapter untuk pria dan wanita
        KategoriAdapter adapterPria = new KategoriAdapter(getContext(), filterByType(Arrays.asList(daftarKategori), "Pria"));
        KategoriAdapter adapterWanita = new KategoriAdapter(getContext(), filterByType(Arrays.asList(daftarKategori), "Wanita"));


        binding.recKategoriPria.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.recKategoriWanita.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        binding.recKategoriPria.setAdapter(adapterPria);
        binding.recKategoriWanita.setAdapter(adapterWanita);

    }

    private List<KategoriModel> filterByType(List<KategoriModel> asList, String type) {
        List<KategoriModel> filteredList = new ArrayList<>();

        for (KategoriModel model : asList) {
            if (model.getType().equals(type)) {
                filteredList.add(model);
            }
        }

        return filteredList;
    }


    private void AllButton() {
        binding.btnAllKategoriPria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), KategoriActivity.class);
                startActivity(intent);
            }
        });
        binding.btnAllKategoriWanita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), KategoriActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void IklanResponses(ArrayList<ImageSliderModel> imageUrls) {
        adapter = new ImageSliderAdapter(imageUrls);
        binding.imageSlider.setAdapter(adapter);

    }

    @Override
    public void messageError() {
        Toast.makeText(getActivity(), "Gagal memuat image slider", Toast.LENGTH_SHORT).show();
    }

    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            if (adapter != null) {
                int currentItem = binding.imageSlider.getCurrentItem();
                int itemCount = adapter.getItemCount();

                if (currentItem < itemCount - 1) {
                    // Jika bukan elemen terakhir, pindah ke elemen berikutnya
                    binding.imageSlider.setCurrentItem(currentItem + 1);
                } else {
                    // Jika elemen terakhir, kembali ke elemen pertama
                    binding.imageSlider.setCurrentItem(0);
                }

                sliderHandler.postDelayed(this, 5000);
            } else {
                // Penanganan jika adapter bernilai null
            }

        }
    };
}