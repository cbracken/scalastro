package jp.bracken.scalastro.date

import org.joda.time.chrono.ISOChronology
import org.joda.time.chrono.JulianChronology
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.Days
import org.joda.time.Duration
import org.joda.time.ReadableInstant

/** Transformations between Joda instants and double representations of Modified
  * Julian Date (MJD).
  *
  * Modified Julian Date is the count of days (including fractions of a day)
  * since the epoch: 17 November 1858 CE 0:00:00.000 (midnight). More
  * historically accurately, Modified Julian Date = Julian Date - 2 400 000.5.
  *
  * Modified Julian Date has two main benefits over Julian Date: first, the
  * start of the day aligns with civil time reckoning, at midnight. Second,
  * since the high-order digits of Julian Dates rarely change, it results in
  * shorter 5-digit dates (for the time being).
  *
  * More information about Modified Julian Date available at the
  * [[http://tycho.usno.navy.mil/mjd.html US Naval Observatory Time Service
  * Department]].
  */
object ModifiedJulianDate {
  private val epoch = new DateTime(1858, 11, 17, 0, 0, 0, 0, DateTimeZone.UTC)
  private val oneDay = Days.ONE.toStandardDuration.getMillis.doubleValue

  /** Returns the Modified Julian Date for the specified instant. */
  def doubleValue(instant:ReadableInstant): Double =
    new Duration(epoch, instant).getMillis / oneDay

  /** Returns a DateTime in ISOChronology for the specified Modified Julian
    * Date.
    */
  def fromDouble(modifiedJulianDate:Double): DateTime =
    epoch.plus(Duration.millis((modifiedJulianDate * oneDay).round))
}

