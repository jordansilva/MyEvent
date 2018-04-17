package app.jordansilva.infrastructure.util.extensions

import java.util.*

inline var Calendar.dayOfMonth
    get() = this[Calendar.DAY_OF_MONTH]
    set(value) = this.set(Calendar.DAY_OF_MONTH, value)

inline var Calendar.month
    get() = this[Calendar.MONTH]
    set(value) = this.set(Calendar.MONTH, value)

inline var Calendar.year
    get() = this[Calendar.YEAR]
    set(value) = this.set(Calendar.YEAR, value)

inline var Calendar.hourOfDay
    get() = this[Calendar.HOUR_OF_DAY]
    set(value) = this.set(Calendar.HOUR_OF_DAY, value)

inline var Calendar.minute
    get() = this[Calendar.MINUTE]
    set(value) = this.set(Calendar.MINUTE, value)

inline var Calendar.second
    get() = this[Calendar.SECOND]
    set(value) = this.set(Calendar.SECOND, value)

inline var Calendar.millisecond
    get() = this[Calendar.MILLISECOND]
    set(value) = this.set(Calendar.MILLISECOND, value)
