package com.cogeek.tncoffee.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
            "https://firebasestorage.googleapis.com/v0/b/coffee-74fba.appspot.com/o/image_11.jpg?alt=media&token=bfc136f2-645a-4e58-8eda-6050426ce0d1",
            "https://firebasestorage.googleapis.com/v0/b/coffee-74fba.appspot.com/o/image_5.jpg?alt=media&token=0e201ce1-a251-45ca-88e6-8eb1ae1ee3e7",
            "https://firebasestorage.googleapis.com/v0/b/coffee-74fba.appspot.com/o/image_6.jpg?alt=media&token=f02f8999-dc4d-4b4b-bdac-e8fd8643f132"
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
