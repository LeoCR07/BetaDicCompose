package com.example.betadiccompose.Domain.Provider

import com.example.betadiccompose.data.network.model.DataWorld
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList
import kotlin.random.Random

class GameProvider @Inject constructor() {

    var currentID = 0
    var currentImg = ""

    val lst:List<DataWorld> = emptyList() //Lista completa de palabras

    val lstEasy:List<DataWorld> = emptyList() //Lista de los botones de easy


    fun SoundChoose(lista:List<DataWorld>): ArrayList<DataWorld> {

        var listaWord :ArrayList<DataWorld> = ArrayList<DataWorld>()
        val end = lista[lista.size-1].id
        val start = lista[0].id

        var lst:ArrayList<Int> = ArrayList() //Lista de los 6 ids
        //  var id = 0      //Id respuesta

        //Genera el id principal
        var id = (lista[0].id until lista.size).random()
        lst.add(id) //lo guardo

        //Generar 5 id mas diferentes paras las opciones
        do{

            var contandor = 0
            var valor = Random.nextInt(start,end)

            for (e in lst){
                if(e!=valor){
                    contandor++
                }
            }

            if(contandor == lst.size){
                lst.add(valor)
            }

        }while (lst.size<6)

        for (i in 0 until lst.size){
            for (j in 0 until  lista.size){

                if(lst[i] == lista[j].id){
                    listaWord.add(lista[j])
                }
            }
        }


        return listaWord
    }

    public fun MakeEasy(lista:List<DataWorld>) :List<DataWorld>{

        val end = lista[lista.size-1].id
        val start = lista[0].id

        var listaWord :ArrayList<DataWorld> = ArrayList<DataWorld>()
        var lst:ArrayList<Int> = ArrayList() //Lista de los 4 ids
      //  var id = 0      //Id respuesta

        //Genera el id principal
       var id = (lista[0].id until lista.size).random()
        lst.add(id) //lo guardo

        //Generar 5 id mas diferentes paras las opciones
        do{

            var contandor = 0
            var valor = Random.nextInt(start,end)

            for (e in lst){

                if(e!=valor){
                    contandor++
                }
            }

            if(contandor == lst.size){
                lst.add(valor)
            }

        }while (lst.size<4)


        for (i in 0 until lst.size){
            for (j in 0 until  lista.size){

                if(lst[i] == lista[j].id){
                    listaWord.add(lista[j])
                }

                if(i == 0 && j == 0 ){
                    currentID = id
                    //println("la respuesta es ${lista[lst[i]].World_1}")

                }
            }
        }

       // println("Game provider")
       // println("tama??o ${listaWord.size}")
       // return RandomText(listaWord)
       return listaWord
    }

    public fun MakeWrongWritten(lista:List<DataWorld>):List<DataWorld>{


        val end = lista[lista.size-1].id
        val start = lista[0].id

        println("$start --  $end")
        var listaWord :ArrayList<DataWorld> = ArrayList<DataWorld>()
        var lst:ArrayList<Int> = ArrayList() //Lista de los 4 ids
        var id = 0      //Id respuesta

        //Genero el id principal
        //id = Random.nextInt(lista[0].id, lista.size)
        id = (lista[0].id..lista.size-1).random()
        println()
        lst.add(id) //lo guardo

        //Generar 3 id mas diferentes paras las demas opciones
        do{
            var contandor = 0
            var valor = Random.nextInt(start,end)

            for (e in lst){

                if(e!=valor){
                    contandor++
                }
            }

            if(contandor == lst.size){
                lst.add(valor)
            }

        }while (lst.size<4)


        //Creo 4 objetos DataWord en base a a lista de los id
        for (i in 0 until lst.size){
            for (j in 0 until  lista.size){
                if(lst[i] == lista[j].id){

                    println("nombre: ${lista[j].World_1}")
                    if(i>=1){
                        listaWord.add(

                            DataWorld(
                                id=lista[j].id,
                                World_2 = lista[j].World_2,
                                World_1 = GenerarTexto(Random.nextInt(0,4),lista[j].World_1),
                                Img = lista[j].Img))

                    }else if (i==0){
                        listaWord.add(lista[j])
                    }

                }
            }
        }

        return listaWord
    }
    
