package io.xrq.guestbook

/**
  * Created by Jacob Lambert Tue Mar 13 06:10:11 CDT 2018
  */

import com.github.nscala_time.time.Imports._

import scalikejdbc._

case class Guest(id: Option[Long], name: String, message: String, timestamp: Option[DateTime])

object Guest extends SQLSyntaxSupport[Guest] {
  override val tableName = "guests"
  def apply(rs: WrappedResultSet) = {
    val i = rs.long("id")
	 val n = rs.string("name")
	 val m = rs.string("message")
	 val t = new DateTime(rs.timestamp("created"))
	 new Guest(Some(i), n, m, Some(t))
  }
}

