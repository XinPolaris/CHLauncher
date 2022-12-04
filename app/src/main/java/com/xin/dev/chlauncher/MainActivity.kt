package com.xin.dev.chlauncher

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.content.pm.ResolveInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_recycler_itemview.view.*

class MainActivity : Activity() {

    private val apps: List<ResolveInfo> by lazy {
        this.packageManager.queryIntentActivities(Intent(Intent.ACTION_MAIN, null).apply {
            addCategory(Intent.CATEGORY_LAUNCHER)
        }, 0)
    }

    private val adapter: RecyclerView.Adapter<*> =
        object : RecyclerView.Adapter<CustomViewHolder?>() {

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
                return CustomViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.view_recycler_itemview, parent, false)
                )
            }

            override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
                val resolveInfo = apps[position]
                holder.itemView.iv_icon.setImageDrawable(resolveInfo.loadIcon(packageManager))
                holder.itemView.tv_name.text = resolveInfo.loadLabel(packageManager)
                holder.itemView.setOnClickListener {
                    startActivity(
                        Intent().setComponent(
                            ComponentName(
                                resolveInfo.activityInfo.packageName,
                                resolveInfo.activityInfo.name
                            )
                        )
                    )
                }
            }

            override fun getItemCount(): Int {
                return apps.size
            }

        }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.apply {
            adapter = this@MainActivity.adapter
            layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
        }
    }


}