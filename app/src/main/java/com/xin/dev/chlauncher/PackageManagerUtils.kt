package com.xin.dev.chlauncher

import android.content.Context
import android.content.Intent
import android.content.pm.ResolveInfo

/**
 *  Created by HuangXin on 2022/12/4.
 */
class PackageManagerUtils(val context: Context) {

    private val apps: List<ResolveInfo> by lazy {
        context.packageManager.queryIntentActivities(Intent(Intent.ACTION_MAIN, null).apply {
            addCategory(Intent.CATEGORY_LAUNCHER)
        }, 0)
    }

}