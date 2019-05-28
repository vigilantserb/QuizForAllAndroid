package com.stameni.com.quizforall

import android.app.Dialog
import android.content.Context
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget

object Tools {
    fun showCustomDialog(context: Context, layout: Int): Dialog {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) // before
        dialog.setContentView(layout)
        dialog.setCancelable(true)

        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window!!.attributes)
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT

        dialog.window!!.attributes = lp
        return dialog
    }

    fun showLoadingDialog(context: Context, layout: Int): Dialog {
        var dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(layout)
        dialog.setCancelable(false)

        //...initialize the imageView form infalted layout
        val gifImageView = dialog.findViewById(R.id.custom_loading_imageView) as ImageView

        /*
        it was never easy to load gif into an ImageView before Glide or Others library
        and for doing this we need DrawableImageViewTarget to that ImageView
        */
        val imageViewTarget = GlideDrawableImageViewTarget(gifImageView)

        //...now load that gif which we put inside the drawble folder here with the help of Glide

        Glide.with(context)
            .load(R.drawable.loading)
            .placeholder(R.drawable.loading)
            .centerCrop()
            .crossFade()
            .into(imageViewTarget)

        return dialog
    }
}