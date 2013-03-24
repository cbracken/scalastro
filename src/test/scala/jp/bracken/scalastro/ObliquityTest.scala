package jp.bracken.scalastro

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
