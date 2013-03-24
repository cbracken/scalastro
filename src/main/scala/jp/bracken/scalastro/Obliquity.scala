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

/** Calculations for Earth's axial obliquity relative to the ecliptic.
  * Ref: Jean Meeus, _Astronomical Algorithms_, 2nd edition, 1998
  */
object Obliquity {
  private val j2000 =
    JulianDate.doubleValue(new DateTime(2000, 1, 1, 12, 0, 0, DateTimeZone.UTC))

  def forJulianDate(jd:Double): Angle = {
    val t = (jd - j2000) / 36525
    Angle(23, 26, 21.448) - (Angle(0, 0, 46.8150) +
    (Angle(0, 0, 0.0059) - Angle(0, 0, 0.001813) * t) * t) * t
  }
}
