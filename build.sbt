name := "scalastro"

version := "0.0.1"

scalaVersion := "2.10.1"

libraryDependencies ++= Seq(
    "org.scalanlp"  %  "breeze-math_2.9.2" % "0.1",
    "joda-time"     %  "joda-time"         % "2.2",
    "org.joda"      %  "joda-convert"      % "1.3.1",
    "org.scalatest" %% "scalatest"         % "2.0.M5b" % "test"
)
