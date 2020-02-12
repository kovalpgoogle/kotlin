// !LANGUAGE: +NewInference
// !DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 0.1-261
 * PLACE:  overload-resolution, a-call-with-an-explicit-receiver -> paragraph 7 -> sentence 2
 * RELEVANT PLACES: overload-resolution, callables-and-invoke-convention -> paragraph 6 -> sentence 1
 * overload-resolution, a-call-with-an-explicit-receiver -> paragraph 3 -> sentence 1
 * overload-resolution, a-call-with-an-explicit-receiver -> paragraph 2 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: set of non-extension member callables only
 */
// TESTCASE NUMBER: 1
class Case1 {
    operator fun String.invoke() :String{ return this} //fqName: Case1.invoke; typeCall: function
    fun String.foo() : String = this                   //fqName: Case1.foo;    typeCall: function;
    fun bar() {
        "".<!DEBUG_INFO_AS_CALL("fqName: Case1.foo; typeCall: function; ")!>foo()<!>
        "".<!DEBUG_INFO_AS_CALL("fqName: Case1.invoke; typeCall: function; ")!>invoke()<!>
        <!DEBUG_INFO_AS_CALL("fqName: Case1.invoke; typeCall: function; ")!>""()<!>
        <!DEBUG_INFO_AS_CALL("fqName: Case1.invoke; typeCall: function; ")!>""()()()()  ()<!>
        <!DEBUG_INFO_AS_CALL("fqName: Case1.invoke; typeCall: function; ")!>""().invoke()()()<!>
        <!DEBUG_INFO_AS_CALL("fqName: Case1.invoke; typeCall: function; ")!>""().foo()().invoke()()<!>
        <!DEBUG_INFO_AS_CALL("fqName: Case1.invoke; typeCall: function; ")!>"1".foo()()<!>
        <!DEBUG_INFO_AS_CALL("fqName: Case1.invoke; typeCall: function; ")!>"1"()().invoke()()<!>.<!DEBUG_INFO_AS_CALL("fqName: Case1.foo; typeCall: function; ")!>foo()<!>
    }
}