// !LANGUAGE: +NewInference
// !DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 0.1-253
 * PLACE: overload-resolution, a-call-with-an-explicit-receiver -> paragraph 7 -> sentence 1
 * RELEVANT PLACES: overload-resolution, callables-and-invoke-convention -> paragraph 6 -> sentence 1
 * overload-resolution, a-call-with-an-explicit-receiver -> paragraph 3 -> sentence 1
 * overload-resolution, a-call-with-an-explicit-receiver -> paragraph 2 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: sets of non-extension member callables only
 */

package tests.diag

// TESTCASE NUMBER: 1, 2
operator fun Int.invoke() {}

// TESTCASE NUMBER: 1
class Case1 {
    val foo: Int = 1
}

fun case1() {
    Case1().<!DEBUG_INFO_AS_CALL("fqName: tests.diag.invoke; typeCall: variable&invoke; ")!>foo()<!>
    val case = Case1()
    case.<!DEBUG_INFO_AS_CALL("fqName: tests.diag.invoke; typeCall: variable&invoke; ")!>foo()<!>
}

// TESTCASE NUMBER: 2
class Case2 {
    val foo: Int = 1
    fun foo(): Int = 1
}
fun case2() {
    Case2().<!DEBUG_INFO_AS_CALL("fqName: tests.diag.Case2.foo; typeCall: function; ")!>foo()<!>
    val case = Case2()
    case.<!DEBUG_INFO_AS_CALL("fqName: tests.diag.Case2.foo; typeCall: function; ")!>foo()<!>
}


// TESTCASE NUMBER: 3
class Case3() {
    val foo = object : Marker {}
    fun foo(): Int = 1

    fun f() {
        this.<!DEBUG_INFO_AS_CALL("fqName: tests.diag.Case3.foo; typeCall: function; ")!>foo()<!>
    }
}

fun case3() {
    Case3().<!DEBUG_INFO_AS_CALL("fqName: tests.diag.Case3.foo; typeCall: function; ")!>foo()<!>
    val case = Case3()
    case.<!DEBUG_INFO_AS_CALL("fqName: tests.diag.Case3.foo; typeCall: function; ")!>foo()<!>
}
// TESTCASE NUMBER: 4
class Case4() {
    val foo = object : Marker {}

    fun f() {
       this.<!DEBUG_INFO_AS_CALL("fqName: tests.diag.Marker.invoke; typeCall: variable&invoke; ")!>foo()<!>
    }
}

fun case4() {
    Case4().<!DEBUG_INFO_AS_CALL("fqName: tests.diag.Marker.invoke; typeCall: variable&invoke; ")!>foo()<!>
    val case = Case4()
    case.<!DEBUG_INFO_AS_CALL("fqName: tests.diag.Marker.invoke; typeCall: variable&invoke; ")!>foo()<!>
}

interface Marker {
    operator fun invoke() {}
}

