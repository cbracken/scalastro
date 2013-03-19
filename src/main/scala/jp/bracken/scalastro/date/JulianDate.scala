package jp.bracken.scalastro.date

import org.joda.time.chrono.ISOChronology
import org.joda.time.chrono.JulianChronology
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.Days
import org.joda.time.Duration
import org.joda.time.ReadableInstant

object JulianDate {
  private val epoch = new DateTime(-4713, 1, 1, 12, 0, 0, 0,
      JulianChronology.getInstance(DateTimeZone.UTC))
  private val oneDay = Days.ONE.toStandardDuration.getMillis.doubleValue

  def doubleValue(instant:ReadableInstant): Double =
    new Duration(epoch, instant).getMillis / oneDay

  def fromDouble(julianDate:Double): DateTime =
    epoch.plus(Duration.millis((julianDate * oneDay).round)).toDateTime(
      ISOChronology.getInstance(DateTimeZone.UTC))
}

