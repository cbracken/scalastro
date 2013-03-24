package jp.bracken.scalastro

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
