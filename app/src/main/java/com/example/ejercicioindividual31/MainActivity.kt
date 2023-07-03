package com.example.ejercicioindividual31

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ejercicioindividual31.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appDb : AppDatabase
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appDb = AppDatabase.getDatabase(this)

        userAdapter = UserAdapter(emptyList()) // Inicializar el adaptador con una lista vacía

        binding.btnAdd.setOnClickListener{
            writeData()
        }

        binding.btnShow.setOnClickListener{
            readData()
        }
        binding.btnDelete.setOnClickListener{
            deleteAllData()
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = userAdapter // Asignar el adaptador al RecyclerView
    }

    private fun writeData(){

        val name = binding.textFieldNombre.editText?.text.toString()
        val userName = binding.textFieldNombreUsuario.editText?.text.toString()
        val age = binding.textFieldEdad.editText?.text.toString()

        if (name.isNotEmpty() && userName.isNotEmpty() && age.isNotEmpty()){

            val user = User(
                null,name,userName,age.toInt()
            )
            GlobalScope.launch (Dispatchers.IO){
                appDb.userDao().insert(user)
                readData()
            }

            binding.textFieldNombre.editText?.text?.clear()
            binding.textFieldNombreUsuario.editText?.text?.clear()
            binding.textFieldEdad.editText?.text?.clear()

            Snackbar.make(binding.root, "Datos guardados correctamente...", Snackbar.LENGTH_SHORT).show()

        }else{
            Snackbar.make(binding.root, "Por favor ingrese datos...", Snackbar.LENGTH_SHORT).show()
        }

    }

    private fun readData() {
        GlobalScope.launch(Dispatchers.Main) {
            val userList = withContext(Dispatchers.IO) {
                appDb.userDao().getAll()
            }
            userAdapter.setData(userList)

        }
    }

    private fun deleteAllData(){

        GlobalScope.launch(Dispatchers.IO){
            appDb.userDao().deleteAll()
            readData()
        }
    }


}