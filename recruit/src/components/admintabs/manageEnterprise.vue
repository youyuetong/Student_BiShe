<template>
  <div class="wrapper">
    <div v-if="show" class="nofind">
      <img src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1524746733938&di=9ae24b5205e97c2876b48d3ff26f1c23&imgtype=0&src=http%3A%2F%2Fwww.snlfjx.com%2Fforum%2FSkin%2Fimgs%2Fno-data.png" style="margin: 0 auto">
      <p>暂时没有企业入驻哦</p>
    </div>
    <el-card  v-for="(item, key) in companyList" :key="key" class="mycard" style="height: 120px" v-if="!show">
      <div class="myflex" style="width: 70%">
        <h4 style="text-align:left">公司名称：{{item.name}}</h4>
        <h4 style="text-align:left">公司简介：{{item.introduction}}</h4>
        <h4 style="text-align:left">公司电话：{{item.mobile}}</h4>
        <h4 style="text-align:left">建司时间：{{item.birthDate}}</h4>
       
        <!-- <h4 style="text-align:left">职位诱惑：{{item.temptation}}</h4>
        <h4 style="text-align:left">技能标签：{{item.tag}}</h4> -->
      </div>

      <!-- <div class="myflex" style="width: 40%">
        <h4 style="text-align:left">学历要求：{{item.education}}</h4>
        <h4 style="text-align:left">工作地点：{{item.workAddress}}</h4>
        <h4 style="text-align:left">是否发布：{{item.activeFlg == '2' ? '已发布' : '未发布'}}</h4>
        <h4 style="text-align:left">发布时间：{{item.publishedTime}}</h4>
      </div> -->

      <div class="myflex" style="float:right;">
        <p style="text-align:left">
          <el-tag type="warning" effect="dark" v-if="item.status == '待审核'">待审核</el-tag>
          <el-tag type="error" effect="dark" v-if="item.status == '审核驳回'">审核驳回</el-tag>
          <el-tag type="success" effect="dark" v-if="item.status == '审核通过'">审核通过</el-tag>

           <el-button class="" size="small" @click="viewDetail(item.id)">查看详情</el-button>
        </p>
  
        <p>
        <el-button class="" @click="auditPass(item.id)" v-if="item.status == '待审核'"  size="small">审核通过</el-button>
        <el-button class="" @click="auditReject(item.id)" v-if="item.status == '待审核'"  size="small">审核驳回</el-button>
         </p>
        <!-- <el-button class="" @click="deletejob(item.id)">删除职位</el-button> -->
      </div>
      <!-- <el-button class="delbtn" @click="deletejob(item.id)">删除职位</el-button>
      <el-button class="delbtn" @click="deletejob(item.id)">删除职位</el-button> -->
    </el-card>

    <el-dialog :title="companyInfo.name+'的信息'" :visible.sync="companyDialogShowFlag">
        <table border="1" cellspacing="0" style="border-color:#ededed" class="mytable">
          <tr>
            <td>企业名称：</td>
            <td>{{companyInfo.name}}</td>
          </tr>
          <tr>
            <td>成立时间：</td>
            <td>{{companyInfo.birthDate}}</td>
          </tr>
          <tr>
            <td>简介：</td>
            <td>{{companyInfo.introduction}}</td>
          </tr>
          <tr>
            <td>联系电话：</td>
            <td>{{companyInfo.mobile}}</td>
          </tr>
          <tr>
            <td>地址：</td>
            <td>{{companyInfo.address}}</td>
          </tr>
          <tr>
            <td>微信ID：</td>
            <td>{{companyInfo.wechatId}}</td>
          </tr>
          <tr>
            <td>所在城市：</td>
            <td>{{companyInfo.city}}</td>
          </tr>
          <tr>
            <td>所在区：</td>
            <td>{{companyInfo.region}}</td>
          </tr>
          <tr>
            <td>当前状态：</td>
            <td>{{companyInfo.status}}</td>
          </tr>
          <tr>
            <td>审核时间：</td>
            <td>{{companyInfo.auditTime}}</td>
          </tr>
        </table>
      </el-dialog>
  </div>
</template>

<style>

  .mytable {
    width: 100%;
    height: 700px;
  }
</style>


<script>
import fetch from '../../api/fetch'
export default {
  data () {
    return {
      companyList: [],
      count: 0,
      show: false,
      companyDialogShowFlag: false,
      companyInfo: {
        name: null,
        birthDate: null,
        address: null,
        introduction: null,
        wechatId: null,
        mobile: null,
        city: null,
        region: null,
        status: null,
        auditTime: null
      }
    }
  },
  mounted () {
    this.getCompany()
  },
  watch: {
    count () {
      location.reload()
    }
  },
  methods: {
    checkDetail (id) {
      localStorage.setItem('jobId', id)
      this.$router.push({name: 'jobInfo'})
    },
    getCompany () {
      let param = {};


      fetch.getCompany(param).then(res => {
        console.log(res);
        let data = JSON.parse(res.data);
        if (res.status === 200) {
          this.companyList = data.queryresult
        }
      })
    },

    viewDetail(id){
      let _this = this;

      this.companyDialogShowFlag = true
        fetch
          .getCompanyDetail(id)
          .then(res => {
             let data = JSON.parse(res.data);
            if (res.status === 200) {
              this.companyInfo = data.enterpriseInfo;
              // this.recruit = data.jobList;
              // if (this.recruit.length === 0) {
              //   this.isShow = false
              // }
            }
          })
          .catch(e => {
            console.log(e)
          })
    },

    auditPass(id){

      let _this = this;
      fetch
      .auditEnterprise(id, "1")
      .then(res => {
        let data = JSON.parse(res.data);

        if (res.status === 200) {
          // if (res.data.success === true) {
            if (data.result) {
              _this.$message.success('操作成功')
              _this.getCompany();
            }else{
                _this.$message.error('操作失败')
            }
          // }
        }
      })
      .catch(e => {
        console.log(e)
      })
    },

    auditReject(id){
      let _this = this;
      fetch
      .auditEnterprise(id, "2")
      .then(res => {
        let data = JSON.parse(res.data);

        if (res.status === 200) {
          // if (res.data.success === true) {
            if (data.result) {
              _this.$message.success('操作成功')
              _this.getCompany();
            }else{
                _this.$message.error('操作失败')
            }
          // }
        }
      })
      .catch(e => {
        console.log(e)
      })
    }
  }
}
</script>

<style>

.mycard {
  border-left: 5px solid #888;
  height: 100px;
  margin-bottom: 14px;
}
.myflex {
  float: left;
}
.delbtn {
  float: right;
}
</style>
