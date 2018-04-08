package com.github.abdularis.pivsample

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.abdularis.piv.ScrollTransformImageView
import com.github.abdularis.piv.transformer.ViewTransformer

class ImageListAdapter(val itemLayout : Int) : RecyclerView.Adapter<ImageListAdapter.ViewHolder>() {

    val items : IntArray = intArrayOf(
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3,
            R.drawable.img4,
            R.drawable.img5,
            R.drawable.img6,
            R.drawable.img7,
            R.drawable.img8
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(itemLayout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.image.setImageResource(items[position])
    }

    class ViewHolder(v : View) : RecyclerView.ViewHolder(v) {
        val image = v.findViewById<ScrollTransformImageView>(R.id.image)
    }

}