import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import type { Habit } from '@/types/habit'
import { habitService } from '@/api/habitService'

export const useHabitStore = defineStore('habit', () => {
  const habits = ref<Habit[]>([])

  const fetchHabits = async () => {
    habits.value = await habitService.fetchHabits()
  }

  const addHabit = async (habit: Habit) => {
    await habitService.addHabit(habit)
    await fetchHabits()
  }

  const deleteHabit = async (id: string) => {
    await habitService.deleteHabit(id)
    await fetchHabits()
  }

  return {
    habits,
    fetchHabits,
    addHabit,
    deleteHabit,
  }
})
