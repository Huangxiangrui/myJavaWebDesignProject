<template>
  <div id="adminhome">
    <div class="logdiv" style="margin-left: 80%">
      <p style="display: inline">管理员{{ admin.aname }}&nbsp;</p>
      <router-link to="/login">
        <el-button @click="logout()"> 登出 </el-button>
      </router-link>
    </div>

    <div class="main">
      <el-tabs type="border-card">
        <el-tab-pane label="商户列表">
          <el-form :inline="true" :model="formInline" class="demo-form-inline">
            <el-form-item label="商户">
              <el-input v-model="formInline.buss" placeholder="商户"></el-input>
            </el-form-item>
            <el-form-item label="状态">
              <el-select
                v-model="formInline.status"
                placeholder="选择状态"
                clearable
              >
                <el-option label="正常" value="0"></el-option>
                <el-option label="封禁" value="1"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="onSubmit">查询</el-button>
            </el-form-item>
          </el-form>
          <el-table
            :data="tableData"
            style="width: 50%; margin: auto"
            :row-class-name="tableRowClassName"
          >
            <el-table-column
              prop="bid"
              label="商户ID"
              width="180"
              header-align="center"
              align="center"
            >
            </el-table-column>
            <el-table-column
              prop="bname"
              label="商户名"
              width="180"
              header-align="center"
              align="center"
            >
            </el-table-column>
            <el-table-column
              prop="bstatus"
              label="状态"
              header-align="center"
              align="center"
            >
              <template v-slot="{ row }">
                {{ row.bstatus === 0 ? "正常" : "封禁" }}
              </template>
            </el-table-column>
            <el-table-column label="操作" header-align="center" align="center">
              <template v-slot="scope">
                <el-button
                  v-if="scope.row.bstatus === 0"
                  type="danger"
                  @click="handleBan(scope.row)"
                  >封禁</el-button
                >
                <el-button
                  v-if="scope.row.bstatus === 1"
                  type="success"
                  @click="handleUnban(scope.row)"
                  >解封</el-button
                >
              </template>
            </el-table-column>
          </el-table>
          <br>
          <el-pagination
            style="width: 50%; margin: auto"
            @current-change="handleCurrentChange"
            :current-page="current"
            :page-size="5"
            layout="total,prev, pager, next, jumper"
            :total="totalPage"
          >
          </el-pagination>
        
        </el-tab-pane>
        <el-tab-pane label="申请审核"><ApplyAudit></ApplyAudit></el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>
<script>
import { getBuslist, updBuss } from "@/api/adminhome";
import ApplyAudit from "@/views/Admin/approvalView";
export default {
  components: {
    ApplyAudit,
  },
  data() {
    return {
      totalPage:0,
      admin: {
        aid: null,
        aname: "",
      },
      current: 1,
      param: {
        bid: null,
        bname: "",
        bstatus: null,
      },
      tableData: [],
      formInline: {
        buss: "",
        status: null,
      },
      status: "正常",
    };
  },
  methods: {
    logout() {
      sessionStorage.removeItem("admin");
    },
    tableRowClassName(row) {
      //console.log(row);
      if (row.row.bstatus === 0) {
        return "success-row";
      } else {
        return "warning-row";
      }
    },
    onSubmit() {
      this.param.bname = this.formInline.buss;
      this.param.bstatus = this.formInline.status;
      this.getlist();
    },
    getlist() {
      // console.log("enter..");
      getBuslist(this.current, this.param).then((resp) => {
        // console.log("调用getBuslist接口返回的数据是：", resp.data);
        if (resp.data.code !== "00000") {
          this.tableData = resp.data.records;
          this.totalPage = resp.data.total;
        } else {
          this.$message({
            message: "商户列表显示失败",
            type: "warning",
          });
        }
      });
    },
    handleBan(row) {
      this.$confirm(`是否封禁用户:${row.bname}?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          updBuss({ bid: row.bid, bstatus: 1 }).then((resp) => {
            if (resp.data.code === "00000") {
              this.param.bstatus = null;
              this.param.bid = null;
              this.getlist();
            } else {
              this.$message({
                message: "封禁失败",
                type: "warning",
              });
            }
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消操作",
          });
        });
    },
    handleUnban(row) {
      this.$confirm(`是否解禁用户:${row.bname}?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          updBuss({ bid: row.bid, bstatus: 0 }).then((resp) => {
            if (resp.data.code === "00000") {
              this.param.bstatus = null;
              this.param.bid = null;
              this.getlist();
            } else {
              this.$message({
                message: "解禁失败",
                type: "warning",
              });
            }
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消操作",
          });
        });
    },
    handleCurrentChange(val) {
        this.current=val;
        this.getproductlist();
      }
  },
  created() {
    this.admin = JSON.parse(sessionStorage.getItem("admin"));
    this.getlist();
  },
};
</script>
<style>
tr.el-table__row.success-row {
  background: #d4e6f3;
}
tr.el-table__row.warning-row {
  background: rgb(247, 202, 202);
}
</style>