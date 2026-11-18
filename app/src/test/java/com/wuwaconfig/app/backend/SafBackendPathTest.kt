package com.wuwaconfig.app.backend

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class SafBackendPathTest {
    private val samsungGameRoot = "/storage/emulated/0/Android/data/com.kurogame.wutheringwaves.samsung"

    @Test
    fun `accepts the selected Samsung game package tree`() {
        assertTrue(isMatchingGameTreeRoot(samsungGameRoot, samsungGameRoot))
    }

    @Test
    fun `rejects a saved tree from the Google Play package`() {
        val googlePlayRoot = "/storage/emulated/0/Android/data/com.kurogame.wutheringwaves.global"
        assertFalse(isMatchingGameTreeRoot(googlePlayRoot, samsungGameRoot))
    }

    @Test
    fun `rejects a missing or unrelated tree grant`() {
        assertFalse(isMatchingGameTreeRoot(null, samsungGameRoot))
        assertFalse(isMatchingGameTreeRoot("/storage/emulated/0/Download", samsungGameRoot))
    }

    @Test
    fun `normalizes a trailing slash without accepting a different package`() {
        assertTrue(isMatchingGameTreeRoot("$samsungGameRoot/", samsungGameRoot))
        assertFalse(isMatchingGameTreeRoot("$samsungGameRoot/../global", samsungGameRoot))
    }
}
