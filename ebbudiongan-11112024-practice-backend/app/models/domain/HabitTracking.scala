package domains
import java.util.UUID
import java.time.LocalDate

case class HabitTracking(
  idTracking: UUID,
  idHabit: UUID,
  date: LocalDate,
  completed: Boolean,
)

object HabitTracking{
  def unapply(ht: HabitTracking): Option[(UUID, UUID, LocalDate, Boolean)] =
   Some((ht.idTracking, ht.idHabit, ht.date, ht.completed))
}
