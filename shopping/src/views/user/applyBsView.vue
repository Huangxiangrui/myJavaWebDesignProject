<template>
  <div>
        <el-button v-if ="user.ustatus==0" type="primary" @click="apply()">申请成为商户</el-button>
        <el-button v-else-if ="user.ustatus==2" @click="toBs()">前往商户页面</el-button>
        <p v-else-if="user.ustatus==1">您的申请商户请求正在审核，请耐心等待</p>
  </div>
</template>
<script>
import { applyBs } from "@/api/userApi";
export default {
  data() {
    return {
        user: {
            uid:null,
            uname:"",
            ustatus:null
        },
        param: {},
        active:0
    };
  },
  methods: {
    apply() {
        applyBs({ uid: this.user.uid, ustatus: 1 }).then((resp) => {
          if (resp.data.code == "00000") {
            this.$message({
              message: "申请已提交",
              type: "success",
            });
            this.user.ustatus=1;
            sessionStorage.setItem("user", JSON.stringify(this.user))
          }
          console.log(resp.data);
        });
    },
    toBs(){
      this.$router.push("/login")
    }
  },
  created() {
    this.user = JSON.parse(sessionStorage.getItem("user"));
    console.log(this.user);
  },
};
</script>
<style scoped>
</style>