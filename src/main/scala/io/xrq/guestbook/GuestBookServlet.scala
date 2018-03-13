package io.xrq.guestbook

/**
  * Created by Jacob Lambert Tue Mar 13 06:05:41 CDT 2018
  */

import scala.util.{Failure, Success, Try}

import org.scalatra._
import org.scalatra.json._

import org.json4s.{DefaultFormats, Formats}

class GuestBookServlet extends ScalatraServlet with JacksonJsonSupport {

  protected implicit lazy val jsonFormats: Formats = DefaultFormats ++ org.json4s.ext.JodaTimeSerializers.all 

  before() { contentType = formats("json") }

  private def getResultOrError[T](f: => T): ActionResult = {
    Try { f } match {
      case Success(r) => Ok(r)
      case Failure(t) => InternalServerError(Error(t))
    }
  }

  get("/log") {
    getResultOrError {
      val list = DBUtil.getAllGuests 
      val result = list map { _.copy(id = None) } // remove the id value as the field is not specified in the docs
      result
    }
  }

  post("/log") {
    getResultOrError {
      val guest = parsedBody.extract[Guest]
      DBUtil.insertGuest(guest)
    }
  }
}
