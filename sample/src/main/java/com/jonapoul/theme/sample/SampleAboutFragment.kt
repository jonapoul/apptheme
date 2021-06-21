package com.jonapoul.theme.sample

import com.jonapoul.about.AboutFragment
import com.jonapoul.about.AboutItem
import com.jonapoul.about.AboutSection

class SampleAboutFragment : AboutFragment(
    sections = listOf(
        AboutSection(
            title = R.string.about_build,
            items = listOf(
                AboutItem.fromVersion(BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE),
                AboutItem.fromBuildType(BuildConfig.BUILD_TYPE),
                AboutItem.fromBuildTimeMs(BuildConfig.BUILD_TIME_MS),
            )
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
