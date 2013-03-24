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
import org.joda.time.Duration
import org.scalatest._

class JulianDateTest extends FlatSpec with ShouldMatchers {
  "JulianDate.doubleValue" should "return 0 for the epoch" in {
    val t = new DateTime(-4713, 1, 1, 12, 0,
        JulianChronology.getInstance(DateTimeZone.UTC))
    JulianDate.doubleValue(t) should be (0.0)
  }

  it should "handle dates from Julian Chronology" in {
    val t = new DateTime(400, 1, 1, 12, 0,
        JulianChronology.getInstance(DateTimeZone.UTC))
    JulianDate.doubleValue(t) should be (1867158.0)
  }

  it should "handle dates from Gregorian Chronology" in {
    val t = new DateTime(2000, 1, 1, 12, 0, DateTimeZone.UTC)
    JulianDate.doubleValue(t) should be (2451545.0)
  }

  it should "handle fractional days" in {
    val t = new DateTime(2000, 1, 1, 2, 20, 15, 332, DateTimeZone.UTC)
    JulianDate.doubleValue(t) should be (2451544.5973996758)
  }

  "JulianDate.fromDouble" should "return the epoch for 0" in {
    val julianChron = JulianChronology.getInstance(DateTimeZone.UTC)
    val epoch = new DateTime(-4713, 1, 1, 12, 0, julianChron)
    val dateTime = JulianDate.fromDouble(0.0)
    dateTime.toDateTime(julianChron) should be (epoch)
    val isoChron = ISOChronology.getInstance(DateTimeZone.UTC)
    dateTime.getChronology should be (isoChron)
  }

  it should "handle fractional days" in {
    val t = new DateTime(2000, 1, 1, 2, 20, 15, 332, DateTimeZone.UTC)
    JulianDate.fromDouble(2451544.5973996758) should be (t)
  }
}
