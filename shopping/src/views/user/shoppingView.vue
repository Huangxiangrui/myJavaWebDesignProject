<template>
  <div>
    <el-dialog
      title="加入购物车"
      v-model="dialogVisible"
      width="30%"
      :before-close="handleClose"
    >
      <el-descriptions title="商品信息" :column="1">
        <el-descriptions-item label="商品名">{{
          selectedrow.pname
        }}</el-descriptions-item>
        <el-descriptions-item label="商品描述">{{
          selectedrow.pcontent
        }}</el-descriptions-item>
        <el-descriptions-item label="库存">{{
          selectedrow.numbers
        }}</el-descriptions-item>
        <el-descriptions-item label="数量">
          <el-input-number
            v-model="num"
            :min="1"
            :max="10"
            label="选择数量"
          ></el-input-number>
        </el-descriptions-item>
      </el-descriptions>

      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="carshopconfirm()">确 定</el-button>
        </span>
      </template>
    </el-dialog>

    <el-form :inline="true" :model="formInline" class="demo-form-inline">
      <el-form-item label="商品">
        <el-input v-model="formInline.buss" placeholder="关键词"></el-input>
      </el-form-item>
      <el-form-item label="类型">
        <el-select v-model="formInline.status" placeholder="选择类型" clearable>
          <el-option label="手机" value="1"></el-option>
          <el-option label="电脑" value="2"></el-option>
          <el-option label="电视" value="3"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">查询</el-button>
      </el-form-item>
    </el-form>

    <el-table :data="tableData" style="width: 100%">
      <el-table-column type="expand">
        <template v-slot="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="商品名称">
              <span>{{ props.row.pname }}</span>
            </el-form-item>
            <el-form-item label="所属商户">
              <span>{{ bsMap.get(props.row.bid + "") }}</span>
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
          <el-button type="success" @click="handleaddcar(scope.row)"
            >加入购物车</el-button
          >
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
import { getprodlist, gettype, getBs } from "@/api/productApi";
import { addcarshop } from "@/api/shopcar";
export default {
  data() {
    return {
      num: 1,
      selectedrow: {},
      dialogVisible: false,
      formInline: [],
      tableData: [],
      param: {},
      current: 1,
      totalPage: 5,
      typeMap: new Map([]),
      bsMap: new Map([]),
      user: {},
    };
  },
  methods: {
    getproductlist() {
      getprodlist(this.current, this.param).then((resp) => {
        if (resp.data.code !== "00000") {
          this.tableData = resp.data.records;
          this.totalPage = resp.data.total;
          for (var item in this.tableData) {
            this.tableData[item].pimage =  String(
            "http://localhost:80/images/" + this.tableData[item].pimage
            );
            //"http://localhost:80/images/f593b895a8664324a16ea5c69333037f.jpg"
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
    getBussinessman() {
      getBs().then((resp) => {
        const json = resp.data.data;
        for (let item in json) {
          this.bsMap.set(item, json[item]);
        }
      });
    },
    onSubmit() {
      this.param = {};
      this.param.typeId = this.formInline.status;
      this.param.pname=this.formInline.buss;
      this.getproductlist();
    },
    handleCurrentChange(val) {
      this.current = val;
      this.getproductlist();
    },
    handleaddcar(row) {
      this.dialogVisible = true;
      this.selectedrow.bid=row.bid;
      this.selectedrow.pid = row.pid;
      this.selectedrow.pname = row.pname;
      this.selectedrow.pcontent = row.pcontent;
      this.selectedrow.numbers = row.pnumber;
      this.selectedrow.num = this.num;
    },
    carshopconfirm() {
      var carshop = {
        uid: this.user.uid,
        pid: this.selectedrow.pid,
        pname: this.selectedrow.pname,
        numbers: this.num,
        bid:this.selectedrow.bid
      };
      addcarshop(carshop).then((resp) => {
        console.log(resp);
        if (resp.data.code == "00000") {
          this.$message({
            message: "添加购物车成功",
            type: "success",
          });
        }
      });
      this.dialogVisible = false;
      this.num = 1;
      this.selectedrow = {};
      this.getproductlist();
    },
    handleClose(done) {
      this.$confirm("确认关闭？")
        .then(() => {
          done();
        })
        .catch(() => {});
    },
  },
  created() {
    this.user = JSON.parse(sessionStorage.getItem("user"));
    this.gettypelist();
    this.getproductlist();
    this.getBussinessman();
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
</style>