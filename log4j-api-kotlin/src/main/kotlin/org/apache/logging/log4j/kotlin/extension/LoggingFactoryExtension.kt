package org.apache.logging.log4j.kotlin.extension

import org.apache.logging.log4j.kotlin.KotlinLogger
import org.apache.logging.log4j.kotlin.cachedLoggerOf

/**
 * Provides a logger named after the receiver object's class.
 *
 * Simply import this property and use it.
 *
 * ```
 * import org.apache.logging.log4j.kotlin.extension.logger
 *
 * class MyClass {
 *   // use `logger` as necessary
 * }
 * ```
 *
 * Note that this is significantly slower than creating a logger explicitly, as it requires a lookup of the
 * logger on each call via the property getter. We attempt to minimize the overhead of this by caching the
 * loggers, but according to microbenchmarks, it is still about 3.5 times slower than creating a logger once
 * and using it (about 4.2 nanoseconds per call instead of 1.2 nanoseconds).
 *
 * @since 1.3.0
 */
inline val <reified T> T.logger: KotlinLogger
  get() = cachedLoggerOf(T::class.java)
