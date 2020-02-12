// !LANGUAGE: +NewInference
// !DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 0.1-261
 * PLACE:overload-resolution, a-call-with-an-explicit-receiver -> paragraph 7 -> sentence 3
 * RELEVANT PLACES: overload-resolution, a-call-with-an-explicit-receiver -> paragraph 7 -> sentence 4
 * overload-resolution, a-call-with-an-explicit-receiver -> paragraph 7 -> sentence 5
 * overload-resolution, a-call-with-an-explicit-receiver -> paragraph 11 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: sets of explicitly imported, declared in the package scope and star-imported extension callables
 */

// FILE: Extensions.kt
package libPackage

public fun String?.orEmpty(): String = "my Extension for $this"

// FILE: Tests.kt
package sentence3

import libPackage.*
import libPackage.orEmpty


fun String?.orEmpty(): String = "my top level extension for $this"

// TESTCASE NUMBER: 1
fun case1(s: String?) {
    <!DEBUG_INFO_AS_CALL("fqName: libPackage.orEmpty; typeCall: function; ")!>s.orEmpty()<!>
}

