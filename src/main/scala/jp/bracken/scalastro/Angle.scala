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
import scala.math.Pi

/** An angle measured in degrees, minutes and seconds of arc in the range
  * [0, 360) degrees. */
class Angle private(val deg: Int, val min: Int, val sec: Double) {
  def toDegrees(): Double = sec / 3600.0 + min / 60.0 + deg

  def toRadians(): Double = (
      sec * Pi / 648000.0 +
      min * Pi / 10800.0 +
      deg * Pi / 180.0)

  def +(rhs: Angle): Angle = {
    var d = deg + rhs.deg
    var m = min + rhs.min
    var s = sec + rhs.sec
    if (s > 60) {
      m += 1
      s -= 60
    }
    if (m > 60) {
      d += 1
      m -= 60
    }
    new Angle(d % 360, m, s)
  }

  def unary_- = {
    var d = deg
    var m = min
    var s = sec
    if (s > 0) {
      m += 1
      s = 60 - s
    }
    if (m > 0) {
      d += 1
      m = 60 - m
    }
    new Angle((360 - d) % 360, m, s)
  }

  def -(a: Angle): Angle = this.+(-a)

  def *(x: Double): Angle = {
    Angle.fromDegrees(deg * x) +
    Angle.fromDegrees(min * x / 60) +
    Angle.fromDegrees(sec * x / 3600)
  }

  def /(x: Double): Angle = {
    require(x != 0)
    Angle.fromDegrees(deg / x) +
    Angle.fromDegrees(min / (x * 60)) +
    Angle.fromDegrees(sec / (x * 3600))
  }

  override def equals(obj: Any): Boolean =
    obj match {
      case a: Angle =>
        a.isInstanceOf[Angle] &&
          deg == a.deg &&
          min == a.min &&
          sec == a.sec
      case _ => false
    }

  override def hashCode: Int = 41 * (41 * (41 + sec.hashCode) + min) + deg

  override def toString: String = "[" + deg + "d" + min + "'" + sec +"\"]"
}

object Angle {
  def apply(deg: Int, min: Int, sec: Double) = {
    require(deg >= 0)
    require(min >= 0 && min < 60)
    require(sec >= 0.0 && sec < 60.0)
    new Angle(deg % 360, min, sec)
  }

  def fromDegrees(a: Double): Angle = {
    val deg = Math.abs(a)
    val min = ((deg - deg.toInt) * 60).toInt
    val sec = (deg - deg.toInt) * 3600 - min * 60
    val angle = new Angle(deg.toInt % 360, min, sec)
    if (a >= 0) angle else -angle
  }

  def fromRadians(a:Double): Angle = fromDegrees(a * Pi / 180)
}
