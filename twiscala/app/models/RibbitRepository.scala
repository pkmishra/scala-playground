package models

import com.yasuoza.plugin.RedisDB
import org.joda.time.DateTime


case class RibbitRepository(content: String, sender: String, dateTime: String)

object RibbitRepository {


  def create(content: String, sender: Option[String]): (String, String, String, String) = {
    require(sender.isDefined)
    RedisDB.withClient {
      client =>
            val postId = client.incr("global:nextRibbitId").get
            println(postId)
           client.set("ribbit:" + postId + ":post", sender.get + "|" + DateTime.now() + "|" + content)
            val account = Account.findByEmail(sender.get).get

            val followers = client.smembers("uid:" + account.id + ":followers")
            if (followers != null && followers.isDefined) {
              for (follower <- followers) {
                client.lpush("uid:" + follower + ":posts", postId)

              }
              client.lpush("global:timeline", postId)
              client.ltrim("global:timeline", 0, 1000)
            }

        }
        findAll(sender.getOrElse("")).last

  }

  def findAll(sender: String): Seq[(String, String, String, String)] = {
    RedisDB.withClient {
      client =>
        val postIds = client.lrange("global:timeline", 0, 1000).getOrElse(List())

        val ribbits = for (postId <- postIds if postId.isDefined) yield {
          client.get[String]("ribbit:" + postId.get + ":post").get.split('|') match {
            case Array(x, y, z) => (z, x, y, sender)
            case _ => ("", "", "", sender)
          }

        }
       ribbits.toList
    }
  }

}
