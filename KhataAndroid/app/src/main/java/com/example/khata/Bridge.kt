package com.example.khata

import android.webkit.JavascriptInterface

/** Web page `AndroidDB.get()` / `AndroidDB.set(json)` se Room database tak. */
class Bridge(private val dao: KhataDao) {
    @JavascriptInterface fun get(): String = dao.get()?.json ?: ""

    @JavascriptInterface fun set(json: String) {
        val now = System.currentTimeMillis()
        dao.put(Snapshot(1, json, now))
        val last = dao.lastBackup()
        if (last == null || now - last.createdAt > 86_400_000L) {
            dao.addBackup(Backup(json = json, createdAt = now))
            dao.trim()
        }
    }
}
