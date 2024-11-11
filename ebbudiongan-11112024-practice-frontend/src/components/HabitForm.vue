<script setup lang="ts">
import { ref } from 'vue'
import type { Habit } from '@/types/habit'
import { useHabitStore } from '@/stores/habitStore'

const habitStore = useHabitStore()
const habitGoal = ref('')
const habitName = ref('')

const onSubmit = async () => {
  await habitStore.addHabit({name: habitName.value, goal: habitGoal.value})
  habitName.value = ''
  habitGoal.value = ''
}
</script>

<template>
  <div>
    <h2>Add A New Habit</h2>
    <form @submit.prevent="onSubmit">
      <div class="input-containers">
        <div>
          <label for="inputHabitName">Habit Name  </label>
          <input v-model="habitName" id="inputHabitName" required />
        </div>

        <div>
          <label for="inputHabitGoal">Habit Goal(Optional)  </label>
          <input v-model="habitGoal" id="inputHabitGoal"/>
        </div>
      </div>

      <button type="submit">Add Habit</button>
    </form>
  </div>

</template>

<style scoped>
  h2{
    padding: 40px;
    background: #28bca9;
    margin: 10px;
    border-radius: 10px;
    color: Black;
  }
  .input-containers{
    margin-left: 45vh ;
    width: 50%;
    display: flex;
    align-items: center;
    justify-content: space-between;
    background: blue;
    padding: 30px;
    border-radius: 10px;
    margin-top: 30px;
  }

  label{
    color: black;
  }
</style>
