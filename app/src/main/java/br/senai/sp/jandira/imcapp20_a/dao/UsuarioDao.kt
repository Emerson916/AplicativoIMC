package br.senai.sp.jandira.imcapp20_a.dao

import android.content.ContentValues
import android.content.Context
import android.util.Log
import br.senai.sp.jandira.imcapp20_a.model.Usuario
import br.senai.sp.jandira.imcapp20_a.utils.*
import java.time.LocalDate
import java.time.Period

class UsuarioDao(val context: Context, val usuario: Usuario?) {

    val dbHelper = ImcDataBase.getDatabase(context)

    public fun gravar() {

        // *** obter uma instância do banco para escrita
        val db = dbHelper.writableDatabase

        // *** Criar os valores que serão inseridos no banco
        val dados = ContentValues()
        dados.put("nome", usuario!!.nome)
        dados.put("profissao", usuario.profissao)
        dados.put("email", usuario.email)
        dados.put("senha", usuario.senha)
        dados.put("altura", usuario.altura)
        dados.put("data_nascimento", usuario.dataNascimento.toString())
        dados.put("sexo", usuario.sexo.toString())
        dados.put("foto", converterBitmapParaByteArray(usuario.foto))

        // *** Executar o comando de gravação
        db.insert("tb_usuario", null, dados)

        db.close()
    }

    public fun autenticar(email: String, senha: String):Boolean{

        // *** obter uma instância de leitura do banco
        val db = dbHelper.readableDatabase

        // *** Determinar quais colunas da tabela
        // *** que nós queremos no resultado
        // *** vamos criar uma projeção
        val campos = arrayOf(
            "email",
            "senha",
            "nome",
            "profissao",
            "data_nascimento",
            "foto")

        // *** Defininr o filtro da consulta
        // *** Construindo o filtro
        // *** "WHERE email = ? AND senha = ?"
        val filtro = "email = ? AND senha = ?"

        // *** Criando os argumentos do filtro
        // *** Dizendo quais valores que deverão substituir o "?" no filtro
        val argumentos = arrayOf(email, senha)

        // *** Executar a consulta e obter o resultado em um objeto "cursor"
        val cursor = db.query(
            "tb_usuario",
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

            val emailIndex = cursor.getColumnIndex("email")
            val nomeIndex = cursor.getColumnIndex("nome")
            val profissaoIndex = cursor.getColumnIndex("profissao")
            val dataNascimentoIndex = cursor.getColumnIndex("data_nascimento")
            val fotoIndex = cursor.getColumnIndex("foto")

            val dataNascimento = cursor.getString(dataNascimentoIndex)

            // *** Criação/atualização do sharedPreferences
            val dados = context.getSharedPreferences("dados_usuario", Context.MODE_PRIVATE)
            val editor = dados.edit()
            editor.putString("nome", cursor.getString(nomeIndex))
            editor.putString("profissao", cursor.getString(profissaoIndex))
            editor.putString("idade", obterDiferencaEntreDatasEmAnos(dataNascimento))
            editor.putString("email", cursor.getString(emailIndex))
            editor.putInt("peso", 0)

            //Converter o ByteArray do banco em Bitmap
            var bitmap = converteByteArrayParaBitMap(cursor.getBlob(fotoIndex))

            editor.putString("foto", converterBitMapParaBase64(bitmap)) // converter em base 64
            editor.apply()



        }

        db.close()
        return autenticado

    }

}