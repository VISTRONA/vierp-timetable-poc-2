<script setup>
import { ref } from 'vue'
import { uploadTimetable } from '../../services/timetableApi'

const selectedFile = ref(null)
const uploading = ref(false)
const message = ref('')
const error = ref(false)

function handleFile(event) {
  selectedFile.value = event.target.files[0]
  message.value = ''
  error.value = false
}

async function uploadFile() {
  if (!selectedFile.value) {
    message.value = 'Please select an Excel file first.'
    error.value = true
    return
  }

  uploading.value = true
  message.value = ''
  error.value = false

  try {
    await uploadTimetable(selectedFile.value)
    message.value = 'Timetable uploaded successfully.'
  } catch (err) {
    message.value = 'Upload failed. Please check the backend connection.'
    error.value = true
  } finally {
    uploading.value = false
  }
}
</script>

<template>
  <div class="uploader">
    <label class="file-area">
      <div class="upload-icon">↑</div>

      <strong>
        {{ selectedFile ? selectedFile.name : 'Choose timetable Excel file' }}
      </strong>

      <span>
        {{ selectedFile ? 'File selected' : 'Click here to browse' }}
      </span>

      <input
        type="file"
        accept=".xlsx,.xls"
        @change="handleFile"
      />
    </label>

    <div class="actions">
      <button
        class="upload-button"
        :disabled="uploading"
        @click="uploadFile"
      >
        {{ uploading ? 'Uploading...' : 'Upload Timetable' }}
      </button>
    </div>

    <p
      v-if="message"
      class="message"
      :class="{ error: error }"
    >
      {{ message }}
    </p>
  </div>
</template>

<style scoped>
.uploader {
  padding: 25px;
}

.file-area {
  min-height: 180px;
  border: 2px dashed #d5d8e5;
  border-radius: 5px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  transition: 0.2s;
}

.file-area:hover {
  border-color: #6573c3;
  background: #fafaff;
}

.file-area input {
  display: none;
}

.upload-icon {
  width: 48px;
  height: 48px;
  border-radius: 50%;
  background: #eef0fb;
  color: #303f9f;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 25px;
  margin-bottom: 12px;
}

.file-area strong {
  color: #333;
  font-size: 15px;
}

.file-area span {
  color: #888;
  font-size: 13px;
  margin-top: 6px;
}

.actions {
  display: flex;
  justify-content: flex-end;
  margin-top: 20px;
}

.upload-button {
  border: none;
  border-radius: 4px;
  padding: 10px 20px;
  background: #303f9f;
  color: white;
  font-size: 14px;
  cursor: pointer;
}

.upload-button:hover {
  opacity: 0.9;
}

.upload-button:disabled {
  opacity: 0.6;
  cursor: not-allowed;
}

.message {
  margin: 15px 0 0;
  font-size: 13px;
  color: #3d9146;
}

.message.error {
  color: #d64545;
}
</style>