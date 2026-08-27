const API_URL = 'http://localhost:8080/api'

export async function uploadTimetable(file) {
  const formData = new FormData()

  formData.append('file', file)

  const response = await fetch(`${API_URL}/timetable/upload`, {
    method: 'POST',
    body: formData,
  })

  if (!response.ok) {
    throw new Error('Failed to upload timetable')
  }

  return await response.json()
}