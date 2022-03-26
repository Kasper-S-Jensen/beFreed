package io.github.KasperSJensen.beFreed

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pokemonList: RecyclerView
        val pokemonAdapter: CelebrityAdapter

        pokemonList = findViewById(R.id.recyclerview)
        pokemonList.hasFixedSize()
        pokemonList.layoutManager= LinearLayoutManager(this)

        var celebrities = ArrayList<Celebrity>()
        celebrities.add(Celebrity("kenobi",R.drawable.obiwan))
        celebrities.add(Celebrity("jens",R.drawable.obiwan))
        celebrities.add(Celebrity("bob",R.drawable.obiwan))
        celebrities.add(Celebrity("hans",R.drawable.obiwan))
        celebrities.add(Celebrity("kennet",R.drawable.obiwan))
        celebrities.add(Celebrity("kenobi",R.drawable.obiwan))
        celebrities.add(Celebrity("jens",R.drawable.obiwan))
        celebrities.add(Celebrity("bob",R.drawable.obiwan))
        celebrities.add(Celebrity("hans",R.drawable.obiwan))
        celebrities.add(Celebrity("kennet",R.drawable.obiwan))
        celebrities.add(Celebrity("kenobi",R.drawable.obiwan))
        celebrities.add(Celebrity("jens",R.drawable.obiwan))
        celebrities.add(Celebrity("bob",R.drawable.obiwan))
        celebrities.add(Celebrity("hans",R.drawable.obiwan))
        celebrities.add(Celebrity("kennet",R.drawable.obiwan))
        celebrities.add(Celebrity("kenobi",R.drawable.obiwan))
        celebrities.add(Celebrity("jens",R.drawable.obiwan))
        celebrities.add(Celebrity("bob",R.drawable.obiwan))
        celebrities.add(Celebrity("hans",R.drawable.obiwan))
        celebrities.add(Celebrity("kennet",R.drawable.obiwan))
        pokemonAdapter = CelebrityAdapter(celebrities);
        pokemonList.adapter=pokemonAdapter

        pokemonAdapter.setOnClickListener { celebrity: Celebrity ->
            Toast.makeText(
                this,
                celebrity.name,
                Toast.LENGTH_SHORT
            ).show()
        }

    }


}