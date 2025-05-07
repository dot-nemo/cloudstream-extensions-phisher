package com.nemo


import android.content.SharedPreferences
import com.nemo.StreamPlayExtractor.invokeVidSrcXyz
import com.lagradost.cloudstream3.SubtitleFile
import com.lagradost.cloudstream3.argamap
import com.lagradost.cloudstream3.runAllAsync
import com.lagradost.cloudstream3.utils.AppUtils
import com.lagradost.cloudstream3.utils.ExtractorLink
import com.nemo.StreamPlayExtractor.invokeExtramovies

class StreamPlayTest(sharedPreferences:SharedPreferences?=null) : StreamPlay(sharedPreferences) {
    override var name = "StreamPlay-Test"
    override suspend fun loadLinks(
        data: String,
        isCasting: Boolean,
        subtitleCallback: (SubtitleFile) -> Unit,
        callback: (ExtractorLink) -> Unit
    ): Boolean {
        val res = AppUtils.parseJson<LinkData>(data)
        runAllAsync(
            {
                if (!res.isAnime) invokeExtramovies(
                    res.imdbId,
                    subtitleCallback,
                    callback
                )
            },
        )
        return true
    }

}