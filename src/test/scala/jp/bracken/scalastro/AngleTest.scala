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
import org.scalatest._

class AngleTest extends FlatSpec with ShouldMatchers {
  "Angle construction" should "succeed for 0 angle" in {
    Angle(0, 0, 0)
  }

  it should "succeed for positive angles" in {
    val a = Angle(359, 59, 59.99999999)
    a.deg should be (359)
    a.min should be (59)
    a.sec should be (59.99999999)
  }

  it should "fail for negative angle components" in {
    evaluating { Angle(-5, 5, 5.0) } should produce [IllegalArgumentException]
    evaluating { Angle(5, -5, 5.0) } should produce [IllegalArgumentException]
    evaluating { Angle(5, 5, -5.0) } should produce [IllegalArgumentException]
  }

  "Angle.fromDegrees" should "handle 0 angles" in {
    Angle.fromDegrees(0.0) should be (Angle(0, 0, 0))
  }

  it should "handle positive angles" in {
    Angle.fromDegrees(30.0) should be (Angle(30, 0, 0))
    Angle.fromDegrees(30.125) should be (Angle(30, 7, 30))
    Angle.fromDegrees(30.03125) should be (Angle(30, 1, 52.5))
    Angle.fromDegrees(3000.125) should be (Angle(120, 7, 30))
  }

  it should "handle negative angles" in {
    Angle.fromDegrees(-30.0) should be (Angle(330, 0, 0))
    Angle.fromDegrees(-30.125) should be (Angle(329, 52, 30))
  }

  "Angle.toDegrees" should "handle 0 angles" in {
    Angle(0, 0, 0).toDegrees should be (0.0)
  }

  it should "handle positive angles" in {
    Angle(135, 0, 0).toDegrees should be (135.0)
    Angle(135, 30, 36).toDegrees should be (135.51)
  }

  it should "handle negative angles" in {
    (-Angle(135, 0, 0)).toDegrees should be (225.0)
    (-Angle(135, 30, 36)).toDegrees should be (224.49)
    (-Angle(3000, 30, 36)).toDegrees should be (239.49)
  }

  "Angle.toRadians" should "handle 0 angles" in {
    Angle(0, 0, 0).toRadians should be (0.0)
  }

  it should "handle positive angles" in {
    Angle(135, 0, 0).toRadians should be (2.356194490192345)
    Angle(90, 30, 36).toRadians should be (1.5796975059800675)
  }

  it should "handle negative angles" in {
    (-Angle(135, 0, 0)).toRadians should be (3.9269908169872414)
    (-Angle(90, 30, 36)).toRadians should be (4.703487801199519)
  }

  "Angle.+" should "handle addition of 0 angle" in {
    Angle(0, 0, 0) + Angle(0, 0, 0) should be (Angle(0, 0, 0))
    Angle(135, 30, 36) + Angle(0, 0, 0) should be (Angle(135, 30, 36))
    (-Angle(135, 30, 36)) + Angle(0, 0, 0) should be (-Angle(135, 30, 36))
  }

  it should "handle the addition of angles without overflow" in {
    Angle(135, 30, 36) + Angle(90, 15, 14) should be (Angle(225, 45, 50))
    (-Angle(135, 30, 36)) + (-Angle(90, 15, 14)) should be (-Angle(225, 45, 50))
    Angle(135, 30, 36) + (-Angle(90, 15, 30)) should be (Angle(45, 15, 06))
    (-Angle(135, 30, 36)) + Angle(90, 15, 30) should be (-Angle(45, 15, 06))
  }

  it should "handle the addition of angles with overflow" in {
    Angle(135, 30, 36) + Angle(90, 15, 30) should be (Angle(225, 46, 06))
    Angle(135, 30, 36) + Angle(90, 45, 14) should be (Angle(226, 15, 50))
    Angle(135, 30, 36) + Angle(90, 45, 30) should be (Angle(226, 16, 06))
  }

