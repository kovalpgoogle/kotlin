// !LANGUAGE: +NewInference
// !DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 0.1-261
 * PLACE: overload-resolution, a-call-with-an-explicit-receiver -> paragraph 7 -> sentence 4
 * RELEVANT PLACES: overload-resolution, a-call-with-an-explicit-receiver -> paragraph 7 -> sentence 5
 * overload-resolution, a-call-with-an-explicit-receiver -> paragraph 11 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: sets of declared in the package scope and star-imported extension callables
 */

// FILE: Extensions.kt
package libPackage

public fun String?.orEmpty(): String = "my Extension for $this"

// FILE: TestCase2.kt
package sentence3

import libPackage.*
//import libPackage.orEmpty

private fun String?.orEmpty(): String = "my top level extension for $this"

// TESTCASE NUMBER: 1
fun case2(s: String?) {
    <!DEBUG_INFO_AS_CALL("fqName: sentence3.orEmpty; typeCall: function; ")!>s.orEmpty()<!>
}

