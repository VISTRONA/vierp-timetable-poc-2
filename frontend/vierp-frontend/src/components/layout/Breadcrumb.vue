<script setup>
defineProps({
  currentPage: {
    type: String,
    default: 'Upload Timetable',
  },
})
</script>

<template>
  <div class="breadcrumb">
    <span class="home">⌂</span>
    <span>Home</span>

    <span class="separator">/</span>

    <span>TimeTable</span>

    <span class="separator">/</span>

    <span class="current">{{ currentPage }}</span>
  </div>
</template>

<style scoped>
.breadcrumb {
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 26px 28px 18px;
  font-size: 14px;
  color: #333;
}

.home {
  font-size: 18px;
  font-weight: bold;
}

.separator {
  color: #777;
}

.current {
  color: #aaa;
}
</style>