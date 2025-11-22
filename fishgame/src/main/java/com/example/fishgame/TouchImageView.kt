package com.example.fishgame

import android.R.attr.height
import android.R.attr.width
import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

class TouchImageView(context: Context, attrs: AttributeSet?) : AppCompatImageView(context, attrs) {

    var onValidClick: (() -> Unit)? = null

    override fun onTouchEvent(event: android.view.MotionEvent): Boolean {
        val x = event.x.toInt()
        val y = event.y.toInt()

        val drawable = drawable ?: return false
        val bitmap = (drawable as BitmapDrawable).bitmap

        val scaleX = bitmap.width.toFloat() / width
        val scaleY = bitmap.height.toFloat() / height
        val bitmapX = (x * scaleX).toInt()
        val bitmapY = (y * scaleY).toInt()

        if (bitmapX in 0 until bitmap.width && bitmapY in 0 until bitmap.height) {
            val pixel = bitmap.getPixel(bitmapX, bitmapY)
            val alpha = Color.alpha(pixel)
            if (alpha > 0) {
                onValidClick?.invoke()
                return true
            }
        }
        return false
    }
}