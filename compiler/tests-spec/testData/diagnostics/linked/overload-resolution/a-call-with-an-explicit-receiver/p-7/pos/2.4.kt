// !LANGUAGE: +NewInference
// !DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 0.1-261
 * PLACE: overload-resolution, a-call-with-an-explicit-receiver -> paragraph 7 -> sentence 2
 * RELEVANT PLACES: overload-resolution, a-call-with-an-explicit-receiver -> paragraph 7 -> sentence 3
 * overload-resolution, a-call-with-an-explicit-receiver -> paragraph 7 -> sentence 4
 * overload-resolution, a-call-with-an-explicit-receiver -> paragraph 7 -> sentence 5
 * overload-resolution, a-call-with-an-explicit-receiver -> paragraph 11 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: sets of local, explicitly imported, declared in the package scope and star-imported extension callables
 */

// FILE: Extensions.kt
package libPackage

public fun String?.orEmpty(): String = "my Extension for $this"

// FILE: Tests.kt

package sentence3

import libPackage.*
import libPackage.orEmpty

public fun String?.orEmpty(): String = "my top-level (pack scope) Extension for $this"

// TESTCASE NUMBER: 1
class Case1() {
    fun String?.orEmpty(): String = "my local extension for $this"
    fun case1(s: String?) {
        s.<!DEBUG_INFO_AS_CALL("fqName: sentence3.Case1.orEmpty; typeCall: function; ")!>orEmpty()<!>
    }
}