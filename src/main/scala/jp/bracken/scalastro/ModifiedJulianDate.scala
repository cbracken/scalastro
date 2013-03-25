package jp.bracken.scalastro

/* Copyright (C) 2013 Chris Bracken
 *
 * This file is part of Scalastro.
 *
 * Scalastro is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Scalastro is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Scalastro.  If not, see <http://www.gnu.org/licenses/>.
 */
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.Days
import org.joda.time.Duration
import org.joda.time.ReadableInstant

/** Transformations between Joda instants and double representations of Modified
  * Julian Date (MJD).
  *
  * Modified Julian Date is the count of days (including fractions of a day)
  * since the epoch: 17 November 1858 CE 0:00:00.000 UTC (midnight). More
  * historically accurately, Modified Julian Date = Julian Date - 2 400 000.5.
  *
  * Modified Julian Date differs from Julian Date in two important regards:
  * first, start of the day aligns with civil time reckoning, at midnight.
  * Second, it (currently) results in shorter 5-digit dates, which may be
  * convenient where use is unambiguous.
  *
  * More information about Modified Julian Date available at the
  * [[http://tycho.usno.navy.mil/mjd.html US Naval Observatory Time Service
  * Department]] and at the
  * [[http://www.iers.org/nn_10910/IERS/EN/Science/Recommendations/resolutionB1.html International
  * Earth Rotation and Reference Systems Service]]. */
object ModifiedJulianDate {
  private val epoch = new DateTime(1858, 11, 17, 0, 0, 0, 0, DateTimeZone.UTC)
  private val oneDay = Days.ONE.toStandardDuration.getMillis.toDouble

  /** Returns the Modified Julian Date for the specified instant. */
  def fromInstant(instant: ReadableInstant): Double =
    new Duration(epoch, instant).getMillis / oneDay

  /** Returns a DateTime in ISOChronology for the specified Modified Julian
    * Date. */
  def toDateTime(modifiedJulianDate: Double): DateTime =
    epoch.plus(Duration.millis((modifiedJulianDate * oneDay).round))
}

