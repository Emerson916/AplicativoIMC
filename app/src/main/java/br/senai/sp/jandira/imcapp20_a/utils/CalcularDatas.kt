package br.senai.sp.jandira.imcapp20_a.utils

import android.util.Log
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

fun obterDiferencaEntreDatasEmAnos(dataInicio : String) : String{
    var hoje: LocalDate = LocalDate.now()

    val dataIni = LocalDate.parse(dataInicio, DateTimeFormatter.ofPattern("dd/MM/yyyy"))

//    var ano = dataInicio.substring(0,3).toInt()
//    var mes = dataInicio.substring(5,6).toInt()
//    var dia = dataInicio.substring(8,9).toInt()

//    var nascimento = LocalDate.of(ano, mes, dia)

    var idade = Period.between(dataIni, hoje)

    return idade.years.toString()
}