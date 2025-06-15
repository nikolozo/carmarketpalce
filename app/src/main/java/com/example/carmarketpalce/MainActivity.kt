package com.example.carmarketpalce

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ItemTouchHelper
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var carsAdapter: CarsRecyclerAdapter
    private lateinit var carsList: MutableList<Cars>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Client.init()


        recyclerView = findViewById(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(this)


        carsList = mutableListOf()
        carsAdapter = CarsRecyclerAdapter(carsList) { selectedCar ->
            val intent = Intent(this, CarDetailsActivity::class.java).apply {
                putExtra("CAR_NAME", selectedCar.name)
                putExtra("CAR_MODEL", selectedCar.model)
                putExtra("CAR_YEAR", selectedCar.realiseData)
                putExtra("CAR_PRICE", selectedCar.price)
                putExtra("CAR_ENGINE", selectedCar.engine)
                putExtra("CAR_IMAGE", selectedCar.imageURL)
                putExtra("CAR_DES", selectedCar.description)
            }
            startActivity(intent)
        }

        recyclerView.adapter = carsAdapter


        fetchCars()


        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val car = carsList[position]

                when (direction) {
                    ItemTouchHelper.RIGHT -> {

                        Snackbar.make(recyclerView, "${car.name} added to favorites", Snackbar.LENGTH_SHORT).show()
                        carsAdapter.notifyItemChanged(position)
                    }

                    ItemTouchHelper.LEFT -> {

                        carsList.removeAt(position)
                        carsAdapter.notifyItemRemoved(position)
                        Snackbar.make(recyclerView, "${car.name} deleted", Snackbar.LENGTH_SHORT).show()
                    }
                }
            }
        }


        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }


    private fun fetchCars() {
        val request = Client.getReqResService().getCars()

        request.enqueue(object : Callback<List<Cars>> {
            override fun onResponse(call: Call<List<Cars>>, response: Response<List<Cars>>) {
                if (response.isSuccessful && response.body() != null) {
                    carsList.addAll(response.body()!!)
                    carsAdapter.updateList(carsList)
                } else {
                    Toast.makeText(this@MainActivity, "Unable to fetch data", Toast.LENGTH_SHORT).show()
                    Log.e("MainActivity", "Response error: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<List<Cars>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Connection error", Toast.LENGTH_SHORT).show()
                Log.e("MainActivity", "Failure: ${t.message}")
            }
        })
    }
}