  it should "handle the addition of angles with underflow" in {
    Angle(135, 30, 36) + (-Angle(90, 15, 46)) should be (Angle(45, 14, 50))
    Angle(135, 30, 36) + (-Angle(90, 45, 30)) should be (Angle(44, 45, 06))
    Angle(135, 30, 36) + (-Angle(90, 45, 46)) should be (Angle(44, 44, 50))
    (-Angle(135, 30, 36)) + Angle(90, 15, 46) should be (-Angle(45, 14, 50))
    (-Angle(135, 30, 36)) + Angle(90, 45, 30) should be (-Angle(44, 45, 06))
    (-Angle(135, 30, 36)) + Angle(90, 45, 46) should be (-Angle(44, 44, 50))
  }

  "Angle.-" should "handle the subtraction of 0 angle" in {
    Angle(0, 0, 0) - Angle(0, 0, 0) should be (Angle(0, 0, 0))
    Angle(135, 30, 36) - Angle(0, 0, 0) should be (Angle(135, 30, 36))
    (-Angle(135, 30, 36)) - Angle(0, 0, 0) should be (Angle(224, 29, 24))
  }

  it should "handle the subtraction of angles without underflow" in {
    Angle(135, 30, 36) - Angle(90, 15, 30) should be (Angle(45, 15, 06))
    (-Angle(135, 30, 36)) - (-Angle(90, 15, 14)) should be (Angle(314, 44, 38))
  }

  it should "handle the subtraction of angles with underflow" in {
    Angle(135, 30, 36) - Angle(90, 15, 46) should be (Angle(45, 14, 50))
    Angle(135, 30, 36) - Angle(90, 45, 30) should be (Angle(44, 45, 06))
    Angle(135, 30, 36) - Angle(90, 45, 46) should be (Angle(44, 44, 50))
    Angle(90, 15, 46) - Angle(135, 30, 36) should be (-Angle(45, 14, 50))
    Angle(90, 45, 30) - Angle(135, 30, 36) should be (-Angle(44, 45, 06))
    Angle(90, 45, 46) - Angle(135, 30, 36) should be (-Angle(44, 44, 50))
  }

  "Angle.*" should "handle multiplication by 0" in {
    Angle(0, 0, 0) * 0 should be (Angle(0, 0, 0))
    Angle(135, 30, 36) * 0 should be (Angle(0, 0, 0))
  }

  it should "handle multiplication by 1" in {
    Angle(0, 0, 0) * 1 should be (Angle(0, 0, 0))
    Angle(135, 30, 36) * 1 should be (Angle(135, 30, 36))
  }

  it should "handle multiplication by integers > 1" in {
    Angle(135, 30, 36) * 2 should be (Angle(271, 1, 12))
    Angle(135, 30, 36) * 10 should be (Angle(275, 6, 0))
  }

  it should "handle multiplication by reals" in {
    Angle(135, 30, 36) * 2.03125 should be (Angle(275, 15, 16.875))
  }

  "Angle./" should "fail on handle division by 0" in {
    evaluating { Angle(0, 0, 0) / 0 } should produce [IllegalArgumentException]
    evaluating { Angle(1, 2, 3) / 0 } should produce [IllegalArgumentException]
  }

  it should "handle division by 1" in {
    Angle(0, 0, 0) / 1 should be (Angle(0, 0, 0))
    Angle(135, 30, 36) / 1 should be (Angle(135, 30, 36))
  }

  it should "handle division by integers > 1" in {
    Angle(130, 30, 36) / 2 should be (Angle(65, 15, 18))
    Angle(130, 30, 36) / 10 should be (Angle(13, 3, 3.6))
  }

  it should "handle division by reals" in {
    Angle(135, 30, 36) / 2.5 should be (Angle(54, 12, 14.4))
    Angle(135, 30, 36) / 2.825 should be (Angle(47, 58, 5.309734513254227))
    // 5.3097345132743362831858407079646017697676
  }

  "Angle.equals" should "return true for same inputs" in {
    Angle(0, 0, 0) should be (Angle(0, 0, 0))
    Angle(225, 10, 20) should be (Angle(225, 10, 20))
    -Angle(225, 10, 20) should be (-Angle(225, 10, 20))
  }

  it should "return true for congruous angles" in {
    Angle(360, 0, 0) should be (Angle(0, 0, 0))
    -Angle(179, 49, 40) should be (Angle(180,10,20))
    -Angle(710, 30, 30) should be (Angle(9,29,30))
  }
}
