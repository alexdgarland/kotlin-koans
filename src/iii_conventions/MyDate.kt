package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {

    override operator fun compareTo(other: MyDate): Int = when {
        year != other.year -> year - other.year
        month != other.month -> month - other.month
        else -> dayOfMonth - other.dayOfMonth
    }

}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(override val start: MyDate, override val endInclusive: MyDate) : ClosedRange<MyDate>, Iterable<MyDate> {

    override fun iterator(): Iterator<MyDate> {

        return object : Iterator<MyDate> {

            private var nextDate : MyDate = start

            override fun hasNext() : Boolean = (nextDate <= endInclusive)

            override fun next() : MyDate {
                val currentDate = nextDate
                nextDate = currentDate.nextDay()
                return currentDate
            }

        }

    }

}
