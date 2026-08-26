<script setup>
import { ref } from 'vue'
import { uploadTimetable } from '../../services/timetableApi'

const selectedFile = ref(null)
const message = ref('')
const uploading = ref(false)

function handleFile(event) {
  selectedFile.value = event.target.files[0]
  message.value = ''
}

async function uploadFile() {
  if (!selectedFile.value) {
    message.value = 'Please select an Excel file first.'
    return
  }

  uploading.value = true
  message.value = ''

  try {
    const result = await uploadTimetable(selectedFile.value)
    message.value = result.message || 'Timetable uploaded successfully!'
  } catch (error) {
    message.value = 'Upload failed. Please try again.'
    console.error(error)
  } finally {
    uploading.value = false
  }
}
</script>

<template>
  <div class="upload-box">
    <input
      type="file"
      accept=".xlsx"
      @change="handleFile"
    />

    <p v-if="selectedFile">
      Selected: {{ selectedFile.name }}
    </p>

    <button @click="uploadFile" :disabled="uploading">
      {{ uploading ? 'Uploading...' : 'Upload Timetable' }}
    </button>

    <p v-if="message">
      {{ message }}
    </p>
  </div>
</template>

<style scoped>
.upload-box {
  padding: 20px;
}
</style>