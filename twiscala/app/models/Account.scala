package models

import org.mindrot.jbcrypt.BCrypt
import com.yasuoza.plugin.RedisDB

case class Account(id: Long, email: String, password: String, name: String)


object Account {

  def authenticate(email: String, password: String): Option[Account] = {

    findByEmail(email).filter {
      account =>
        BCrypt.checkpw(password, account.password)
    }
  }

  def create(name: String, email: String, password: String, confirm: String): Option[Account] = {
    if (password != confirm) None
    else {
      RedisDB.withClient {
        client =>
          val uid = client.incr("global:nextuid").get
          client.set("uid:" + uid + ":email", email)
          client.set("uid:" + uid + ":name", name)
          client.set("uid:" + uid + ":password", BCrypt.hashpw(password, BCrypt.gensalt()))
          client.set("email:" + email + ":uid", uid)

      }
      findByEmail(email)

    }
  }

  def findByEmail(email: String): Option[Account] = findBy("email", email)

  def findByUserId(uid: String): Option[Account] = findBy("uid", uid)


  def findBy(field: String, value: String): Option[Account] = {

    RedisDB.withClient {
      client =>
        val uid = field match {
          case "email" => client.get("email:" + value + ":uid")
          case "uid" => Some(field)
          case _ => Some("0")
        }

        if (uid.isDefined) {
          Some(Account(uid.get.toLong, client.get("uid:" + uid.get + ":email").get, client.get("uid:" + uid.get + ":password").get, client.get("uid:" + uid.get + ":name").get))
        }
        else None
    }

  }

}