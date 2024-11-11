import axios from 'axios'
import type { Habit } from '@/types/habit'

//create instance axios
const api = axios.create({
  baseURL: import.meta.env.VITE_API_URL,
})

export const habitService = {
  async fetchHabits(): Promise<Habit[]> {
    const response = await api.get('/habits')
    return response.data
  },
  async addHabit(habit: Habit): Promise<void> {
    await api.post('/habits', habit)
  },
  async deleteHabit(id: string): Promise<void> {
    await api.delete(`/habits/${id}`)
  },
}
