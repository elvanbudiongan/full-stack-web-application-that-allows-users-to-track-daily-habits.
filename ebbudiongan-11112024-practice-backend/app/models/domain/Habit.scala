package domains

import java.util.UUID
import play.api.data._
import play.api.data.Forms._


case class Habit(
  name: String,
  goal: Option[String],
  totalDays: Int = 0,
  streak: Int = 0,
  id: UUID = UUID.randomUUID()
)

object Habit{
  def apply(
    name: String,
    goal: Option[String]
  ):Habit = {
    new Habit (name, goal)
  }

  def unapply(h: Habit) =
   Some(( h.name, h.goal))
}



val habitForm = Form(
  mapping(
    "name" -> nonEmptyText,
    "goal"  -> optional(text)
  )(Habit.apply)(Habit.unapply)
)
