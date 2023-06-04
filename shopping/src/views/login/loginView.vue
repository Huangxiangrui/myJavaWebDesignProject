<template>
  <div>
    <el-card class="box-card">
      <h2>登录</h2>
      <el-form
        :model="ruleForm"
        status-icon
        :rules="rules"
        ref="ruleForm"
        label-position="left"
        label-width="70px"
        class="login-from"
      >
        <el-form-item label="用户名" prop="name">
          <el-input v-model="ruleForm.name"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="pass">
          <el-input
            type="password"
            v-model="ruleForm.pass"
            autocomplete="off"
          ></el-input>
        </el-form-item>
      </el-form>
      <div class="choseGroup">
        <el-radio-group v-model="radio">
          <el-radio :label="3">管理员</el-radio>
          <el-radio :label="6">用户</el-radio>
          <el-radio :label="9">商户</el-radio>
        </el-radio-group>
      </div>
      <br />
      <div class="btnGroup">
        <el-button type="primary" @click="submitForm('ruleForm')"
          >登录</el-button
        >
        <router-link v-if="radio === 6" to="/register">
          <el-button style="margin-left: 10px">注册</el-button>
        </router-link>
        &nbsp;
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </div>
    </el-card>
  </div>
</template>
  
  <script>
import { adminlogin } from "@/api/login";
import { userlogin } from "@/api/login";
import { bussinesslogin } from "@/api/login";
export default {
  data() {
    return {
      radio: 3,
      ruleForm: {
        name: "",
        pass: "",
      },
      rules: {
        name: [
          { required: true, message: "用户名不能为空！", trigger: "blur" },
        ],
        pass: [{ required: true, message: "密码不能为空！", trigger: "blur" }],
      },
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (this.radio === 3) {
            adminlogin({
              aname: this.ruleForm.name,
              apass: this.ruleForm.pass,
            }).then((resp) => {
              if (resp.data.code !== "00000") {
                this.$message({
                  message: "账号或密码错误",
                  type: "warning",
                });
              } else {
                sessionStorage.setItem("admin", JSON.stringify(resp.data.data));
                this.$router.push("/adminhome");
              }
            });
          } else if (this.radio === 6) {
            userlogin({
              uname: this.ruleForm.name,
              upass: this.ruleForm.pass,
            }).then((resp) => {
              if (resp.data.code !== "00000") {
                this.$message({
                  message: "账号或密码错误",
                  type: "warning",
                });
              } else {
                sessionStorage.setItem("user", JSON.stringify(resp.data.data));
                this.$router.push("/userhome");
              }
            });
          } else {
            bussinesslogin({
              bname: this.ruleForm.name,
              bpass: this.ruleForm.pass,
            }).then((resp) => {
              if (resp.data.code !== "00000") {
                this.$message({
                  message: "账号或密码错误",
                  type: "warning",
                });
              } else {
                sessionStorage.setItem("bussiness", JSON.stringify(resp.data.data));
                this.$router.push("/bussinessHome");
              }
            });
          }
        } else {
          // 如果账号或密码有一个没填，就直接提示必填，不向后端请求
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
  },
};
</script>
  
  <style scoped>
.box-card {
  margin: auto auto;
  width: 400px;
}
.login-from {
  margin: auto auto;
}
</style>