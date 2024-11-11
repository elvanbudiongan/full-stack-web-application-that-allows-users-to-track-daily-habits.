package repo

import domains.Habit
import javax.inject.{ Singleton, Inject }
import java.util.UUID
import slick.jdbc.PostgresProfile.api._
import play.api.db.slick._
import slick.jdbc.JdbcProfile
import scala.concurrent.{ ExecutionContext , Future }
import slick.ast.BaseTypedType
import slick.jdbc.JdbcType

@Singleton
class HabitRepo @Inject()(
  val dbConfigProvider: DatabaseConfigProvider,
)(using ec: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile]{
  import dbConfig.profile.api._

  class HabitTable(tag: Tag) extends Table[Habit](tag, "HABITS"){

    def id = column[UUID]("ID", O.PrimaryKey)
    def name = column[String]("NAME")
    def goal = column[Option[String]]("GOAL")
    def totalDays = column[Int]("TOTAL_DAYS")
    def streak = column[Int]("STREAK")

    def * = (name, goal, totalDays, streak ,id).mapTo[Habit]
  }

  val habits = TableQuery[HabitTable]

  def addHabit(habit: Habit): Future[Int] = {
    val action: DBIO[Int] = {
      habits += habit
    }
    db.run(action)
  }

  def deleteHabit(id: UUID): Future[Int] = {
    val action: DBIO[Int] = {
      habits.filter( h => h.id === id ).delete
    }
    db.run(action)
  }

  def listHabits(): Future[Seq[Habit]] = {
    val action: DBIO[Seq[Habit]] = {
      habits.result
    }
    db.run(action)
  }


}
