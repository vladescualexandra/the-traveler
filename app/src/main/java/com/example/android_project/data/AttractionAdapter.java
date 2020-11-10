package com.example.android_project.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.android_project.R;
import com.example.android_project.data.Attraction;

import java.util.List;

public class AttractionAdapter extends ArrayAdapter<Attraction> {

    private Context context;
    private int resource;
    private List<Attraction> attractionList;
    private LayoutInflater layoutInflater;

    public AttractionAdapter(@NonNull Context context,
                             int resource,
                             @NonNull List<Attraction> attractionList,
                             LayoutInflater layoutInflater) {
        super(context, resource, attractionList);
        this.context = context;
        this.resource = resource;
        this.attractionList = attractionList;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public View getView(int position,
                        @Nullable View convertView,
                        @NonNull ViewGroup parent) {

        Attraction attraction = attractionList.get(position);
        View view = layoutInflater.inflate(resource, parent, false);

        if (attraction != null) {
            addAttractionBanner(view, attraction.getBanner());
        }

        return view;
    }

    private void addAttractionBanner(View view, String banner) {


        if (banner != null && !banner.isEmpty()) {
            ImageView imageView = view.findViewById(R.id.main_banner);
            int resID = context.getResources().getIdentifier(banner, "drawable", context.getPackageName());
            imageView.setImageResource(resID);
        } else {
        }
    }
}
