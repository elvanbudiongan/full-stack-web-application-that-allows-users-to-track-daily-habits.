package controllers

import repo.HabitRepo
import domains.{ Habit, habitForm}

import javax.inject._
import java.util.UUID
import play.api._
import play.api.mvc._
import play.api.libs.json._
import scala.concurrent.{ Future, ExecutionContext }


@Singleton
class HabitController @Inject()(
  cc: ControllerComponents,
  habitRepo: HabitRepo
)(
  implicit ec: ExecutionContext
) extends AbstractController(cc){
  implicit val habitFormat: OFormat[Habit]= Json.format[Habit]

  //add habit
  def addHabit: Action[AnyContent] = Action.async{ implicit request =>
    habitForm
    .bindFromRequest()
    .fold(
      formWithErrors => {
       Future.successful(BadRequest("Invalid data"))
      },
      habitData => {
        val habit = Habit( name = habitData.name, goal = habitData.goal)
        habitRepo.addHabit(habit).map{ _ =>
          Created(Json.obj("message" -> "Habit Added successfully", "habit" -> Json.toJson(habit)))
        }
      }
    )
  }

  def deleteHabit(id: UUID): Action[AnyContent] = Action.async{ implicit request =>
    habitRepo.deleteHabit(id).map{ deletedCount =>
      if( deletedCount > 0){
        Ok(Json.obj("message" -> "Habit deleted successfully"))
      } else{
        NotFound(Json.obj("error" -> "Habit Not Found"))
      }
    }
  }

  def listHabits: Action[AnyContent] = Action.async{ implicit request =>
    habitRepo.listHabits().map{ habits =>
      Ok(Json.toJson(habits))
    }
  }

}
