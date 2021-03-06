/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.expressions.builder

import kotlin.contracts.*
import org.jetbrains.kotlin.fir.FirSourceElement
import org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder
import org.jetbrains.kotlin.fir.builder.FirBuilderDsl
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall
import org.jetbrains.kotlin.fir.expressions.FirArraySetCall
import org.jetbrains.kotlin.fir.expressions.FirExpression
import org.jetbrains.kotlin.fir.expressions.FirOperation
import org.jetbrains.kotlin.fir.expressions.builder.FirQualifiedAccessBuilder
import org.jetbrains.kotlin.fir.expressions.impl.FirArraySetCallImpl
import org.jetbrains.kotlin.fir.expressions.impl.FirModifiableQualifiedAccess
import org.jetbrains.kotlin.fir.expressions.impl.FirNoReceiverExpression
import org.jetbrains.kotlin.fir.references.FirReference
import org.jetbrains.kotlin.fir.types.FirTypeProjection
import org.jetbrains.kotlin.fir.visitors.*

/*
 * This file was generated automatically
 * DO NOT MODIFY IT MANUALLY
 */

@FirBuilderDsl
class FirArraySetCallBuilder : FirQualifiedAccessBuilder, FirAnnotationContainerBuilder {
    override var source: FirSourceElement? = null
    override val annotations: MutableList<FirAnnotationCall> = mutableListOf()
    override var safe: Boolean = false
    override val typeArguments: MutableList<FirTypeProjection> = mutableListOf()
    override var explicitReceiver: FirExpression? = null
    override var dispatchReceiver: FirExpression = FirNoReceiverExpression
    override var extensionReceiver: FirExpression = FirNoReceiverExpression
    lateinit var calleeReference: FirReference
    lateinit var rValue: FirExpression
    lateinit var operation: FirOperation
    val indexes: MutableList<FirExpression> = mutableListOf()

    override fun build(): FirArraySetCall {
        return FirArraySetCallImpl(
            source,
            annotations,
            safe,
            typeArguments,
            explicitReceiver,
            dispatchReceiver,
            extensionReceiver,
            calleeReference,
            rValue,
            operation,
            indexes,
        )
    }

}

@OptIn(ExperimentalContracts::class)
inline fun buildArraySetCall(init: FirArraySetCallBuilder.() -> Unit): FirArraySetCall {
    contract {
        callsInPlace(init, kotlin.contracts.InvocationKind.EXACTLY_ONCE)
    }
    return FirArraySetCallBuilder().apply(init).build()
}
