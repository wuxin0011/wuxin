<template>
  <div class="errPage-container">
    <el-button icon="el-icon-arrow-left" class="pan-back-btn" @click="back">返回</el-button>
    <div>
      <h2>你没有权限去该页面</h2>
      <ul class="list-unstyled">
        <li>或者你可以去:</li>
        <li class="link-type"><router-link to="/dashboard">回首页</router-link></li>
        <li class="link-type"><router-link to="/dashboard">回首页</router-link></li>
        <li class="link-type"><router-link to="/login">登录</router-link></li>
      </ul>
    </div>

  </div>
</template>

<script>

export default {
  name: 'Page401',
  data() {

  },
  methods: {
    back() {
      if (this.$route.query.noGoBack) {
        this.$router.push({ path: '/dashboard' })
      } else {
        this.$router.go(-1)
      }
    }
  }
}
</script>

<style lang="scss" scoped>
  .errPage-container {
    width: 800px;
    max-width: 100%;
    margin: 100px auto;
    .pan-back-btn {
      background: #008489;
      color: #fff;
      border: none!important;
    }
    .pan-gif {
      margin: 0 auto;
      display: block;
    }
    .pan-img {
      display: block;
      margin: 0 auto;
      width: 100%;
    }
    .text-jumbo {
      font-size: 60px;
      font-weight: 700;
      color: #484848;
    }
    .list-unstyled {
      font-size: 14px;
      li {
        padding-bottom: 5px;
      }
      a {
        color: #008489;
        text-decoration: none;
        &:hover {
          text-decoration: underline;
        }
      }
    }
  }
</style>
