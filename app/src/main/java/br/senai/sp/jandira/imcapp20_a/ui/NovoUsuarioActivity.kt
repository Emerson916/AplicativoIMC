package br.senai.sp.jandira.imcapp20_a.ui

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import br.senai.sp.jandira.imcapp20_a.R
import br.senai.sp.jandira.imcapp20_a.dao.UsuarioDao
import br.senai.sp.jandira.imcapp20_a.model.Usuario
import kotlinx.android.synthetic.main.activity_novo_usuario.*
import java.util.*

const val CODE_IMAGE = 100

class NovoUsuarioActivity : AppCompatActivity() {

    var imageBitmap: Bitmap? = null
//    lateinit var imgProfile: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_novo_usuario)

        // Detectar o click no texto "trocar foto"
        tv_trocar_foto.setOnClickListener{
            abrirGaleria()
        }

        //Criando um calendário
        val calendario = Calendar.getInstance()
        val ano = calendario.get(Calendar.YEAR)
        val mes = calendario.get(Calendar.MONTH)
        val dia = calendario.get(Calendar.DAY_OF_MONTH)

        //Abrir um componenete DatePickerDialog --- caixa de dialogo
        et_data_nascimento.setOnClickListener {
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{view, _ano, _mes, _dia ->
                var diaZero = "$_dia"
                var mesZero = "$_mes"

                    if (_dia < 10) {
                        diaZero = "0$_dia"
                    }

                    if (_mes < 9) {
                        mesZero = "0${_mes + 1 }"
                    }
                et_data_nascimento.setText("$diaZero/${mesZero}/$_ano")
            }, ano, mes, dia)
            dpd.show()
        }

        bt_gravar.setOnClickListener {
            // *** Criar o sharedPreferences
//            val dados = getSharedPreferences("dados_usuario", Context.MODE_PRIVATE)
//
//            val editor = dados.edit()
//            editor.putString("nome", et_nome.text.toString())
//            editor.putString("profissao", et_profissao.text.toString())
//            editor.putInt("peso", et_peso.text.toString().toInt())
//            editor.putInt("idade", et_data_nascimento.text.toString().toInt())
//            editor.putString("email", et_email.text.toString())
//            editor.putString("senha", et_senha.text.toString())
//            editor.apply()

            /** Gravar um novo usuario no Banco de Dados**/

            val usuario = Usuario(
            0,
            et_email.text.toString(),
            et_senha.text.toString(),
            et_nome.text.toString(),
            et_profissao.text.toString(),
            et_altura.text.toString().toDouble(),
            et_data_nascimento.text.toString(),
            'M',
            imageBitmap)

            val dao = UsuarioDao(this, usuario)
            dao.gravar()

            Toast.makeText(this, "Dados gravados com sucesso!!", Toast.LENGTH_SHORT).show()

            finish()

        }

    }

    private fun abrirGaleria() {
        // Chamando a galeria de imagens

        val intent = Intent(Intent.ACTION_GET_CONTENT)

        //Definir qual o tipo de conteúdo que deverá ser obtido
        intent.type = "image/*"

        //Iniciar a Activity, mas neste caso nós queremos que
        // está Activity retorne alfo pra gente, a imagem

        startActivityForResult(Intent.createChooser(intent, "Escolha uma foto"), CODE_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

       if (requestCode == CODE_IMAGE && resultCode == -1){

           //Recuperando o resultado em um BitMap
           val stream = contentResolver.openInputStream(data!!.data!!)

           // Transformando o resultado em um Bitmap
           imageBitmap = BitmapFactory.decodeStream(stream)

           //Colocar a imagem no ImageView
           img_profile.setImageBitmap(imageBitmap)
       }
    }
}

