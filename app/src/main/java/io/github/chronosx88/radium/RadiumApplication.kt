package io.github.chronosx88.radium

import android.app.Application
import io.github.chronosx88.radium.utils.time.LocaleUtils

class RadiumApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        LocaleUtils.initFormatters(this.applicationContext) // FIXME this is bullshit, need to think about better solution
    }
}