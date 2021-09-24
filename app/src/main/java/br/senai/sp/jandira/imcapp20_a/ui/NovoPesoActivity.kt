package br.senai.sp.jandira.imcapp20_a.ui


import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.TextView
import android.widget.Toast
import br.senai.sp.jandira.imcapp20_a.R
import br.senai.sp.jandira.imcapp20_a.dao.UsuarioDao
import br.senai.sp.jandira.imcapp20_a.model.Usuario
import kotlinx.android.synthetic.main.activity_dash_board.*
import kotlinx.android.synthetic.main.activity_novo_peso.*
import kotlinx.android.synthetic.main.activity_novo_peso.view.*
import kotlinx.android.synthetic.main.activity_novo_usuario.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class NovoPesoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_novo_peso)
        
    }

//    private fun salvar() {
//        val usuario = Usuario(
//            0,
//            et_peso.text.toString(),
////            spinner_novas_atividades.text.toString(),
//            et_data_pesagem.text.toString()
//
//            )
//
//        val dao = UsuarioDao(this, usuario)
//        dao.gravar()
//
//        Toast.makeText(this, "Dados gravados com sucesso!!", Toast.LENGTH_SHORT).show()
//
//        finish()
//    }

}


