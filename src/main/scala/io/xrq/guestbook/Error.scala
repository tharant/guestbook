package io.xrq.guestbook

/**
  * Created by Jacob Lambert Tue Mar 13 06:12:34 CDT 2018
  */

case class Error(error: String)

object Error {
  def apply(t: Throwable): Error = {
    val msg = t.getMessage.replace("\n", ". ")
    Error(msg)
  }
}
