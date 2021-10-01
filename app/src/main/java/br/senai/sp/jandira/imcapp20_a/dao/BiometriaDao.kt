package br.senai.sp.jandira.imcapp20_a.dao

import android.content.ContentValues
import android.content.Context
import br.senai.sp.jandira.imcapp20_a.model.Biometria
import br.senai.sp.jandira.imcapp20_a.utils.converteByteArrayParaBitMap
import br.senai.sp.jandira.imcapp20_a.utils.converterBitMapParaBase64

import br.senai.sp.jandira.imcapp20_a.utils.converterBitmapParaByteArray
import br.senai.sp.jandira.imcapp20_a.utils.obterDiferencaEntreDatasEmAnos

class BiometriaDao(val context: Context, val biometria: Biometria?) {

    val dbHelper = ImcDataBase.getDatabase(context)

    public fun gravar() {

        // *** obter uma instância do banco para escrita
        val db = dbHelper.writableDatabase

        // *** Criar os valores que serão inseridos no banco
        val dados = ContentValues()
        dados.put("peso", biometria!!.peso)
        dados.put("nivel_atividade", biometria.nivelAtiviade)
        dados.put("data_pesagem", biometria.dataPesagem.toString())
        dados.put("id_usuario", biometria.usuario.toString())

        // *** Executar o comando de gravação
        db.insert("tb_biometria", null, dados)

        db.close()
    }


    public fun autenticar(peso:String, nivel_atividade:String):Boolean{

        val dados = context.getSharedPreferences("dados_usuario", Context.MODE_PRIVATE)
        val db = dbHelper.readableDatabase

        val campos = arrayOf(
            "peso",
            "nivel_atividade"
        )

        val idUsuario = dados.getInt("id", 0)

        // *** Defininr o filtro da consulta
        // *** Construindo o filtro
        // *** "WHERE email = ? AND senha = ?"
        val filtro = "peso = ? AND nivel_atividade = ?"

        // *** Criando os argumentos do filtro
        // *** Dizendo quais valores que deverão substituir o "?" no filtro
        val argumentos = arrayOf(peso, nivel_atividade)

        // *** Executar a consulta e obter o resultado em um objeto "cursor"
        val cursor = db.query(
            "tb_biometria",
            campos,
            filtro,
            argumentos,
            null,
            null,
            null
        )

//        Log.i("XPTO", "Linhas ${cursor.count.toString()}")

        // *** Guardando a quantidade de linhas obtida na consulta
        val linhas = cursor.count

        var autenticado = false

        if (linhas > 0 ){
            autenticado = true
            cursor.moveToFirst()

            val pesoIndex = cursor.getColumnIndex("peso")
            val nivelAtividadeIndex = cursor.getColumnIndex("nivel_atividade")
            val dataPesagemIndex = cursor.getColumnIndex("data_pesagem")
            val idUsuarioIndex = cursor.getColumnIndex("id_usuario")


            // *** Criação/atualização do sharedPreferences
            val dados = context.getSharedPreferences("dados_biometria", Context.MODE_PRIVATE)
            val editor = dados.edit()
            editor.putInt("peso", cursor.getInt(pesoIndex))
            editor.putInt("nivel_atividade", cursor.getInt(nivelAtividadeIndex))

            editor.putString("data_pesagem", cursor.getString(dataPesagemIndex))
            editor.putInt("id_usuario", cursor.getInt(idUsuarioIndex))


        }

        db.close()
        return autenticado
    }
}