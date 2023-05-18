package com.koltsov.captain.calculator.items.service.infrastructure.db.exposed

import org.jetbrains.exposed.sql.Expression
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.SqlExpressionBuilder.regexp
import org.jetbrains.exposed.sql.stringParam

fun <T : String?> Expression<T>.regexpOp(pattern: String?, defaultValue: Op<Boolean> = Op.TRUE): Op<Boolean> =
    pattern?.let { this.regexp(stringParam(it), caseSensitive = false) } ?: defaultValue
