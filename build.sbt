import com.typesafe.sbt.SbtAspectj.{ Aspectj, aspectjSettings }
import com.typesafe.sbt.SbtAspectj.AspectjKeys.{ aspectjVersion, compileOnly, lintProperties, weaverOptions }

name := "kamon-issue-95"

version := "0.1"

scalaVersion := "2.11.2"

resolvers ++= Seq(
  "kamon" at "http://snapshots.kamon.io/"
)

val akkaVersion    = "2.3.6"
val kamonVersion   = "0.3.5-fde062f7e700925e30b60f366ddcd66a04f7c2c5"
val ajVersion      = "1.8.2"

libraryDependencies ++= Seq(
  "com.typesafe.akka"      %% "akka-actor"           % akkaVersion,
  "com.typesafe.akka"      %% "akka-agent"           % akkaVersion,
  "com.typesafe.akka"      %% "akka-slf4j"           % akkaVersion,
  "io.kamon"               %% "kamon-core"           % kamonVersion,
  "io.kamon"               %% "kamon-spray"          % kamonVersion,
  "io.kamon"               %% "kamon-system-metrics" % kamonVersion,
  "org.scalatest"          %% "scalatest"            % "2.2.2"      % "test",
  "com.typesafe.akka"      %% "akka-testkit"         % akkaVersion  % "test"
)

aspectjSettings ++ Seq(
  aspectjVersion in Aspectj    :=  ajVersion,
  compileOnly in Aspectj       :=  true,
  fork in Test                 :=  true,
  javaOptions in Test        <++=  weaverOptions in Aspectj,
  javaOptions in run         <++=  weaverOptions in Aspectj,
  lintProperties in Aspectj    +=  "invalidAbsoluteTypeName = ignore"
)