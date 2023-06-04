<template>
  <div>
    <el-alert v-if="bussiness.bstatus==1" title="您的账号被封禁，若有疑问请联系管理员" type="warning" show-icon>  </el-alert>
    <el-dialog
      title="商品信息"
      v-model="dialogVisible"
      width="30%"
      :before-close="handleClose"
    >
      <el-form
        :model="ruleForm"
        :rules="rules"
        ref="ruleForm"
        label-width="100px"
        class="demo-ruleForm"
      >
        <el-form-item label="商品名称" prop="name">
          <el-input v-model="ruleForm.name"></el-input>
        </el-form-item>
        <el-form-item label="商品价格" prop="price">
          <el-input v-model="ruleForm.price"></el-input>
        </el-form-item>
        <el-form-item label="商品分类" prop="type">
          <el-select v-model="ruleForm.type" placeholder="选择类型">
            <el-option label="手机" value="1"></el-option>
            <el-option label="电脑" value="2"></el-option>
            <el-option label="电视" value="3"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="商品描述" prop="content">
          <el-input v-model="ruleForm.content"></el-input>
        </el-form-item>
        <el-form-item label="商品库存" prop="number">
          <el-input v-model="ruleForm.number"></el-input>
        </el-form-item>
        <el-form-item label="商品图片" prop="image">
          <el-upload
            class="upload-demo"
            drag
            action="http://localhost:80/prod/upload"
            :on-success="handleAvatarSuccess"
            multiple
          >
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">
              将文件拖到此处，或<em>点击上传</em>
            </div>
            <div class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
          </el-upload>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')"
            >提交</el-button
          >
          <el-button @click="resetForm('ruleForm')">重置</el-button>
        </el-form-item>
      </el-form>

      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
        </span>
      </template>
    </el-dialog>
    <div class="opration">
      <el-form :inline="true" :model="formInline" class="demo-form-inline">
        <el-form-item label="类型">
          <el-select
            v-model="formInline.status"
            placeholder="选择类型"
            clearable
          >
            <el-option label="手机" value="1"></el-option>
            <el-option label="电脑" value="2"></el-option>
            <el-option label="电视" value="3"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">查询</el-button>
        </el-form-item>
      </el-form>
      <el-button type="success" @click="handleaddcar()">新增商品</el-button>
    </div>
    <el-table :data="tableData" style="width: 100%">
      <el-table-column type="expand">
        <template v-slot="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="商品名称">
              <span>{{ props.row.pname }}</span>
            </el-form-item>
            <el-form-item label="商品分类">
              <span>{{ typeMap.get(props.row.typeId + "") }}</span>
            </el-form-item>
            <el-form-item label="库存">
              <span>{{ props.row.pnumber }}</span>
            </el-form-item>
            <el-form-item label="商品描述">
              <span>{{ props.row.pcontent }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column label="商品 ID" prop="pid"> </el-table-column>
      <el-table-column label="图片">
        <template v-slot="scope">
          <img
            v-if="scope.row.pimage !== undefined"
            :src="scope.row.pimage"
            lazy
            style="width: 100px; height: 80px"
          />
        </template>
      </el-table-column>
      <el-table-column label="商品名称" prop="pname"> </el-table-column>
      <el-table-column label="价格" prop="pprice"> </el-table-column>
      <el-table-column label="操作">
        <template v-slot="scope">
          <el-button type="danger" @click="delprod(scope.row)" :disabled="bussiness.bstatus == 1">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination
      @current-change="handleCurrentChange"
      :current-page="current"
      :page-size="5"
      layout="total,prev, pager, next, jumper"
      :total="totalPage"
    >
    </el-pagination>
  </div>
</template>
  <script>
import { getprodlist, gettype, addprod, delprod } from "@/api/productApi";
import { confirmorder } from "@/api/orderApi";
export default {
  data() {
    var checkNum = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("不能为空"));
      }
      setTimeout(() => {
        if (isNaN(value)) {
          callback(new Error("请输入数字值"));
        } else {
          callback();
        }
      }, 1000);
    };
    // 在代码中，当输入值为4时，控制台会输出false，是因为在判断是否为整数的语句中使用了Number.isInteger()方法，该方法会判断输入值是否为整数，
    // 但是在JavaScript中，4默认被解读为数字类型，而数字类型不是整数类型，因此会输出false，这也是判断输入值是否为整数的判断中，
    // 为什么4会被判断为不是整数的原因。
    var checkInt = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("不能为空"));
      }
      setTimeout(() => {
        if (value - Math.floor(value) != 0 || value <= 0) {
          callback(new Error("请输入正整数值"));
        } else {
          callback();
        }
      }, 1000);
    };
    return {
      selectedrow: {},
      dialogVisible: false,
      formInline: [],
      tableData: [],
      param: {},
      current: 1,
      totalPage: 5,
      typeMap: new Map([]),
      bussiness: {},
      ruleForm: {},
      rules: {
        name: [
          { required: true, message: "请输入商品名称", trigger: "blur" },
          {
            min: 1,
            max: 20,
            message: "长度在 1 到 20 个字符",
            trigger: "blur",
          },
        ],
        type: [
          { required: true, message: "请选择商品类型", trigger: "change" },
        ],
        price: [{ required: true, validator: checkNum, trigger: "blur" }],
        number: [{ required: true, validator: checkInt, trigger: "blur" }],
        // image: [{ required: true, message: "请上传图片", trigger: "blur" }],
      },
    };
  },
  methods: {
    getproductlist() {
      this.param.bid = this.bussiness.bid;
      getprodlist(this.current, this.param).then((resp) => {
        if (resp.data.code !== "00000") {
          this.tableData = resp.data.records;
          this.totalPage = resp.data.total;
          for (var item in this.tableData) {
            this.tableData[item].pimage = String(
              "http://localhost:80/images/" + this.tableData[item].pimage
            );
          }
        } else {
          this.$message({
            message: "商户列表显示失败",
            type: "warning",
          });
        }
      });
    },
    gettypelist() {
      gettype().then((resp) => {
        const json = resp.data.data;
        for (let item in json) {
          this.typeMap.set(item, json[item]);
        }
      });
    },
    onSubmit() {
      this.param = {};
      this.param.typeId = this.formInline.status;
      this.getproductlist();
    },
    handleCurrentChange(val) {
      this.current = val;
      this.getproductlist();
    },
    handleaddcar() {
      if(this.bussiness.bstatus==1){
        this.$message({
                type: "warning",
                message: "您已被封禁无法新增商品",
              });
      }else{
        this.dialogVisible = true;
      }
    },
    handleClose(done) {
      this.$confirm("确认关闭？")
        .then(() => {
          done();
        })
        .catch(() => {});
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.param = {
            pname: this.ruleForm.name,
            pcontent: this.ruleForm.content,
            pprice: this.ruleForm.price,
            pimage: this.ruleForm.pimage,
            pnumber: this.ruleForm.number,
            typeId: this.ruleForm.type,
            bid: this.bussiness.bid,
          };
          addprod(this.param).then((resp) => {
            if (resp.data.code === "00000") {
              this.$message({
                type: "success",
                message: "添加成功",
              });
            } else {
              this.$message({
                type: "error",
                message: "添加失败",
              });
            }
            console.log(resp);
            (this.param = {}), this.getproductlist();
            this.dialogVisible = false;
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    delprod(row) {
      console.log(row);
      this.$confirm("请确定该商品的相关订单已处理完毕", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          var order = {
            pid: row.pid,
            bid: this.bussiness.bid,
          };
          confirmorder(order).then((resp) => {
            if (resp.data.code == "A0001") {
              this.$message({
                type: "warning",
                message: "仍有订单未处理，请等订单收货!",
              });
            } else {
              delprod({pid:row.pid}).then((resp) => {
                if (resp.data.code == "00000") {
                  this.getproductlist();
                  this.$message({
                    type: "success",
                    message: "删除成功!",
                  });
                } else {
                  this.$message({
                    type: "error",
                    message: "删除失败!",
                  });
                }
              });
            }
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },
     //上传图片
    handleAvatarSuccess(res, file) {
      // if(res.data.code=="00000"){
      console.log("handleAvatarSuccess图片上传成功返回的访问路径是:",file.response.data);
      this.ruleForm.pimage = file.response.data
      console.log("此时dataForm里面picture路径是:",this.ruleForm.pimage);
      // }else{
      //   this.$message({
      //           type: "error",
      //           message: "文件不符合条件",
      //         });
      // }
    },
  },
  created() {
    this.bussiness = JSON.parse(sessionStorage.getItem("bussiness"));
    this.gettypelist();
    this.getproductlist();
  },
};
</script>
  <style>
.demo-table-expand {
  font-size: 0;
}
.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}
.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
.opration {
  display: flex;
  justify-content: center;
}
</style>