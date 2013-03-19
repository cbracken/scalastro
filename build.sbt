name := "scalastro"

version := "0.0.1"

organization := "jp.bracken"

scalaVersion := "2.10.1"

resolvers ++= Seq(
    "ScalaNLP Maven2"       at "http://repo.scalanlp.org/repo",
    "Scala Tools Snapshots" at "http://scala-tools.org/repo-snapshots/",
    "Typesafe Repository"   at "http://repo.typesafe.com/typesafe/releases/",
    "Sonatype Snapshots"    at "https://oss.sonatype.org/content/repositories/snapshots/"
)

libraryDependencies ++= Seq(
    "org.scalanlp"  %% "breeze-math"       % "0.2-SNAPSHOT",
    "joda-time"     %  "joda-time"         % "2.2",
    "org.joda"      %  "joda-convert"      % "1.3.1",
    "org.scalatest" %% "scalatest"         % "2.0.M5b" % "test"
)
