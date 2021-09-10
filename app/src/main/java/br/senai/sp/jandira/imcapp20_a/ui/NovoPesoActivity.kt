package br.senai.sp.jandira.imcapp20_a.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.senai.sp.jandira.imcapp20_a.R
import kotlinx.android.synthetic.main.activity_novo_peso.*

class NovoPesoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_novo_peso)

        button_voltar.setOnClickListener{
            setContentView(R.layout.activity_dash_board)
        }


    }
}