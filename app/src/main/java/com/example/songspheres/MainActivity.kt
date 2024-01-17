package com.example.songspheres

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.songspheres.adapter.CategoryAdapter
import com.example.songspheres.databinding.ActivityMainBinding
import com.example.songspheres.models.CategoryModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObjects

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var categoryAdapter: CategoryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getCategories()

    }

    fun getCategories(){
        FirebaseFirestore.getInstance().collection("category").get().addOnSuccessListener {
            val categoryList = it.toObjects(CategoryModel::class.java)
            setupCategoryRecyclerView(categoryList)
        }
    }

    fun setupCategoryRecyclerView(categoryList : List<CategoryModel>){
        categoryAdapter = CategoryAdapter(categoryList)
        binding.categoriesRecyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)
        binding.categoriesRecyclerView.adapter = categoryAdapter
    }
}