package br.senai.sp.jandira.imcapp20_a.ui


import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

import android.widget.TextView
import android.widget.Toast
import br.senai.sp.jandira.imcapp20_a.R
import br.senai.sp.jandira.imcapp20_a.dao.BiometriaDao
import br.senai.sp.jandira.imcapp20_a.dao.UsuarioDao
import br.senai.sp.jandira.imcapp20_a.model.Biometria
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

        val button_inserir_dados: Button = findViewById(R.id.button_inserir_dados)

        button_inserir_dados.setOnClickListener {
            val dados = getSharedPreferences("dados_usuario", Context.MODE_PRIVATE)

        }
    }

    private fun salvar() {


        val dados = getSharedPreferences("dados_usuario", Context.MODE_PRIVATE)

        val email = dados.getString("email", "")
        val senha = dados.getString("senha", "")
        val nome = dados.getString("nome", "")
        val profissao = dados.getString("profissao", "")
//      val altura = dados.getFloat("altura", "0.0")
        val dataNascimento = dados.getString("idade", "")
        val sexo = dados.getString("sexo", "F")


        val usuario = Usuario(
            0,
            email.toString(),
            senha.toString(),
            nome.toString(),
            profissao.toString(),
            1.74,
            dataNascimento.toString(),
            'M'
        )


        val biometria = Biometria(
            0,
            et_peso.text.toString().toDouble(),
            spinner_novas_atividades.toString().toInt(),
            et_data_pesagem.text.toString(),
            usuario
        )

        val dao = BiometriaDao(this, biometria)
        dao.gravar()

        Toast.makeText(this, "Dados gravados com sucesso!!", Toast.LENGTH_SHORT).show()

        finish()
    }



}