    fun GenerarTexto(i:Int,palabra:String): String {
      val valor =  when(i){
            0->EliminarWord(palabra)
            1->AgregarWord(palabra)
            2->RemplazarVowel(palabra)
            3->ReemplazarWord(palabra)
          else-> ""
        }

        return valor
    }
    
    private fun ReemplazarWord(valor: String):String {

        val lstalphabet:List<String> = listOf("q","w","r","t","y","p","s","d","f","g","h","j","k"
            ,"l","??","z","x","c","v","b","n","m")

        var total = ""
        var palabra = valor.lowercase().trim()

        var lstconsonantWord:ArrayList<Char> = arrayListOf()
        var lstconsonantfilter:ArrayList<String> = arrayListOf()
        var lstconsonantIndex:ArrayList<Int> = arrayListOf()

        var indice = 0

        //To know if it has a consonante
        for (i in 0..palabra.length-1) {
            for(j in 0..lstalphabet.count()-1){
                if(palabra[i]+"" == lstalphabet[j]){
                    lstconsonantWord.add(palabra[i])
                    lstconsonantIndex.add(i)
                }
            }
        }

        if(lstconsonantIndex.isNotEmpty() ){
            //Take the consonant
            val aux = Random.nextInt(0,lstconsonantIndex.count())
            indice = lstconsonantIndex[aux]

            //filter the consonant
            for(j in 0..lstalphabet.count()-1){
                if(palabra[indice]+"" != lstalphabet[j]){
                    lstconsonantfilter.add(lstalphabet[j])
                }
            }

            //chose the new vowel
            val temp = Random.nextInt(0,lstconsonantfilter.count())
            val vowel = lstconsonantfilter[temp]

            //iterate and show the word with the changes
            for(i in 0..palabra.length-1){
                if(indice == i){
                    total+=vowel
                }else{
                    total+=palabra[i]
                }

            }

        }else{
            println("Cambiar de metodo")
        }

        return total
    }

    private fun EliminarWord(valor: String) :String{

        var palabra = valor.trim()
        var indice = Random.nextInt(0, palabra.length)
        var total = ""

        for (i in 0..palabra.length - 1) {
            if (indice != i) {
                total+=palabra[i]
            }
        }

        return total
    }

    private fun AgregarWord(valor: String):String {

        var palabra = valor.trim()
        var indice = Random.nextInt(0, palabra.length)
        var total = ""

        for (i in 0..palabra.length - 1) {
            if (indice == i) {
                total+=palabra[i]
            }
            total+=palabra[i]
        }
        return total
    }

    private fun RemplazarVowel(valor: String): String {

        val lstvowels :List<String> = listOf("a","e","i","o","u")

        var palabra = valor.lowercase().trim()
        var total = ""
        var lstvowelsWord:ArrayList<Char> = arrayListOf()
        var lstvowefilter:ArrayList<String> = arrayListOf()
        var lstvowelsIndex:ArrayList<Int> = arrayListOf()

        var indice = 0

        //To know if it has a vowel
        for (i in 0..palabra.length-1) {
            for(j in 0..lstvowels.count()-1){
                if(palabra[i]+"" == lstvowels[j]){
                    lstvowelsWord.add(palabra[i])
                    lstvowelsIndex.add(i)
                }
            }
        }

        if(lstvowelsIndex.isNotEmpty()){
            //Take the vowel
            val aux = Random.nextInt(0,lstvowelsIndex.count())
            indice = lstvowelsIndex[aux]

            //filter the vowels
            for(j in 0..lstvowels.count()-1){
                if(palabra[indice]+"" != lstvowels[j]){
                    lstvowefilter.add(lstvowels[j])
                }
            }

            //chose the new vowel
            val temp = Random.nextInt(0,lstvowefilter.count())
            val vowel = lstvowefilter[temp]

            //iterate and show the word with the changes
            for(i in 0..palabra.length-1){
                if(indice == i){
                    total += vowel
                }else{
                    total += palabra[i]
                }

            }

        }else{
            println("Cambiar de metodo")
        }

        return total
    }

    private fun RandomText(lst:List<DataWorld>): MutableList<DataWorld> {

        var lista = lst.toMutableList()
        //var lista = emptyList<DataWorld>()

        var randomvalue = 0
        var temp = ""
        var aux:DataWorld

        for (i in (0 until lst.size)){
            randomvalue = (lista.indices).random()
            aux = lista[randomvalue]
            lista[randomvalue] = lista[i]
            lista[i] = aux
        }

       return lista

    }


}