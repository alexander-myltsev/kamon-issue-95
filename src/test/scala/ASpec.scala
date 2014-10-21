import akka.actor.ActorSystem
import akka.testkit.{TestKit, TestKitBase}
import org.scalatest.{BeforeAndAfterAll, FreeSpec, MustMatchers}
import scala.concurrent.duration._

class ASpec extends FreeSpec with MustMatchers with TestKitBase with BeforeAndAfterAll {
  implicit lazy val system: ActorSystem = ActorSystem(this.toString())

  override protected def afterAll(): Unit = {
    TestKit.shutdownActorSystem(system, 5.seconds, verifySystemShutdown = true)
    super.afterAll()
  }

  "a fact" in {
    (2 + 2) mustBe 4
  }
}
