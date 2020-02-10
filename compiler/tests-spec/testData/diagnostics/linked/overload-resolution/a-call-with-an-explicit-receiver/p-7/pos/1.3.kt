// !LANGUAGE: +NewInference
// !DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 0.1-261
 * PLACE: overload-resolution, a-call-with-an-explicit-receiver -> paragraph 7 -> sentence 1
 * RELEVANT PLACES: overload-resolution, callables-and-invoke-convention -> paragraph 6 -> sentence 1
 * overload-resolution, a-call-with-an-explicit-receiver -> paragraph 3 -> sentence 1
 * overload-resolution, a-call-with-an-explicit-receiver -> paragraph 2 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: set of non-extension member callables only
 */

// TESTCASE NUMBER: 1
class Case1() {
    fun foo() : Int =1

    val foo = object : Marker {}

    fun innerFun() {
        <!DEBUG_INFO_AS_CALL("fqName: Case1.foo; typeCall: function; ")!>foo()<!>
        this.<!DEBUG_INFO_AS_CALL("fqName: Case1.foo; typeCall: function; ")!>foo()<!>
    }

    operator fun Int.invoke() {}

    inner class InnerClass0 {
        val foo = object : Marker {}

        fun innerClassFun() {
            <!DEBUG_INFO_AS_CALL("fqName: Marker.invoke; typeCall: variable&invoke; ")!>foo()<!>
            this.<!DEBUG_INFO_AS_CALL("fqName: Marker.invoke; typeCall: variable&invoke; ")!>foo()<!>
            this@Case1.<!DEBUG_INFO_AS_CALL("fqName: Case1.foo; typeCall: function; ")!>foo()<!>
        }
    }
    inner class InnerClass1 {
        fun foo() : Int =1

        fun innerClassFun() {
            <!DEBUG_INFO_AS_CALL("fqName: Case1.InnerClass1.foo; typeCall: function; ")!>foo()<!>
            this.<!DEBUG_INFO_AS_CALL("fqName: Case1.InnerClass1.foo; typeCall: function; ")!>foo()<!>
            this@Case1.<!DEBUG_INFO_AS_CALL("fqName: Case1.foo; typeCall: function; ")!>foo()<!>
        }
    }
}

interface Marker {
    operator fun invoke() {}
}
