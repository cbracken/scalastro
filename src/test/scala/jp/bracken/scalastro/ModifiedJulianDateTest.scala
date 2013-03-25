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

class ModifiedJulianDateTest extends FlatSpec with ShouldMatchers {
  "ModifiedJulianDate.fromInstant" should "return 0 for the epoch" in {
    val epoch = new DateTime(1858, 11, 17, 0, 0, 0, 0, DateTimeZone.UTC)
    ModifiedJulianDate.fromInstant(epoch) should be (0)
  }

  it should "return -2400000.5 for the julian epoch" in {
    val t = new DateTime(-4713, 1, 1, 12, 0,
        JulianChronology.getInstance(DateTimeZone.UTC))
    ModifiedJulianDate.fromInstant(t) should be (-2400000.5)
  }

  it should "handle dates from Julian Chronolgy" in {
    val t = new DateTime(400, 1, 1, 12, 0,
        JulianChronology.getInstance(DateTimeZone.UTC))
    ModifiedJulianDate.fromInstant(t) should be (-532842.5)
  }

  it should "handle dates from Gregorian Chronology" in {
    val t = new DateTime(2000, 1, 1, 0, 0, DateTimeZone.UTC)
    ModifiedJulianDate.fromInstant(t) should be (51544.0)
  }

  it should "handle fractional days" in {
    val t = new DateTime(2000, 1, 1, 2, 20, 15, 332, DateTimeZone.UTC)
    ModifiedJulianDate.fromInstant(t) should be (51544.09739967593)
  }

  "ModifiedJulianDate.toDateTime" should "return the epoch for 0" in {
    val epoch = new DateTime(1858, 11, 17, 0, 0, 0, 0, DateTimeZone.UTC)
    val dateTime = ModifiedJulianDate.toDateTime(0.0)
    dateTime should be (epoch)
    val isoChron = ISOChronology.getInstance(DateTimeZone.UTC)
    dateTime.getChronology should be (isoChron)
  }

  it should "handle fractional days" in {
    val t = new DateTime(2000, 1, 1, 2, 20, 15, 332, DateTimeZone.UTC)
    ModifiedJulianDate.toDateTime(51544.09739967593) should be (t)
  }
}

