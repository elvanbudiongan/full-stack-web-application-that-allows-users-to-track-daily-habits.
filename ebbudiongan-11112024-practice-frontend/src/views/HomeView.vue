
<script setup lang="ts">
import { onMounted } from 'vue'
import { useHabitStore } from '@/stores/habitStore'
import HabitForm from '@/components/HabitForm.vue'

const habitStore = useHabitStore()

onMounted(() => {
  habitStore.fetchHabits()
})
</script>

<template>
  <div>
    <HabitForm />
    <div class="listContainer">
      <div v-for="habit in habitStore.habits" :key="habit.id" class="habit-item">
        <input type="checkbox">
        <span class="habit-name">Name: {{ habit.name }}</span>
        <span class="habit-goal">Goal: {{ habit.goal || 'None' }}</span>

        <button @click="habitStore.deleteHabit(habit.id)">Delete Habit</button>
      </div>
    </div>
  </div>
</template>

<style scoped>
 .habit-tracker {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

h1 {
  text-align: center;
  color: #333;
}

.listContainer {
  display: flex;
  flex-direction: column;
  margin-top: 30px;
}

.habit-item {
  display: flex;
  align-items: center;
  padding: 10px;
  background-color: #fff;
  border: 1px solid #ddd;
  border-radius: 4px;
  margin-bottom: 10px;
  transition: background-color 0.2s;
  width: 50%;
  margin-left: 45vh;
}

.habit-item:hover {
  background-color: #f0f0f0;
}

.habit-checkbox {
  margin-right: 2px;
}

.habit-name,
.habit-goal {
  margin-left: 5px;
  flex-grow: 1;
}

.delete-button {
  background-color: #ff4d4d;
  color: white;
  border: none;
  border-radius: 4px;
  padding: 5px 10px;
  cursor: pointer;
  transition: background-color 0.2s;
}

.delete-button:hover {
  background-color: #ff1a1a;
}
span{
  color: black;
}
</style>

