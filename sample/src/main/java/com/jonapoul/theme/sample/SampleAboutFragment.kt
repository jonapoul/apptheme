package com.jonapoul.theme.sample

import com.jonapoul.about.AboutFragment
import com.jonapoul.about.AboutItem
import com.jonapoul.about.AboutSection

class SampleAboutFragment : AboutFragment(
    sections = listOf(
        AboutSection.fromBuildInfo(
            versionName = BuildConfig.VERSION_NAME,
            versionCode = BuildConfig.VERSION_CODE,
            buildType = BuildConfig.BUILD_TYPE,
            buildTimeMs = BuildConfig.BUILD_TIME_MS
        ),
        AboutSection(
            title = R.string.about_support,
            items = listOf(
                AboutItem.fromGithub("https://github.com/jonapoul/apptheme"),
                AboutItem.fromReddit("https://reddit.com/r/androiddev"),
            )
        )
    )
)
