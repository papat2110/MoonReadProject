package com.example.moonreadproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_more_info.*
import kotlinx.android.synthetic.main.activity_show_all_book.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MoreInfo : AppCompatActivity() {
    val bookClient = BookAPI.create()

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when(item.itemId){
            R.id.home->{
                val intent = Intent(this,MainCustomer::class.java)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
            R.id.cart->{
                val intent = Intent(this,MainCustomer::class.java)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
            R.id.mail->{
                val intent = Intent(this,MainCustomer::class.java)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
            else -> return@OnNavigationItemSelectedListener false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_info)

        var customer_id = intent?.getStringExtra("customer_id")
        var book_id = intent?.getStringExtra("book_id")
        var book_name = intent?.getStringExtra("book_name")
        var img_book = intent?.getStringExtra("img_book")
        var book_price = intent?.getStringExtra("book_price")
        var book_detail = intent?.getStringExtra("book_detail")
        var book_type = intent?.getStringExtra("book_type")

        more_book_id.setText(book_id)
        //more_img_info.setImageURI(img_book)
        more_cus_id.setText(customer_id)
        Glide.with(this).load(img_book).into(more_img_info)
        more_book_name.setText(book_name)
        more_book_detail.setText(book_detail)
        more_book_price.setText(book_price)

        Toast.makeText(applicationContext,"Moreinfo "+customer_id,Toast.LENGTH_LONG).show()

        val actionBar = supportActionBar
        actionBar!!.title = "More Information"

        button_nav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    fun createOrder(v:View){
        val serv : OrderAPI = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OrderAPI::class.java)

        var total_price:Int = 0
        var order_date:String = "h"
        var address:String = "k"
        var status:String = "order"

        Toast.makeText(applicationContext,"Hello", Toast.LENGTH_SHORT).show()

        serv.insertOrder(
            more_cus_id.text.toString().toInt(),
            total_price,
            order_date,
            address,
            status
        ).enqueue(object : Callback<Order> {
            // FOR PHP Callback<List<mapMovieDB>>
            override fun onResponse(call: Call<Order>, response: retrofit2.Response<Order>) {
                if (response.isSuccessful) {
                    Toast.makeText(applicationContext,"Successfully Inserted", Toast.LENGTH_SHORT).show()
                    finish()
                }else{
                    Toast.makeText(applicationContext,"Error ", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<Order>, t: Throwable) {
                Toast.makeText(applicationContext,"Error onFailure " + t.message, Toast.LENGTH_LONG).show()
            }
        })
    }




}


