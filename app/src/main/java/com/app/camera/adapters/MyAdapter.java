// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.app.camera.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.camera.R;

public class MyAdapter extends MyRecylceAdapterBase
        implements View.OnClickListener
{
    public static interface CurrentCollageIndexChangedListener
    {

        public abstract void onIndexChanged(int i);
    }

    public static interface PatternResIdChangedListener
    {

        public abstract void onPatternResIdChanged(int i);
    }

    public interface CurrentSquareIndexChangedListener {
        public abstract void onIndexChanged(int i);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {

        private static final String TAG = "ViewHolder";
        public ImageView imageView;
        private int item;

        public void setItem(int i)
        {
            item = i;
            imageView.setImageResource(item);
        }

        public ViewHolder(View view, boolean flag)
        {
            super(view);
            imageView = (ImageView)view.findViewById(R.id.image_view_collage_icon);
            if (flag)
            {
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }
        }
    }


    private static final String TAG = "Adapter";
    int colorDefault;
    int colorSelected;
    CurrentCollageIndexChangedListener currentIndexlistener;
    public int iconList[];
    boolean isPattern;
    PatternResIdChangedListener patternResIdListener;
    RecyclerView recylceView;
    View selectedListItem;
    int selectedPosition;
    boolean setSelectedView;

    public MyAdapter(int ai[], int i, int j, boolean flag, boolean flag1)
    {
        isPattern = false;
        setSelectedView = true;
        iconList = ai;
        colorDefault = i;
        colorSelected = j;
        isPattern = flag;
        setSelectedView = flag1;
    }

    public MyAdapter(int ai[], CurrentSquareIndexChangedListener currentcollageindexchangedlistener, int i, int j, boolean flag, boolean flag1)
    {
        isPattern = false;
        setSelectedView = true;
        iconList = ai;
        currentIndexlistener = (CurrentCollageIndexChangedListener) currentcollageindexchangedlistener;
        colorDefault = i;
        colorSelected = j;
        isPattern = flag;
        setSelectedView = flag1;
    }

    public int getItemCount()
    {
        return iconList.length;
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerview)
    {
        recylceView = recyclerview;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewholder, int i)
    {
        onBindViewHolder((ViewHolder)viewholder, i);
    }

    public void onBindViewHolder(ViewHolder viewholder, int i)
    {
        viewholder.setItem(iconList[i]);
        if (selectedPosition == i)
        {
            viewholder.itemView.setBackgroundColor(colorSelected);
            return;
        } else
        {
            viewholder.itemView.setBackgroundColor(colorDefault);
            return;
        }
    }

    public void onClick(View view)
    {
        int i = recylceView.getChildPosition(view);
        Object obj = recylceView.findViewHolderForPosition(selectedPosition);
        if (obj != null)
        {
            obj = ((RecyclerView.ViewHolder) (obj)).itemView;
            if (obj != null)
            {
                ((View) (obj)).setBackgroundColor(colorDefault);
            }
        }
        if (selectedListItem != null)
        {
            Log.d("Adapter", (new StringBuilder("selectedListItem ")).append(i).toString());
        }
        if (isPattern)
        {
            currentIndexlistener.onIndexChanged(iconList[i]);
        } else
        {
            currentIndexlistener.onIndexChanged(i);
        }
        if (setSelectedView)
        {
            selectedPosition = i;
            view.setBackgroundColor(colorSelected);
            selectedListItem = view;
        }
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewgroup, int i)
    {
        viewgroup = (ViewGroup) LayoutInflater.from(viewgroup.getContext()).inflate(R.layout.recycler_view_item, null);
        ViewHolder viewholder = new ViewHolder(viewgroup, isPattern);
        viewgroup.setOnClickListener(this);
        return viewholder;
    }


    public void setData(int ai[])
    {
        iconList = ai;
    }

    public void setSelectedPositinVoid()
    {
        selectedListItem = null;
        selectedPosition = -1;
    }
}
