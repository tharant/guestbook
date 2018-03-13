val ScalatraVersion = "2.6.2"

organization := "io.xrq"

name := "guestbook"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.12.4"

resolvers += Classpaths.typesafeReleases

libraryDependencies ++= Seq(
  "org.scalatra"           %% "scalatra"            % ScalatraVersion,
  "org.scalatra"           %% "scalatra-scalatest"  % ScalatraVersion    % "test",
  "org.scalatra"           %% "scalatra-json"       % ScalatraVersion,
  "ch.qos.logback"         %  "logback-classic"     % "1.2.3"            % "runtime",
  "org.eclipse.jetty"      %  "jetty-webapp"        % "9.4.8.v20171121"  % "container",
  "javax.servlet"          %  "javax.servlet-api"   % "3.1.0"            % "provided",
  "com.github.nscala-time" %% "nscala-time"         % "2.18.0",
  "org.scalikejdbc"        %% "scalikejdbc"         % "3.2.1",
  "com.h2database"         %  "h2"                  % "1.4.196",
  "ch.qos.logback"         %  "logback-classic"     % "1.2.3",
  "org.json4s"             %% "json4s-ext"          % "3.5.3",
  "org.json4s"             %% "json4s-jackson"      % "3.5.3"
)

enablePlugins(ScalatraPlugin)
