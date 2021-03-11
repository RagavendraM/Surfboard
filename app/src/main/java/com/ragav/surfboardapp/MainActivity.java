package com.ragav.surfboardapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Custom ActionBar
//        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        getSupportActionBar().setCustomView(R.layout.custom_appbar);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        //RecyclerView
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        ConAdapter adapter = new ConAdapter(this);
        recyclerView.setAdapter(adapter);

        //BottomSheet
        BottomSheet bottomSheet = new BottomSheet();
        bottomSheet.show(getSupportFragmentManager(),"Fragment");
    }

    public static class ConAdapter extends RecyclerView.Adapter<ViewHolder>
    {
        private static final int LENGTH=18;
        private final String[] mProducts;
        private final String[] mProductsDesc;
        private final String[] mProductsPrice;
        private final String[] mProductsQty;
        private final Drawable[] pics;

        public ConAdapter(Context context)
        {
            Resources resources=context.getResources();
            mProducts=resources.getStringArray(R.array.product_array);
            mProductsDesc=resources.getStringArray(R.array.product_desc_array);
            mProductsPrice=resources.getStringArray(R.array.product_price_array);
            mProductsQty=resources.getStringArray(R.array.product_qty_array);
            TypedArray typedArray = resources.obtainTypedArray(R.array.product_icon_array);
            pics=new Drawable[typedArray.length()];
            for(int i=0;i<typedArray.length();i++)
            {
                pics[i]=typedArray.getDrawable(i);
            }
            typedArray.recycle();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()),parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.img.setImageDrawable(pics[position%pics.length]);
            holder.title.setText(mProducts[position%mProducts.length]);
            holder.desc.setText(mProductsDesc[position%mProductsDesc.length]);
            holder.qty.setText(mProductsQty[position%mProductsQty.length]);
            holder.price.setText(mProductsPrice[position%mProductsPrice.length]);
        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView img;
        public TextView title,desc,qty,price;
        public ViewHolder(LayoutInflater inflater,ViewGroup parent)
        {
            super(inflater.inflate(R.layout.recycler_item,parent,false));
            img=(ImageView)itemView.findViewById(R.id.img_item);
            title=(TextView)itemView.findViewById(R.id.title_item);
            desc=(TextView)itemView.findViewById(R.id.subtitle_item);
            qty=(TextView)itemView.findViewById(R.id.qty_item);
            price=(TextView)itemView.findViewById(R.id.price_item);
        }
    }
}
