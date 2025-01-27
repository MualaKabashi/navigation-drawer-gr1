package com.cacttus.navigationdrawer_gr1.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.cacttus.navigationdrawer_gr1.R
import com.cacttus.navigationdrawer_gr1.model.Post

class PostAdapter(var context: Context, private var postList: List<Post>) : BaseAdapter() {
    var layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return postList.size
    }

    override fun getItem(position: Int): Any {
        return postList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, contextView: View?, parent: ViewGroup?): View {
        var rowView = layoutInflater.inflate(R.layout.post_item, parent, false)

        var tvId = rowView.findViewById<TextView>(R.id.tvId)
        var tvTitle = rowView.findViewById<TextView>(R.id.tvTitle)
        var tvUserId = rowView.findViewById<TextView>(R.id.tvUserId)
        var tvDescription = rowView.findViewById<TextView>(R.id.tvDescription)

        tvId.text = postList[position].id.toString()
        tvTitle.text = postList[position].title
        tvUserId.text = postList[position].userId.toString()
        tvDescription.text = postList[position].body

        return rowView
    }
}