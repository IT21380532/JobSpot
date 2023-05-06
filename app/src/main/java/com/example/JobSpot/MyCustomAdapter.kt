package com.example.JobSpot

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.JobSpot.mydata
import com.example.JobSpot.R

class MyCustomAdapter(val ctx:Context,val myDtaList: List<mydata>):BaseAdapter() {
    override fun getCount(): Int {
        return myDtaList.size
    }

    override fun getItem(position: Int): mydata {
        return myDtaList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var myConvertView= convertView
        if(myConvertView==null){
            myConvertView=LayoutInflater.from(ctx).inflate(R.layout.my_list,parent,false )
        }
        val currentItem = getItem(position)
        val imageItem = myConvertView?.findViewById<ImageView>(R.id.list_item_image)
        val titleItem= myConvertView?.findViewById<TextView>(R.id.list_item_title)
        val descItem= myConvertView?.findViewById<TextView>(R.id.list_item_desc)
        val noItem= myConvertView?.findViewById<TextView>(R.id.list_item_no)
        val salItem= myConvertView?.findViewById<TextView>(R.id.list_item_sal)


        imageItem?.setImageResource(currentItem.image)
        titleItem?.text= currentItem.title
        descItem?.text= currentItem.desc
        noItem?.text= currentItem.no
        salItem?.text= currentItem.sal

        return myConvertView!!
    }
}