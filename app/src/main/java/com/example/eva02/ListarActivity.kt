package com.example.eva02

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.eva02.databinding.ActivityListaPersonaBinding
import android.R

class ListarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListaPersonaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaPersonaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val listaPersonas = intent.getSerializableExtra("lista")
                as ArrayList<*>
        val  adapter = ArrayAdapter(
            this,
            R.layout.simple_list_item_1,
            listaPersonas)
        binding.lvperson.adapter = adapter
        }
    }
}