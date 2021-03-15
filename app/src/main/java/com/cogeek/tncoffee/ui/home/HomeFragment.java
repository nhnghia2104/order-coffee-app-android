package com.cogeek.tncoffee.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.cogeek.tncoffee.R;
import com.cogeek.tncoffee.models.Notification;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Date;

import me.relex.circleindicator.CircleIndicator3;

public class HomeFragment extends Fragment {
    private ListView listView;
    private NotificationAdapter adapter;
    private ArrayList<Notification> arrayList;
    //    private List<SliderItem> sliderItems;
    private ViewPager2 viewPager2;
    private Handler slideHandler;
    private CircleIndicator3 indicator;
    private View header;

    String[] imageUrls = new String[]{
            "https://scontent.fsgn2-3.fna.fbcdn.net/v/t1.0-9/130818260_4019742878053264_1195813771588404240_o.jpg?_nc_cat=108&ccb=1-3&_nc_sid=730e14&_nc_ohc=OBOeLzuKcWEAX8H92-M&_nc_ht=scontent.fsgn2-3.fna&oh=b940c0851975f5020882a80a17f6e059&oe=607534C2",
            "https://scontent.fsgn2-5.fna.fbcdn.net/v/t1.0-9/118649170_3715031765191045_3492615898358150308_o.jpg?_nc_cat=104&ccb=1-3&_nc_sid=730e14&_nc_ohc=lVA5xKfWiG0AX88fv2g&_nc_ht=scontent.fsgn2-5.fna&oh=10fed9463b84ba911499801a2ef5fe6c&oe=6073BFB4",
            "https://scontent.fsgn2-3.fna.fbcdn.net/v/t1.0-9/147126357_4170418296319054_6493367843296217162_o.jpg?_nc_cat=108&ccb=1-3&_nc_sid=730e14&_nc_ohc=zoKybtgQOVIAX8ipMJq&_nc_ht=scontent.fsgn2-3.fna&oh=6513735cdfd1b28bfdc7e727c89dccf4&oe=6076447A"
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fakeData();
        listView = view.findViewById(R.id.lvNotification);
        adapter = new NotificationAdapter(getActivity(), R.layout.notification, arrayList);
        this.header = getLayoutInflater().inflate(R.layout.header_home_listview, null, false);
        listView.addHeaderView(header);
        listView.setAdapter(adapter);

        setUpSlider();
        addEvent();
    }

    private void addEvent() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                NotificationBottomSheetDialogFragment bottomSheetDialog = new NotificationBottomSheetDialogFragment();
                bottomSheetDialog.show(getActivity().getSupportFragmentManager(),"Detail Notification");
            }
        });
    }

    private void setUpSlider() {
        viewPager2 = header.findViewById(R.id.viewPagerInageSlider);
        indicator = header.findViewById(R.id.indicator);


        SliderAdapter sliderAdapter = new SliderAdapter(imageUrls, viewPager2);
        viewPager2.setAdapter(sliderAdapter);
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(1);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        // Transformer
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });

        viewPager2.setPageTransformer(compositePageTransformer);
        slideHandler = new Handler();
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                slideHandler.removeCallbacks(sliderRunnable);
                slideHandler.postDelayed(sliderRunnable, 4000);
            }
        });

        indicator.setViewPager(viewPager2);
        sliderAdapter.registerAdapterDataObserver(indicator.getAdapterDataObserver());

    }

    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            int target = (viewPager2.getCurrentItem() + 1) % imageUrls.length;
            viewPager2.setCurrentItem(target);
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        slideHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        slideHandler.postDelayed(sliderRunnable, 4000);
    }

    private void fakeData() {
        arrayList = new ArrayList<Notification>();
        arrayList.add(new Notification("non", "", "", (int) (new Date().getTime() / 1000)));
        arrayList.add(new Notification("non", "", "", (int) (new Date().getTime() / 1000)));
        arrayList.add(new Notification("non", "", "", (int) (new Date().getTime() / 1000)));
    }
}
