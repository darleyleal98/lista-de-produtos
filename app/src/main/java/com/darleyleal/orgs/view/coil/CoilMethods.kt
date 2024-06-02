package com.darleyleal.orgs.view.coil

import android.content.Context
import android.os.Build
import android.widget.ImageView
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.load
import com.darleyleal.orgs.R

class CoilMethods {
    companion object {
        fun ImageView.carregarImagem(url: String? = null, context: Context) {
            val imageLoader = ImageLoader.Builder(context)
                .components {
                    if (Build.VERSION.SDK_INT >= 28) {
                        add(ImageDecoderDecoder.Factory())
                    } else {
                        add(GifDecoder.Factory())
                    }
                }
                .build()
            load(url, imageLoader) {
                fallback(R.drawable.erro)
                error(R.drawable.erro)
                placeholder(R.drawable.loading)
            }
        }
    }
}