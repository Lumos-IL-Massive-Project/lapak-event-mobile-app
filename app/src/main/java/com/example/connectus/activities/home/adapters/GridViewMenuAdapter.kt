package com.example.connectus.activities.home.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.connectus.R
import com.example.connectus.activities.allcategories.AllCategoriesActivity
import com.example.connectus.activities.home.models.MenuData
import com.example.connectus.activities.productlist.ProductListActivity
import com.example.connectus.utils.startDynamicActivity

class GridViewMenuAdapter(private val context: Context, private val menuList: List<MenuData>) :
    BaseAdapter() {
    override fun getCount(): Int {
        return menuList.size
    }

    override fun getItem(position: Int): Any {
        return menuList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view =
            convertView ?: LayoutInflater.from(context)
                .inflate(R.layout.home_menu_item, parent, false)
        val item = getItem(position) as MenuData
        val imageView = view.findViewById<ImageView>(R.id.menuIcon)
        val textView = view.findViewById<TextView>(R.id.menuLabel)

        imageView.setImageResource(item.image)
        textView.text = item.name

        view.setOnClickListener {
            if (item.name == "Menu Lainnya") {
                startDynamicActivity(context, AllCategoriesActivity::class.java)
            } else {
                startDynamicActivity(context, ProductListActivity::class.java)
            }
        }
        return view
    }
}