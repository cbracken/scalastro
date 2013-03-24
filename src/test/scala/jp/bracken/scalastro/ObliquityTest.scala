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
import org.scalatest._

class ObliquityTest extends FlatSpec with ShouldMatchers {
  "Obliquity.forJulianDate" should "return 23 26 21.448 for J2000" in {
    val j2000 = JulianDate.doubleValue(
      new DateTime(2000, 1, 1, 12, 0, 0, DateTimeZone.UTC))
    Obliquity.forJulianDate(j2000) should be (Angle(23, 26, 21.448))
  }
}
