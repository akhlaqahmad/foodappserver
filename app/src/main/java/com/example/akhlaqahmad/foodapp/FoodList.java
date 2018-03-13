package com.example.akhlaqahmad.foodapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.akhlaqahmad.foodapp.Interface.ItemClickListener;
import com.example.akhlaqahmad.foodapp.Model.Food;
import com.example.akhlaqahmad.foodapp.ViewHolder.FoodViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class FoodList extends AppCompatActivity {

    FirebaseRecyclerAdapter <Food,FoodViewHolder> adapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference foodList;

    String CategoryId = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        //fireBase
        database = FirebaseDatabase.getInstance();
        foodList = database.getReference("Foods");

        recyclerView = (RecyclerView)findViewById(R.id.recycler_food);

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //get intent
        if(getIntent() !=null)
            CategoryId = getIntent().getStringExtra("CategoryId");
        if (!CategoryId.isEmpty() && CategoryId != null)
        {
            loadListFood(CategoryId);
        }
    }

    private void loadListFood(String categoryId) {
        adapter = new FirebaseRecyclerAdapter<Food,FoodViewHolder>(Food.class, R.layout.food_item, FoodViewHolder.class,
                foodList.orderByChild("menuId").equalTo(categoryId)//like : Select * FROM foods where MenuId='categoryId'
        ) {

            @Override
            protected void populateViewHolder(FoodViewHolder foodViewHolder, Food food, int i) {
                foodViewHolder.food_name.setText(food.getName());
                Picasso.with(getBaseContext()).load(food.getImage())
                        .into(foodViewHolder.food_image);
                final Food local = food;

                foodViewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void OnClick(View view, int position, boolean isLongClick) {
                       Intent foodDetail = new Intent(FoodList.this,FoodDetail.class);
                       foodDetail.putExtra("FoodId",adapter.getRef(position).getKey());
                       startActivity(foodDetail);
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
    }
}
