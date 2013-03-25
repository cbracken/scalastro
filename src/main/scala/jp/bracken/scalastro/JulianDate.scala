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
import org.joda.time.chrono.ISOChronology
import org.joda.time.chrono.JulianChronology
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.Days
import org.joda.time.Duration
import org.joda.time.ReadableInstant

/** Transformations between Joda instants and double representations of Julian
  * Dates (JD).
  *
  * Julian Date is the count of days (including fractions of a day) since the
  * epoch: 1 January 4713 BCE at 12:00:00.000 UTC (noon) of the Julian Proleptic
  * Calendar. It is in wide use by astronomers, geophysicists and others who
  * require an unambiguous dating system based on a continuous count of days.
  *
  * More information from the
  * [[http://www.iers.org/nn_10910/IERS/EN/Science/Recommendations/resolutionB1.html International
  * Earth Rotation and Reference Systems Service]]. */
object JulianDate {
  private val epoch = new DateTime(-4713, 1, 1, 12, 0, 0, 0,
      JulianChronology.getInstance(DateTimeZone.UTC))
  private val oneDay = Days.ONE.toStandardDuration.getMillis.doubleValue

  /** Returns the Julian Date for the specified instant. */
  def fromInstant(instant: ReadableInstant): Double =
    new Duration(epoch, instant).getMillis / oneDay

  /** Returns a DateTime in ISOChronology for the specified Julian Date. */
  def toDateTime(julianDate: Double): DateTime =
    epoch.plus(Duration.millis((julianDate * oneDay).round))
        .toDateTime(ISOChronology.getInstance(DateTimeZone.UTC))
}

