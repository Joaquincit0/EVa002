package com.example.eva02

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CheckBox
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.eva02.common.AppMensaje
import com.example.eva02.common.TipoMensaje
import com.example.eva02.databinding.ActivityRegistroBinding

class RegistroActivity : AppCompatActivity(), View.OnClickListener, AdapterView.OnItemSelectedListener {
    private lateinit var binding: ActivityRegistroBinding
    private val listaUsuarios = ArrayList<String>()
    private val listaProgramas = ArrayList<String>()
    private val listafavoritos = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnAcceder.setOnClickListener(this)
        binding.chkSQL.setOnClickListener(this)
        binding.chkVScode.setOnClickListener(this)
        binding.chkAndroid.setOnClickListener(this)
        binding.chkOtro.setOnClickListener(this)

    }
        fun validarDatos(): Boolean {
            var respuesta = true

            val camposVacios = listOf(
                binding.txtnombres,
                binding.txtApellidos,
                binding.txtDni,
                binding.txtCelular,
                binding.txtEmail
            ).filter { it.text.toString().trim().isEmpty() }

            if (camposVacios.isNotEmpty()) {
                respuesta = false
                camposVacios.forEach { campo ->
                    campo.isFocusableInTouchMode = true
                    campo.requestFocus()
                }
            }

            return respuesta
        }
        fun validarProgramas():Boolean {
            var respuesta = true
            if(binding.radioGroup.checkedRadioButtonId == -1)
                respuesta = false
            return respuesta
        }
        fun validarColor():Boolean {
            var respuesta = false
            if(binding.rbAzul.isChecked ||
                binding.rbVerde.isChecked ||
                binding.rbRojo.isChecked||
                binding.rbOtro.isChecked)
                respuesta = true
            return respuesta
        }
        fun validarFormulario(): Boolean{
            var respuesta = false
            if(!validarDatos()){
                AppMensaje.mensaje(binding.root,
                    getString(R.string.valerrornomape), TipoMensaje.ERROR)
                respuesta = true
            }else if(!validarProgramas()){
                AppMensaje.mensaje(binding.root,
                    getString(R.string.valerrorprograma), TipoMensaje.ERROR)
                respuesta = true
            }else if(!validarColor()) {
                AppMensaje.mensaje(
                    binding.root,
                    getString(R.string.valerrorcolor), TipoMensaje.ERROR
                )
                respuesta = true
            }
            return  respuesta
        }
        private fun agregarProgramas(v: CheckBox) {
        if(v.isChecked)
            listaProgramas.add(v.text.toString())
        else
            listaProgramas.remove(v.text.toString())
        }
        override fun onClick(v: View) {
        if(v is CheckBox){
            agregarProgramas(v)
        } else {
            when(v.id){
                R.id.btnAcceder-> registrarPersona()
                                }
            }
        }

        fun obtenerPrograma(): String{
            var programas= ""
            for(programa in listaProgramas){
                programas += "$programa -"
            }
            return programas
        }
        fun obtenerColor(): String{
            return when(binding.radioGroup.checkedRadioButtonId){
                R.id.rbAzul ->  binding.rbAzul.text.toString()
                R.id.rbRojo ->  binding.rbRojo.text.toString()
                R.id.rbVerde ->  binding.rbVerde.text.toString()
                R.id.rbOtro ->  binding.rbOtro.text.toString()
                else -> ""
            }
        }
        fun setearControles(){
            binding.txtnombres.setText("")
            binding.txtApellidos.setText("")
            binding.txtDni.setText("")
            binding.txtEmail.setText("")
            binding.txtCelular.setText("")
            binding.txtOtros.setText("")
            binding.chkSQL.isChecked = false
            binding.chkAndroid.isChecked = false
            binding.chkVScode.isChecked = false
            binding.chkOtro.isChecked = false
            binding.radioGroup.clearCheck()
            binding.txtnombres.isFocusableInTouchMode=true
            binding.txtnombres.requestFocus()
            listaProgramas.clear()
        }
    private fun irListaPersonas() {
            val intentListaPersona = Intent(
                this, ListarActivity::class.java)
                .apply {
                    putExtra("lista", listaUsuarios)
                }
            startActivity(intentListaPersona)
    }

    private fun registrarPersona() {
            if(!validarFormulario()){
                var info =  binding.txtnombres.text.toString()+"-"+
                            binding.txtApellidos.text.toString()+"-"+
                            binding.txtDni.text.toString()+"-"+
                            binding.txtCelular.text.toString()+"-"+
                            binding.txtEmail.text.toString()+"-"+
                            binding.txtOtros.text.toString()+"-"+
                            obtenerPrograma()+"-"+
                            obtenerColor()+"-"
                        listaUsuarios.add(info)
                AppMensaje.mensaje(binding.root,
                    getString(R.string.mensajeregpersona),
                    TipoMensaje.CORRECTO)
                setearControles()
            }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        TODO("Not yet implemented")
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }


}

