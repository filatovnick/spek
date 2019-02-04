package org.spekframework.spek2.runtime.utils

import kotlin.test.*

// Based on https://kotlinlang.org/docs/tutorials/multiplatform-library.html
class Base64Test {
    @Test
    fun testEncodeString() {
        checkEncodeToString("Kotlin is awesome", "S290bGluIGlzIGF3ZXNvbWU=")
    }

    @Test
    fun testEncodePaddedStrings() {
        checkEncodeToString("", "")
        checkEncodeToString("1", "MQ==")
        checkEncodeToString("22", "MjI=")
        checkEncodeToString("333", "MzMz")
        checkEncodeToString("4444", "NDQ0NA==")
    }

    @Test
    fun testDecodeString() {
        checkDecodeFromString("S290bGluIGlzIGF3ZXNvbWU=", "Kotlin is awesome")
    }

    @Test
    fun testDecodePaddedStrings() {
        checkDecodeFromString("", "")
        checkDecodeFromString("MQ==", "1")
        checkDecodeFromString("MjI=", "22")
        checkDecodeFromString("MzMz", "333")
        checkDecodeFromString("NDQ0NA==", "4444")
    }

    @Test
    fun testDecodeInvalidStrings() {
        assertFailsWith<IllegalArgumentException>("Invalid Base64 encoded data.") { Base64.decodeString("a") }
        assertFailsWith<IllegalArgumentException>("Invalid Base64 encoded data.") { Base64.decodeString("aa") }
        assertFailsWith<IllegalArgumentException>("Invalid Base64 encoded data.") { Base64.decodeString("aaa") }
        assertFailsWith<IllegalArgumentException>("Invalid Base64 encoded data.") { Base64.decodeString("a===") }
        assertFailsWith<IllegalArgumentException>("Invalid Base64 encoded data.") { Base64.decodeString("aa&a") }
    }

    private fun checkEncodeToString(input: String, expectedOutput: String) {
        assertEquals(expectedOutput, Base64.encodeString(input))
    }

    private fun checkDecodeFromString(input: String, expectedOutput: String) {
        assertEquals(expectedOutput, Base64.decodeString(input))
    }
}
