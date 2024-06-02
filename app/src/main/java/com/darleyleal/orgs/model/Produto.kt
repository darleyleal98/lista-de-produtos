package com.darleyleal.orgs.model

import android.os.Parcel
import android.os.Parcelable

data class Produto(
    var nome: String,
    var descricao: String,
    var valor: Double,
    val imagem: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readDouble(),
        parcel.readString()
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(nome)
        dest.writeString(descricao)
        dest.writeDouble(valor)
        dest.writeString(imagem)
    }

    companion object CREATOR : Parcelable.Creator<Produto> {
        override fun createFromParcel(parcel: Parcel): Produto {
            return Produto(parcel)
        }

        override fun newArray(size: Int): Array<Produto?> {
            return arrayOfNulls(size)
        }
    }
}
