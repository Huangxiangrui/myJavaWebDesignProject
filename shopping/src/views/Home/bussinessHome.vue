<template>
  <div id="bussinesshome">
    <el-container>
      <el-header>
        <div class="logdiv" style="margin-left: 80%">
          <p style="display: inline">商户{{ bussiness.bname }}您好!&nbsp;</p>
          <router-link to="/login">
            <el-button @click="logout()"> 登出 </el-button>
          </router-link>
        </div>
      </el-header>
      <el-container>
        <el-aside width="200px">
          <el-tabs
            tab-position="left"
            style="height: 200px"
            v-model="activeName"
            @tab-click="handleclick"
          >
            <el-tab-pane label="商品管理" name="0"></el-tab-pane>
            <el-tab-pane label="订单管理" name="1"></el-tab-pane>
            <el-tab-pane label="商户中心" name="2"></el-tab-pane>
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
      activeName: "0",
      bussiness: {},
    };
  },
  methods: {
    logout() {
      sessionStorage.removeItem("bussiness");
    },
    handleclick(tab) {
      if (tab.props.name == "0") {
        this.$router.push("/bussinessHome/prod");
      } else if (tab.props.name == "1") {
        this.$router.push("/bussinessHome/order");
      }else if(tab.props.name =='2'){
        this.$router.push("/bussinessHome/info");
      }
    },
  },
  created() {
    this.bussiness = JSON.parse(sessionStorage.getItem("bussiness"));
    this.$router.push("/bussinessHome/prod");
  },
};
</script>
  <style scoped>
</style>