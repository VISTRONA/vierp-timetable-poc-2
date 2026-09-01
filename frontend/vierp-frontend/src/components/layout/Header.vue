<script setup>
</script>

<template>
  <header class="erp-header">
    <!-- Top bar -->
    <div class="top-bar">
      <div class="brand">
        <div class="vit-logo">VIT</div>
        <span>Vishwakarma Institute of Technology,Pune</span>
      </div>

      <div class="top-icons">
        <span class="network">4G ⚡ 1.55 Mbps</span>
        <span>👤</span>
        <span>🏠</span>
        <span>◉</span>
        <span>?</span>
        <span>🎓</span>
        <span>🔔</span>
        <span>⛶</span>
        <span class="profile">U</span>
      </div>
    </div>

    <!-- Student information bar -->
    <div class="student-bar">
      <div class="student-avatar">P</div>

      <strong>USER</strong>

      <span class="active">
        <span class="active-dot"></span>
        Active
      </span>

      <span>Registration No: ----------</span>

      <span>
        BTech - Computer Science and Engineering
      </span>
    </div>
  </header>
</template>

<style scoped>
.erp-header {
  width: 100%;
  background: white;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.25);
}

.top-bar {
  height: 58px;
  background: #e9ebf8;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 28px 0 12px;
}

.brand {
  display: flex;
  align-items: center;
  gap: 18px;
  font-size: 18px;
  font-weight: 600;
}

.vit-logo {
  width: 32px;
  height: 40px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 12px;
  font-weight: bold;
  color: #2458a6;
}

.top-icons {
  display: flex;
  align-items: center;
  gap: 28px;
  font-size: 21px;
}

.network {
  font-size: 13px;
  color: #46a83f;
  font-weight: 600;
}

.profile {
  width: 36px;
  height: 36px;
  border-radius: 50%;
  background: #101c82;
  color: white;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 13px;
}

.student-bar {
  min-height: 44px;
  display: flex;
  align-items: center;
  gap: 20px;
  padding: 0 110px;
  color: #666;
  font-size: 14px;
}

.student-avatar {
  width: 34px;
  height: 34px;
  border-radius: 50%;
  background: #eee;
  display: flex;
  align-items: center;
  justify-content: center;
  font-weight: bold;
  color: #333;
}

.active {
  display: flex;
  align-items: center;
  gap: 7px;
  background: #e3f5e5;
  color: #43a653;
  padding: 6px 12px;
  border-radius: 18px;
  font-weight: 600;
}

.active-dot {
  width: 10px;
  height: 10px;
  border-radius: 50%;
  background: #43ad50;
}

@media (max-width: 900px) {
  .student-bar {
    padding: 8px 20px;
    flex-wrap: wrap;
  }

  .top-icons {
    gap: 12px;
  }

  .brand span {
    font-size: 14px;
  }
}
</style>