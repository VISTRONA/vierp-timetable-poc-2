<script setup>
import Header from '../components/layout/Header.vue'
import Breadcrumb from '../components/layout/Breadcrumb.vue'
import FileUploader from '../components/upload/FileUploader.vue'
</script>

<template>
  <div class="page">
    <Header />

    <main>
      <Breadcrumb currentPage="Upload Timetable" />

      <section class="content">
        <div class="page-title">
          <h1>Upload Timetable</h1>
          <p>Upload the timetable Excel file to update the timetable.</p>
        </div>

        <div class="upload-card">
          <div class="card-header">
            <h2>Timetable Upload</h2>
            <span class="required">Excel File</span>
          </div>

          <FileUploader />
        </div>
      </section>
    </main>
  </div>
</template>

<style scoped>
.page {
  min-height: 100vh;
  background: #f7f8fc;
}

main {
  min-height: calc(100vh - 102px);
}

.content {
  padding: 10px 85px 60px;
}

.page-title {
  margin-bottom: 25px;
}

.page-title h1 {
  margin: 0 0 6px;
  font-size: 25px;
  font-weight: 500;
  color: #333;
}

.page-title p {
  margin: 0;
  color: #777;
  font-size: 14px;
}

.upload-card {
  max-width: 850px;
  background: white;
  border: 1px solid #e2e2e2;
  border-radius: 5px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.08);
}

.card-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 18px 22px;
  border-bottom: 1px solid #e8e8e8;
}

.card-header h2 {
  margin: 0;
  font-size: 17px;
  font-weight: 500;
  color: #333;
}

.required {
  font-size: 12px;
  color: #777;
}
</style>