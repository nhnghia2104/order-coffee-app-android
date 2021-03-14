package com.cogeek.tncoffee.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.cogeek.tncoffee.R;
import com.cogeek.tncoffee.models.Notification;
import com.cogeek.tncoffee.models.SliderItem;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private ListView listView;
    private NotificationAdapter adapter;
    private ArrayList<Notification> arrayList;
    private List<SliderItem> sliderItems;
    private ViewPager2 viewPager2;
    private Handler slideHandler;
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
        adapter = new NotificationAdapter(getActivity(), R.layout.notification,arrayList);
        View header = getLayoutInflater().inflate(R.layout.header_home_listview, null, false);
        listView.addHeaderView(header);
        listView.setAdapter(adapter);

        viewPager2 = header.findViewById(R.id.viewPagerInageSlider);
        sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItem(R.drawable.image_1));
        sliderItems.add(new SliderItem(R.drawable.image_2));
        sliderItems.add(new SliderItem(R.drawable.image_3));
        sliderItems.add(new SliderItem(R.drawable.image_4));
        sliderItems.add(new SliderItem(R.drawable.image_5));

        viewPager2.setAdapter(new SliderAdapter(sliderItems,viewPager2));

        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

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
                slideHandler.postDelayed(sliderRunnable,4000);
            }
        });
    }

    private void setUpSlider() {

    }

    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            int target = (viewPager2.getCurrentItem() + 1 ) % sliderItems.size();
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
        slideHandler.postDelayed(sliderRunnable,4000);
    }

    private void fakeData() {
        arrayList = new ArrayList<Notification>();
    }
}
