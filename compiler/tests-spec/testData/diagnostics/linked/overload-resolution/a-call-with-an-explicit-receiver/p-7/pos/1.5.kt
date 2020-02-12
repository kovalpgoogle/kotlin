// !LANGUAGE: +NewInference
// !DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 0.1-261
 * PLACE: overload-resolution, a-call-with-an-explicit-receiver -> paragraph 7 -> sentence 1
 * RELEVANT PLACES: overload-resolution, a-call-with-an-explicit-receiver -> paragraph 7 -> sentence 2
 * overload-resolution, a-call-with-an-explicit-receiver -> paragraph 11 -> sentence 1
 * NUMBER: 5
 * DESCRIPTION: The sets of non-extension member callables named f of type T;
 */

class Marker {
    fun foo() = println("non-extension member toplevel Marker.foo()")
    val foo: String = "non-extension member toplevel Marker.foo"
}

// TESTCASE NUMBER: 1
class Case1() {

    fun Marker.<!EXTENSION_SHADOWED_BY_MEMBER!>foo<!>() = println("local extension marker.foo")

    fun test() {
        Marker().<!DEBUG_INFO_AS_CALL("fqName: Marker.foo; typeCall: function; ")!>foo()<!>
    }
}

fun case1() {
    Marker().<!DEBUG_INFO_AS_CALL("fqName: Marker.foo; typeCall: function; ")!>foo()<!>
}

// TESTCASE NUMBER: 2
class Case2() {
    fun test() {
        fun Marker.<!EXTENSION_SHADOWED_BY_MEMBER!>foo<!>() = println("local extension marker.foo")
        Marker().<!DEBUG_INFO_AS_CALL("fqName: Marker.foo; typeCall: function; ")!>foo()<!>
    }
}

fun Marker.<!EXTENSION_SHADOWED_BY_MEMBER!>foo<!>() = println("top level extension marker.foo")

fun case2() {
    Marker().<!DEBUG_INFO_AS_CALL("fqName: Marker.foo; typeCall: function; ")!>foo()<!>
}

// TESTCASE NUMBER: 3
fun case3() {
    fun Marker.<!EXTENSION_SHADOWED_BY_MEMBER!>foo<!>() = println("local extension marker.foo")
    Marker().<!DEBUG_INFO_AS_CALL("fqName: Marker.foo; typeCall: function; ")!>foo()<!>
}
