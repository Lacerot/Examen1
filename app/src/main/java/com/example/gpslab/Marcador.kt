package com.example.gpslab

data class Marcador(val id:String, val nombre:String, val fecha:String, val hora:String, val estado:String){
    constructor():this("","","","","")

    override fun toString(): String {
        return nombre
    }
}
