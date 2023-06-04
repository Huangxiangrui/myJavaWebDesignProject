<template>
  <div id="userhome">
    <el-container>
      <el-header>
        <div class="logdiv" style="margin-left: 80%">
          <p style="display: inline">用户{{ user.uname }}您好!&nbsp;</p>
          <router-link to="/login">
            <el-button @click="logout()"> 登出 </el-button>
          </router-link>
        </div>
      </el-header>
      <el-container>
        <el-aside width="200px">
          <el-tabs tab-position="left" style="height: 200px;" v-model="activeName" @tab-click="handleclick">
            <el-tab-pane label="商城" name="0"></el-tab-pane>
            <el-tab-pane label="购物车" name="1"></el-tab-pane>
            <el-tab-pane label="订单管理" name="2"></el-tab-pane>
            <el-tab-pane label="商户中心" name="3"></el-tab-pane>
          </el-tabs>
        </el-aside>
        <el-main><router-view></router-view></el-main>
      </el-container>
    </el-container>
  </div>
</template>
<script>
export default {
  data() {
    return {
      activeName:"0",
      user: {
        uid: null,
        uname: "",
        ustatus: null,
      },
    };
  },
  methods: {
    logout() {
      sessionStorage.removeItem("user");
    },
    handleclick(tab){
      if(tab.props.name=='0'){
        this.$router.push("/userhome/shopping");
      }else if(tab.props.name=='1'){
        this.$router.push("/userhome/shopcar")
      }else if(tab.props.name=='2'){
        this.$router.push("/userhome/order")
      }
      else if(tab.props.name=='3'){
        this.$router.push("/userhome/apply")
      }
    }
  },
  created() {
    this.user = JSON.parse(sessionStorage.getItem("user"));
    this.$router.push("/userhome/shopping");
  },
};
</script>
<style scoped>
</style>