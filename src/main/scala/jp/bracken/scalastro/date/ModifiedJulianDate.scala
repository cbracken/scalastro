package jp.bracken.scalastro.date

import org.joda.time.chrono.ISOChronology
import org.joda.time.chrono.JulianChronology
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.Days
import org.joda.time.Duration
import org.joda.time.ReadableInstant

object ModifiedJulianDate {
  private val epoch = new DateTime(1858, 11, 17, 0, 0, 0, 0, DateTimeZone.UTC)
  private val oneDay = Days.ONE.toStandardDuration.getMillis.doubleValue

  def doubleValue(instant:ReadableInstant): Double =
    new Duration(epoch, instant).getMillis / oneDay

  def fromDouble(modifiedJulianDate:Double): DateTime =
    epoch.plus(Duration.millis((modifiedJulianDate * oneDay).round))
}

