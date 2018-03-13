package io.xrq.guestbook

/**
  * Created by Jacob Lambert Tue Mar 13 06:09:33 CDT 2018
  */

import scala.util.{Failure, Success, Try}

import scalikejdbc._

object DBUtil {

  Class.forName("org.h2.Driver")
  ConnectionPool.singleton("jdbc:h2:mem:guestbook", "user", "pass")

  implicit val session = AutoSession
  
  Try {
    // create the guestbook table
    sql"""
      CREATE TABLE guests (
        id SERIAL NOT NULL PRIMARY KEY,
        name VARCHAR(256),
        message VARCHAR(256),
        created TIMESTAMP NOT NULL
      )
      """.execute.apply()
  } match {
    case Success(_) => System.out.println("guestbook table created")
    case Failure(t) => 
      System.err.println(s"unable to create guestbook table due to: $t")
      throw t
  }

  def insertGuest(guest: Guest): Unit = {
    sql"INSERT INTO guests(name, message, created) VALUES (${guest.name}, ${guest.message}, now())".update.apply()
  }

  def getAllGuests: List[Guest] = sql"SELECT * FROM guests".map(rs => Guest(rs)).list.apply()

  def getGuestByName(name: String): Guest = {
    sql"SELECT * FROM guests WHERE name = ${name}".map(rs => Guest(rs)).list.apply().head
  }
  
  def getGuestById(id: Long): Guest = {
    sql"SELECT * FROM guests WHERE id = ${id}".map(rs => Guest(rs)).list.apply().head
  }
}
